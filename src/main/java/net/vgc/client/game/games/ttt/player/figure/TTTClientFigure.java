package net.vgc.client.game.games.ttt.player.figure;

import java.util.UUID;

import net.vgc.client.game.games.ttt.player.TTTClientPlayer;
import net.vgc.client.game.player.figure.ClientGameFigure;
import net.vgc.game.games.ttt.map.field.TTTFieldPos;
import net.vgc.game.games.ttt.player.TTTPlayerType;

public class TTTClientFigure implements ClientGameFigure {
	
	protected final TTTClientPlayer player;
	protected final int count;
	protected final UUID uuid;
	
	public TTTClientFigure(TTTClientPlayer player, int count, UUID uuid) {
		this.player = player;
		this.count = count;
		this.uuid = uuid;
	}
	
	@Override
	public TTTClientPlayer getPlayer() {
		return this.player;
	}

	@Override
	public TTTPlayerType getPlayerType() {
		return this.player.getPlayerType();
	}

	@Override
	public int getCount() {
		return this.count;
	}

	@Override
	public UUID getUUID() {
		return this.uuid;
	}

	@Override
	public TTTFieldPos getHomePos() {
		return TTTFieldPos.NO;
	}

	@Override
	public TTTFieldPos getStartPos() {
		return TTTFieldPos.NO;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof TTTClientFigure figure) {
			if (!this.player.equals(figure.player)) {
				return false;
			} else if (this.count != figure.count) {
				return false;
			} else {
				return this.uuid.equals(figure.uuid);
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("TTTClientFigure{");
		builder.append("count=").append(this.count).append(",");
		builder.append("uuid=").append(this.uuid).append("}");
		return builder.toString();
	}
	
}
