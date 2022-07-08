package net.vgc.util.function;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface QuadFunction<T, U, V, W, R> {
	
	R apply(T t, U u, V v, W w);
	
	default <S> QuadFunction<T, U, V, W, S> andThen(Function<? super R, ? extends S> after) {
		Objects.requireNonNull(after);
		return (T t, U u, V v, W w) -> {
			return after.apply(this.apply(t, u, v, w));
		};
	}
	
}
