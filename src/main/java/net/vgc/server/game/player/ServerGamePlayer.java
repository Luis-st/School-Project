package net.vgc.server.game.player;

import java.util.List;

import net.vgc.game.map.field.GameFieldPos;
import net.vgc.game.player.GamePlayer;
import net.vgc.game.player.GamePlayerType;
import net.vgc.server.game.ServerGame;
import net.vgc.server.game.map.ServerGameMap;
import net.vgc.server.game.player.figure.ServerGameFigure;
import net.vgc.server.player.ServerPlayer;

public interface ServerGamePlayer extends GamePlayer {
	
	@Override
	ServerGame getGame();
	
	@Override
	ServerPlayer getPlayer();
	
	@Override
	GamePlayerType getPlayerType();
	
	@Override
	default ServerGameMap getMap() {
		return this.getGame().getMap();
	}
	
	@Override
	List<? extends ServerGameFigure> getFigures();
	
	@Override
	ServerGameFigure getFigure(int figure);
	
	@Override
	List<? extends GameFieldPos> getWinPoses();
	
	@Override
	int getRollCount();
	
	@Override
	void setRollCount(int rollCount);
	
}
