package net.vgc.client.screen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.layout.Pane;
import net.vgc.Main;
import net.vgc.client.Client;
import net.vgc.client.fx.InputHandler;
import net.vgc.client.fx.ScreenScene;
import net.vgc.client.fx.Showable;
import net.vgc.language.TranslationKey;
import net.vgc.network.packet.PacketHandler;
import net.vgc.network.packet.client.ClientPacket;
import net.vgc.util.Tickable;

public abstract class Screen implements Showable, Tickable, InputHandler, PacketHandler<ClientPacket> {
	
	protected static final Logger LOGGER = LogManager.getLogger(Main.class);
	
	protected final Client client;
	public String title = TranslationKey.createAndGet("client.constans.name");
	public int width = 600;
	public int height = 600;
	
	public Screen() {
		this.client = Client.getInstance();
	}
	
	public void init() {
		
	}
	
	@Override
	public void tick() {
		
	}
	
	@Override
	public void handlePacket(ClientPacket clientPacket) {
		
	}
	
	protected void showScreen(Screen screen) {
		this.client.setScreen(screen);
	}
	
	protected void reapplyScreen() {
		this.client.setScreen(this);
	}
	
	protected abstract Pane createPane();
	
	public final ScreenScene show() {
		return new ScreenScene(this.createPane(), this.width, this.height, this);
	}
	
}
