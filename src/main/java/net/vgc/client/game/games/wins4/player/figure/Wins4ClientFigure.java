package net.vgc.client.game.games.wins4.player.figure;

import java.util.UUID;

import net.vgc.game.games.wins4.map.field.Wins4FieldPos;
import net.vgc.game.map.field.GameFieldPos;
import net.vgc.game.player.GamePlayer;
import net.vgc.game.player.figure.AbstractGameFigure;
import net.vgc.util.ToString;

public class Wins4ClientFigure extends AbstractGameFigure {
	
	public Wins4ClientFigure(GamePlayer player, int count, UUID uuid) {
		super(player, count, uuid);
	}

	@Override
	public GameFieldPos getHomePos() {
		return Wins4FieldPos.NO;
	}

	@Override
	public GameFieldPos getStartPos() {
		return Wins4FieldPos.NO;
	}
	
	@Override
	public String toString() {
		return ToString.toString(this, "player");
	}

}
