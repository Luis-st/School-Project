package net.vgc.client.game.map.field;

import javax.annotation.Nullable;

import javafx.scene.image.ImageView;
import net.vgc.client.game.player.figure.ClientGameFigure;
import net.vgc.game.GameResult;
import net.vgc.game.map.field.GameField;
import net.vgc.game.map.field.GameFieldPos;
import net.vgc.game.map.field.GameFieldType;
import net.vgc.game.player.field.GameFigure;

public interface ClientGameField extends GameField {
	
	void init();
	
	@Override
	GameFieldType getFieldType();
	
	@Override
	GameFieldPos getFieldPos();
	
	@Override
	boolean isHome();
	
	@Override
	boolean isStart();
	
	@Override
	boolean isStartFor(GameFigure figure);
	
	@Override
	boolean isWin();
	
	@Override
	ClientGameFigure getFigure();
	
	@Override
	void setFigure(GameFigure figure);
	
	@Nullable
	ImageView getFieldBackground();
	
	default boolean canSelectField() {
		return !this.isEmpty();
	}
	
	boolean isShadowed();
	
	void setShadowed(boolean shadowed);
	
	default void resetShadow() {
		this.setShadowed(false);
	}
	
	GameResult getResult();
	
	void setResult(GameResult result);
	
	void updateFieldGraphic();
	
}
