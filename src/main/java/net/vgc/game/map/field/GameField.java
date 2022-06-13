package net.vgc.game.map.field;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.vgc.game.player.field.GameFigure;
import net.vgc.player.GameProfile;
import net.vgc.util.Util;

public interface GameField {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	GameFieldType getFieldType();
	
	GameFieldPos getFieldPos();
	
	boolean isHome();
	
	boolean isStart();
	
	boolean isStartFor(GameFigure figure);
	
	boolean isWin();
	
	default boolean isSpecial() {
		return this.isHome() || this.isStart() || this.isWin();
	}
	
	@Nullable
	GameFigure getFigure();
	
	void setFigure(@Nullable GameFigure figure);
	
	default void clear() {
		this.setFigure(null);
	}
	
	default boolean isEmpty() {
		return this.getFigure() == null;
	}
	
	default GameFieldInfo getFieldInfo() {
		if (this.isEmpty()) {
			return new GameFieldInfo(this.getFieldPos(), GameProfile.EMPTY, -1, Util.EMPTY_UUID);
		}
		GameFigure figure = this.getFigure();
		return new GameFieldInfo(this.getFieldPos(), figure.getPlayer().getPlayer().getProfile(), figure.getCount(), figure.getUUID());
	}
	
}
