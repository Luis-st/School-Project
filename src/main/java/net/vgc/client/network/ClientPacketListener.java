package net.vgc.client.network;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.collect.Table.Cell;

import net.vgc.account.LoginType;
import net.vgc.account.PlayerAccount;
import net.vgc.client.Client;
import net.vgc.client.game.ClientGame;
import net.vgc.client.player.AbstractClientPlayer;
import net.vgc.client.player.LocalPlayer;
import net.vgc.client.player.RemotePlayer;
import net.vgc.client.screen.LobbyScreen;
import net.vgc.client.screen.MenuScreen;
import net.vgc.client.window.LoginWindow;
import net.vgc.game.GameType;
import net.vgc.game.player.GamePlayer;
import net.vgc.game.player.GamePlayerType;
import net.vgc.game.score.PlayerScore;
import net.vgc.network.NetworkSide;
import net.vgc.network.packet.AbstractPacketListener;
import net.vgc.player.GameProfile;
import net.vgc.player.Player;
import net.vgc.server.game.ServerGame;
import net.vgc.util.Util;

public class ClientPacketListener extends AbstractPacketListener {
	
	protected final Client client;
	
	public ClientPacketListener(Client client, NetworkSide networkSide) {
		super(networkSide);
		this.client = client;
	}
	
	public void handleClientLoggedIn(LoginType loginType, PlayerAccount account, boolean successful) {
		LoginWindow loginWindow = this.client.getLoginWindow();
		if (!this.client.isLoggedIn()) {
			if (successful) {
				switch (loginType) {
					case REGISTRATION: {
						LOGGER.info("Create successfully a new account");
						this.client.login(account);
						if (loginWindow != null) {
							loginWindow.handleLoggedIn(loginType);
						}
					} break;
					case USER_LOGIN: {
						LOGGER.debug("Successfully logged in");
						this.client.login(account);
						if (loginWindow != null) {
							loginWindow.handleLoggedIn(loginType);
						}
					} break;
					case GUEST_LOGIN: {
						LOGGER.debug("Successfully logged in as a guest");
						this.client.login(account);
						if (loginWindow != null) {
							loginWindow.handleLoggedIn(loginType);
						}
					} break;
					case UNKNOWN: {
						LOGGER.warn("Fail to log in");
					} break;
				}
			} else {
				LOGGER.warn("Fail to log in");
			}
		} else {
			LOGGER.warn("Fail to log in, since already logged in");
		}
	}
	
	public void handleClientLoggedOut(boolean successful) {
		LoginWindow loginWindow = this.client.getLoginWindow();
		if (successful) {
			LOGGER.info("Successfully logged out");
			this.client.logout();
			if (loginWindow != null) {
				loginWindow.handleLoggedOut();
			}
		} else {
			LOGGER.warn("Fail to log out");
		}
	}
	
	public void handleClientJoined(List<GameProfile> profiles) {
		for (GameProfile profile : profiles) {
			if (this.client.getAccount().getUUID().equals(profile.getUUID())) {
				this.client.setPlayer(new LocalPlayer(profile));
			} else {
				this.client.addRemotePlayer(new RemotePlayer(profile));
			}
		}
		this.client.setScreen(new LobbyScreen());
	}
	
	public void handlePlayerAdd(GameProfile profile) {;
		if (this.client.getAccount().getUUID().equals(profile.getUUID())) {
			if (this.client.getPlayer() == null) {
				LOGGER.warn("The local player is not set, that was not supposed to be");
				this.client.setPlayer(new LocalPlayer(profile));
			} else {
				LOGGER.warn("The local player is already set to {}, but there is another player with the same id {}", this.client.getPlayer().getProfile(), profile);
			}
		} else {
			this.client.addRemotePlayer(new RemotePlayer(profile));
		}
	}
	
	public void handlePlayerRemove(GameProfile profile) {
		if (this.client.getAccount().getUUID().equals(profile.getUUID())) {
			this.client.removePlayer();
		} else {
			this.client.removeRemotePlayer(new RemotePlayer(profile));
		}
	}
	
	public void handleSyncPermission(GameProfile profile) {
		for (AbstractClientPlayer player : this.client.getPlayers()) {
			if (player.getProfile().equals(profile)) {
				player.setAdmin(true);
				LOGGER.debug("Player {} is now a admin", player.getProfile().getName());
			} else {
				player.setAdmin(false);
			}
		}
		LOGGER.info("Sync admins to value {}, should not be larger than 1", this.client.getPlayers().stream().filter(AbstractClientPlayer::isAdmin).collect(Collectors.toList()).size());
	}
	
	public void handleSyncPlayerData(GameProfile profile, boolean playing, PlayerScore score) {
		AbstractClientPlayer player = this.client.getPlayer(profile);
		if (player != null) {
			player.setPlaying(playing);
			player.getScore().sync(score);
			LOGGER.info("Synchronize data from server to player {}", profile.getName());
		} else {
			LOGGER.warn("Fail to synchronize data from server to player {}, since the player does not exists", profile.getName());
		}
	}
	
	public <S extends ServerGame, C extends ClientGame> void handleStartGame(GameType<S, C> gameType, List<Cell<GameProfile, GamePlayerType, List<UUID>>> playerInfos) {
		if (this.client.getGame() == null) {
			C game = gameType.createClientGame(this.client, playerInfos);
			game.startGame();
			boolean flag = false;
			for (Player player : Util.mapList(game.getPlayers(), GamePlayer::getPlayer)) {
				player.setPlaying(true);
				if (this.client.getPlayer().getProfile().equals(player.getProfile())) {
					flag = true;
				}
			}
			if (flag) {
				gameType.openScreen(this.client, game);
				LOGGER.info("Start game {}", gameType.getInfoName());
				this.client.setGame(game);
			} else {
				LOGGER.warn("Fail to start game {}, since the local player is not in the player list of the game", gameType.getInfoName());
				this.client.setScreen(new LobbyScreen());
			}
		}
	}
	
	public void handleExitGame() {
		LOGGER.info("Exit the current game");
		if (this.client.getPlayer().isPlaying()) {
			this.client.getPlayer().setPlaying(false);
			this.client.getPlayer().getScore().reset();
		} else {
			LOGGER.info("Received a exit game packet, but the local player is not playing a game");
		}
		this.client.setScreen(new LobbyScreen());
	}
	
	public void handleStopGame() {;
		LOGGER.info("Stopping the current game");
		for (AbstractClientPlayer player : this.client.getPlayers()) {
			player.setPlaying(false);
			player.getScore().reset();
		}
		this.client.setScreen(new LobbyScreen());
	}
	
	public void handleServerClosed() {
		this.client.getServerHandler().close();
		this.client.removePlayer();
		this.client.setScreen(new MenuScreen());
	}
	
}
