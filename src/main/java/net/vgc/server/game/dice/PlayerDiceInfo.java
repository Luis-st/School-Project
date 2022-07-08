package net.vgc.server.game.dice;

import net.vgc.game.player.GamePlayer;

public class PlayerDiceInfo {
	
	protected final GamePlayer player;
	protected final int count;
	
	public PlayerDiceInfo(GamePlayer player, int count) {
		this.player = player;
		this.count = count;
	}
	
	public GamePlayer getPlayer() {
		return this.player;
	}
	
	public int getCount() {
		return this.count;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof PlayerDiceInfo diceInfo) {
			if (!this.player.equals(diceInfo.player)) {
				return false;
			} else {
				return this.count == diceInfo.count;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("PlayerDiceInfo{");
		builder.append("player=").append(this.player).append(",");
		builder.append("count=").append(this.count).append("}");
		return builder.toString();
	}
	
}
