package net.vgc.network.packet.account;

import net.vgc.account.LoginType;
import net.vgc.account.network.AccountServerPacketListener;
import net.vgc.network.buffer.FriendlyByteBuffer;

public class ClientLoginPacket implements AccountPacket {
	
	protected final LoginType loginType;
	protected final String name;
	protected final String password;
	
	public ClientLoginPacket(LoginType loginType, String name, String password) {
		this.loginType = loginType;
		this.name = name;
		this.password = password;
	}
	
	public ClientLoginPacket(FriendlyByteBuffer buffer) {
		this.loginType = buffer.readEnum(LoginType.class);
		this.name = buffer.readString();
		this.password = buffer.readString();
	}
	
	@Override
	public void encode(FriendlyByteBuffer buffer) {
		buffer.writeEnum(this.loginType);
		buffer.writeString(this.name);
		buffer.writeString(this.password);
	}

	@Override
	public void handle(AccountServerPacketListener listener) {
		listener.handleClientLogin(this.loginType, this.name, this.password);
	}
	
	public LoginType getLoginType() {
		return this.loginType;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
}
