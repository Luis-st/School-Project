package net.vgc.network.packet.server.game;

import net.vgc.game.map.field.GameFieldPos;
import net.vgc.game.map.field.GameFieldType;
import net.vgc.network.buffer.FriendlyByteBuffer;
import net.vgc.network.packet.server.ServerPacket;
import net.vgc.player.GameProfile;
import net.vgc.server.network.ServerPacketListener;

public class SelectGameFieldPacket implements ServerPacket {
	
	protected final GameProfile profile;
	protected final GameFieldType fieldType;
	protected final GameFieldPos fieldPos;
	
	public SelectGameFieldPacket(GameProfile profile, GameFieldType fieldType, GameFieldPos fieldPos) {
		this.profile = profile;
		this.fieldType = fieldType;
		this.fieldPos = fieldPos;
	}
	
	public SelectGameFieldPacket(FriendlyByteBuffer buffer) {
		this.profile = buffer.read(GameProfile.class);
		this.fieldType = buffer.readEnumInterface();
		this.fieldPos = buffer.readInterface();
	}
	
	@Override
	public void encode(FriendlyByteBuffer buffer) {
		buffer.write(this.profile);
		buffer.writeEnumInterface(this.fieldType);
		buffer.writeInterface(this.fieldPos);
	}

	@Override
	public void handle(ServerPacketListener listener) {
		
	}
	
	public GameProfile getProfile() {
		return this.profile;
	}
	
	public GameFieldType getFieldType() {
		return this.fieldType;
	}
	
	public GameFieldPos getFieldPos() {
		return this.fieldPos;
	}

}
