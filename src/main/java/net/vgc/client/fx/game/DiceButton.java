package net.vgc.client.fx.game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import net.vgc.Constans;
import net.vgc.client.Client;
import net.vgc.client.fx.Box;
import net.vgc.client.game.dice.DiceRenderState;
import net.vgc.client.player.LocalPlayer;
import net.vgc.network.packet.server.game.dice.RollDiceRequestPacket;

public class DiceButton extends Button {
	
	protected final Client client;
	protected final double prefSize;
	protected int count = 0;
	
	public DiceButton(Client client, double prefSize) {
		this.client = client;
		this.prefSize = prefSize;
		this.init();
	}
	
	protected void init() {
		this.setPrefSize(this.prefSize, this.prefSize);
		this.updateState();
		if (!Constans.DEBUG) {
			this.setBackground(null);
		}
		this.setOnAction((event) -> {
			LocalPlayer player = this.client.getPlayer();
			if (player.isCurrent() && player.canRollDice()) {
				this.client.getServerHandler().send(new RollDiceRequestPacket(this.client.getPlayer().getProfile()));
			}
		});
	}
	
	protected void updateState() {
		ImageView image = DiceRenderState.fromCount(this.count).getImage(this.prefSize * 0.9, this.prefSize * 0.9);
		if (image != null) {
			this.setGraphic(new Box<>(image, Pos.CENTER));
		} else {
			this.setGraphic(null);
		}
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void setCount(int count) {
		this.count = count;
		this.updateState();
	}
	
}
