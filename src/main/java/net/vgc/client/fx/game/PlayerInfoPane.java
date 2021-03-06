package net.vgc.client.fx.game;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import net.vgc.Constans;
import net.vgc.client.fx.FxUtil;
import net.vgc.client.game.ClientGame;
import net.vgc.client.game.player.ClientGamePlayer;
import net.vgc.language.TranslationKey;
import net.vgc.util.Util;

public class PlayerInfoPane extends GridPane {
	
	protected final ClientGame game;
	protected final double separatorLength;
	protected final Text currentPlayerInfo;
	protected final PlayerScorePane scorePane;
	
	public PlayerInfoPane(ClientGame game, double separatorLength, PlayerScorePane.Type scoreType) {
		this.game = game;
		this.separatorLength = separatorLength;
		this.currentPlayerInfo = new Text(TranslationKey.createAndGet("screen.tic_tac_toe.no_current_player"));
		this.scorePane = new PlayerScorePane(this.game, scoreType);
		this.init();
	}
	
	protected void init() {
		this.setAlignment(Pos.CENTER);
		this.setVgap(10.0);
		this.setHgap(10.0);
		this.setGridLinesVisible(Constans.DEBUG);
		this.add(this.makePlayerInfoPane(), 0, 0);
		this.add(this.makeSeparator(), 0, 1);
		this.add(this.makeCurrentPlayerPane(), 0, 2);
		this.add(this.makeSeparator(), 0, 3);
		this.add(this.makePlayersPane(), 0, 4);
		this.add(this.makeSeparator(), 0, 5);
		this.add(this.scorePane, 0, 6);
	}
	
	protected Separator makeSeparator() {
		Separator separator = new Separator(Orientation.HORIZONTAL);
		separator.setPrefWidth(this.separatorLength);
		return separator;
	}
	
	protected String getName(ClientGamePlayer player) {
		return player.getPlayer().getProfile().getName();
	}
	
	protected GridPane makePlayerInfoPane() {
		GridPane pane = FxUtil.makeGrid(Pos.CENTER, 0.0, 5.0);
		pane.add(new Text(TranslationKey.createAndGet("screen.tic_tac_toe.player_info")), 0, 0);
		return pane;
	}
	
	protected GridPane makeCurrentPlayerPane() {
		GridPane pane = FxUtil.makeGrid(Pos.CENTER, 5.0, 5.0);
		pane.add(this.currentPlayerInfo, 0, 0);
		return pane;
	}
	
	protected GridPane makePlayersPane() {
		GridPane pane = FxUtil.makeGrid(Pos.CENTER, 5.0, 5.0);
		int i = 0;
		for (ClientGamePlayer player : this.game.getPlayers()) {
			pane.add(new Text(player.getPlayerType().getTranslation().getValue(this.getName(player))), 0, i++);
		}
		return pane;
	}
	
	public void update() {
		Util.runDelayed("DelayedPlayerInfoUpdate", 500, () -> {
			this.currentPlayerInfo.setText(TranslationKey.createAndGet("screen.tic_tac_toe.current_player", this.getName(this.game.getCurrentPlayer())));
			this.scorePane.update();
		});
	}
	
}
