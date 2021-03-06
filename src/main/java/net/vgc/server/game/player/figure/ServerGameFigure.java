package net.vgc.server.game.player.figure;

import java.util.UUID;

import net.vgc.game.map.field.GameFieldPos;
import net.vgc.game.player.GamePlayerType;
import net.vgc.game.player.field.GameFigure;
import net.vgc.server.game.player.ServerGamePlayer;

public interface ServerGameFigure extends GameFigure {
	
	@Override
	ServerGamePlayer getPlayer();
	
	@Override
	GamePlayerType getPlayerType();
	
	@Override
	int getCount();
	
	@Override
	UUID getUUID();
	
	@Override
	GameFieldPos getHomePos();
	
	@Override
	GameFieldPos getStartPos();
	
}
