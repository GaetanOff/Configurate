/*
 * Configurate
 * Copyright (C) zml and Configurate contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.spongepowered.configurate.util;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Provides a set of methods that produce unmodifiable copies of collections.
 */
public final class UnmodifiableCollections {

    private UnmodifiableCollections() {}

    /**
     * Creates an unmodifiable copy of the given {@link List} instance.
     *
     * @param original the list to be copied
     * @param <E> the type of every item in the entry
     * @return a unmodifiable copy of the given {@link List} instance
     */
    public static <E> List<E> copyOf(final List<E> original) {
        return List.copyOf(original);
    }

    /**
     * Creates an unmodifiable copy of the given {@link Set} instance.
     *
     * @param original the set to be copied
     * @param <E> the type of every item in the entry
     * @return a unmodifiable copy of the given {@link Set} instance
     */
    public static <E> Set<E> copyOf(final Set<E> original) {
        return Set.copyOf(original);
    }

    /**
     * Creates an unmodifiable copy of the given array as a list, preserving
     * order.
     *
     * @param original the array to be copied into a list
     * @param <E> the type of every item in the entry
     * @return a unmodifiable copy of the given array as a {@link List}
     *         instance
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <E> List<E> toList(final E... original) {
        return List.of(original);
    }

    /**
     * Creates an unmodifiable copy of the given array as a set.
     *
     * @param original the array to be copied into a set
     * @param <E> the type of every item in the entry
     * @return a unmodifiable copy of the given array as a {@link Set} instance
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <E> Set<E> toSet(final E... original) {
        return Set.of(original);
    }

    /**
     * Build an unmodifiable map.
     *
     * @param <K> key type
     * @param <V> value type
     * @param handler consumer that will populate the map wih keys
     * @return a new unmodifiable map
     */
    public static <K, V> Map<K, V> buildMap(final Consumer<Map<K, V>> handler) {
        final var builder = new LinkedHashMap<K, V>();
        requireNonNull(handler, "handler").accept(builder);
        return Collections.unmodifiableMap(builder);
    }

    /**
     * Creates an immutable instance of {@link Map.Entry}.
     *
     * @param key the key in the entry
     * @param value the value in the entry
     * @param <K> the key's type
     * @param <V> the value's type
     * @return the new map entry
     */
    public static <K, V> Map.Entry<K, V> immutableMapEntry(final K key, final V value) {
        return Map.entry(key, value);
    }

}
