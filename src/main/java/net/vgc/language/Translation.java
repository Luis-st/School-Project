package net.vgc.language;

import java.util.Objects;

public class Translation {
	
	protected final String key;
	protected final String value;
	
	public Translation(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public String getValue() {
		
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.key + ":" + this.value;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Translation translation) {
			return this.key.equals(translation.key) && Objects.equals(this.value, translation.value);
		}
		return false;
	}
	
}
