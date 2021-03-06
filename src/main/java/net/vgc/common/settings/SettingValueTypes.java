package net.vgc.common.settings;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.vgc.language.Language;
import net.vgc.language.Languages;

public class SettingValueTypes {
	
	protected static final Map<Integer, SettingValueType<?>> INT_TO_TYPE = Maps.newHashMap();
	
	public static final SettingValueType<Integer> INT = register(0, new SettingValueType<Integer>() {
		@Override
		public Integer getValue(String string) {
			return Integer.valueOf(string);
		}
		
		@Override
		public String toString(Integer value) {
			return String.valueOf(value);
		}
		
		@Override
		public String toString() {
			return "SettingValueType{type=Integer}";
		}
	});
	public static final SettingValueType<Double> DOUBLE = register(0, new SettingValueType<Double>() {
		@Override
		public Double getValue(String string) {
			return Double.valueOf(string);
		}
		
		@Override
		public String toString(Double value) {
			return String.valueOf(value);
		}
		
		@Override
		public String toString() {
			return "SettingValueType{type=Double}";
		}
	});
	public static final SettingValueType<String> STRING = register(2, new SettingValueType<String>() {
		@Override
		public String getValue(String string) {
			return string;
		}
		
		@Override
		public String toString(String value) {
			return value;
		}
		
		@Override
		public String toString() {
			return "SettingValueType{type=String}";
		}
	});
	public static final SettingValueType<Language> LANGUAGE = register(3, new SettingValueType<Language>() {
		@Override
		public Language getValue(String string) {
			Language language = Languages.fromName(string);
			if (language != null) {
				return language;
			}
			return Languages.EN_US;
		}
		
		@Override
		public String toString(Language value) {
			return value.getName();
		}
		
		@Override
		public String toString() {
			return "SettingValueType{type=Language}";
		}
	});
	
	@Nullable
	public static SettingValueType<?> fromId(int id) {
		return INT_TO_TYPE.get(id);
	}
	
	public static int getId(SettingValueType<?> valueType) {
		for (Entry<Integer, SettingValueType<?>> entry : INT_TO_TYPE.entrySet()) {
			if (entry.getValue() == valueType) {
				return entry.getKey();
			}
		}
		return -1;
	}
	
	protected static <T> SettingValueType<T> register(int id, SettingValueType<T> valueType) {
		INT_TO_TYPE.put(id, valueType);
		return valueType;
	}
	
}
