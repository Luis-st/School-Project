package net.vgc.language;

import java.nio.file.Path;

import net.vgc.network.Network;

public class Language {
	
	protected final String name;
	protected final String fileName;
	
	public Language(String name, String fileName) {
		this.name = name;
		this.fileName = fileName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public Path getPath() {
		return Network.INSTANCE.getResourceDirectory().resolve("lang/" + this.fileName + ".json");
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Language language) {
			return this.name.equals(language.name) && this.fileName.equals(language.fileName);
		}
		return false;
	}
	
}
