package net.vgc.client.game.games.wins4;

import java.util.List;

import net.vgc.client.Client;
import net.vgc.client.game.AbstractClientGame;
import net.vgc.client.game.games.ttt.map.TTTClientMap;
import net.vgc.client.game.games.ttt.player.TTTClientPlayer;
import net.vgc.game.GameType;
import net.vgc.game.GameTypes;
import net.vgc.game.player.GamePlayerInfo;
import net.vgc.server.game.games.wins4.Wins4ServerGame;

public class Wins4ClientGame extends AbstractClientGame {
	
	public Wins4ClientGame(Client client, List<GamePlayerInfo> playerInfos) {
		super(client, TTTClientMap::new, playerInfos, TTTClientPlayer::new);
	}

	@Override
	public GameType<Wins4ServerGame, Wins4ClientGame> getType() {
		return GameTypes.WINS_4;
	}
	
	@Override
	public String toString() {
		return "Win4ClientGame";
	}
	
}