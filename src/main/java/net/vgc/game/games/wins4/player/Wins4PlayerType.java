package net.vgc.game.games.wins4.player;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import javafx.scene.image.ImageView;
import net.vgc.client.fx.FxUtil;
import net.vgc.game.player.GamePlayerType;
import net.vgc.language.TranslationKey;

public enum Wins4PlayerType implements GamePlayerType {
	
	YELLOW("yellow", 0, new TranslationKey("screen.win4.yellow_player"), "textures/wins4/figure/figure_yellow"),
	RED("red", 1, new TranslationKey("screen.win4.red_player"), "textures/wins4/figure/figure_red"),
	NO("no", 2, new TranslationKey("screen.tic_tac_toe.no_player"), null);
	
	private final String name;
	private final int id;
	private final TranslationKey translation;
	private final String path;
	
	private Wins4PlayerType(String name, int id, TranslationKey translation, String path) {
		this.name = name;
		this.id = id;
		this.translation = translation;
		this.path = path;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public TranslationKey getTranslation() {
		return this.translation;
	}

	@Override
	public List<Wins4PlayerType> getOpponents() {
		if (this == NO) {
			return Lists.newArrayList(NO);
		}
		return this == YELLOW ? Lists.newArrayList(RED) : Lists.newArrayList(YELLOW);
	}
	
	@Override
	public Enum<Wins4PlayerType> getDefault() {
		return NO;
	}
	
	@Nullable
	public ImageView getImage(double width, double height) {
		if (this.path == null) {
			return null;
		}
		return FxUtil.makeImageView(this.path + ".png", width, height);
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
