package net.vgc.data.tag;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import com.google.common.collect.Lists;

import net.vgc.data.tag.tags.CompoundTag;
import net.vgc.data.tag.tags.collection.ListTag;
import net.vgc.language.TranslationKey;

public class TagUtil {
	
	public static CompoundTag writeUUID(UUID uuid) {
		CompoundTag tag = new CompoundTag();
		tag.putLong("mostBits", uuid.getMostSignificantBits());
		tag.putLong("leastBits", uuid.getLeastSignificantBits());
		return tag;
	}
	
	public static UUID readUUID(CompoundTag tag) {
		return new UUID(tag.getLong("mostBits"), tag.getLong("leastBits"));
	}
	
	public static CompoundTag writeTranslationKey(TranslationKey key) {
		CompoundTag tag = new CompoundTag();
		tag.putString("key", key.getKey());
		return tag;
	}
	
	public static TranslationKey readTranslationKey(CompoundTag tag) {
		return new TranslationKey(tag.getString("key"));
	}
	
	public static <E, T extends Tag> ListTag writeList(List<E> list, Function<E, T> function) {
		ListTag listTag = new ListTag();
		for (E element : list) {
			listTag.add(function.apply(element));
		}
		return listTag;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Tag, E> List<E> readList(ListTag listTag, Function<T, E> function) {
		List<E> list = Lists.newArrayList();
		for (Tag tag : listTag) {
			list.add(function.apply((T) tag));
		}
		return list;
	}
	
}
