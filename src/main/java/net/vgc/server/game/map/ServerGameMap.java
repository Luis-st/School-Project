package net.vgc.server.game.map;

import java.util.List;

import javax.annotation.Nullable;

import net.vgc.game.map.GameMap;
import net.vgc.game.map.field.GameFieldPos;
import net.vgc.game.map.field.GameFieldType;
import net.vgc.game.player.GamePlayer;
import net.vgc.game.player.GamePlayerType;
import net.vgc.game.player.field.GameFigure;
import net.vgc.network.packet.PacketHandler;
import net.vgc.network.packet.server.ServerPacket;
import net.vgc.server.game.map.field.ServerGameField;
import net.vgc.server.game.player.figure.ServerGameFigure;

public interface ServerGameMap extends GameMap, PacketHandler<ServerPacket> {
	
	@Override
	List<? extends ServerGameField> getFields();
	
	@Override
	ServerGameField getField(GameFigure figure);
	
	@Override
	ServerGameField getField(GameFieldType fieldType, @Nullable GamePlayerType playerType, GameFieldPos fieldPos);
	
	@Override
	ServerGameField getNextField(GameFigure figure, int count);
	
	@Override
	default ServerGameFigure getFigure(GamePlayer player, int figure) {
		return (ServerGameFigure) player.getFigure(figure);
	}
	
	@Override
	default void handlePacket(ServerPacket packet) {
		for (ServerGameField field : this.getFields()) {
			field.handlePacket(packet);
		}
	}
	
}
