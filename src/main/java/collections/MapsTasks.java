package collections;

import java.util.*;

public class MapsTasks {

    static class MyHashMap<K, V> implements Map<K, V> {
        private static class Pair<K, V> {
            final K k;
            final V v;

            private Pair(K k, V v) {
                this.k = k;
                this.v = v;
            }
        }

        private final Pair<K, V>[] kv;
        private final int capacity;
        private int numEntries = 0;

        private static final int NO_OBJECT = -1;

        /**
         * No auto-resizing, put() throws IllegalStateException if trying to add a new key
         * when full to capacity
         */
        @SuppressWarnings("unchecked")
        public MyHashMap(int capacity) {
            kv = new Pair[capacity];
            this.capacity = capacity;
        }

        private int findKeyLocation(Object key, boolean findPlacementPoint) {
            int initialPos = key.hashCode();
            for (int offset = 0; offset < capacity; offset++) {
                int i = (initialPos + offset) % capacity;
                if (kv[i] == null) return findPlacementPoint ? i : NO_OBJECT;
                if (kv[i].k.equals(key)) return i;
            }
            return NO_OBJECT;
        }

        private V put(Pair<K, V> p) {
            final int keyLocation = findKeyLocation(p.k, true);
            if (keyLocation == NO_OBJECT) throw new IllegalStateException();

            V presentValue = null;
            if (kv[keyLocation] == null)
                numEntries++;
            else
                presentValue = kv[keyLocation].v;
            kv[keyLocation] = p;
            return presentValue;
        }

        @Override
        public int size() {
            return numEntries;
        }

        @Override
        public boolean isEmpty() {
            return (numEntries == 0);
        }

        @Override
        public boolean containsKey(Object key) {
            return findKeyLocation(key, false) != NO_OBJECT;
        }

        @Override
        public boolean containsValue(Object value) {
            for (int i = 0; i < capacity; i++)
                if (kv[i] != null && value.equals(kv[i].v)) return true;

            return false;
        }

        @Override
        public V get(Object key) {
            final int keyLocation = findKeyLocation(key, false);
            return keyLocation == NO_OBJECT ? null : kv[keyLocation].v;
        }

        @Override
        public V put(K key, V value) {
            return put(new Pair<>(key, value));
        }

        @Override
        public V remove(Object key) {
            final int keyLocation = findKeyLocation(key, false);
            if (keyLocation == NO_OBJECT) return null;
            V presentValue = kv[keyLocation].v;
            kv[keyLocation] = null;
            numEntries--;

            List<Pair<K, V>> relocations = new ArrayList<>();
            for (int offset = 1; true; offset++) {
                int i = (keyLocation + offset) % capacity;
                if (kv[i] == null) break;
                relocations.add(kv[i]);
                kv[i] = null;
                numEntries--;
            }
            relocations.forEach(this::put);
            return presentValue;
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> m) {
            m.entrySet().forEach(entry -> put(entry.getKey(), entry.getValue()));
        }

        @Override
        public void clear() {
            Arrays.fill(kv, null);
            numEntries = 0;
        }

        /**
         * Not supported
         */
        @Override
        public Set<K> keySet() {
            return Collections.emptySet();
        }

        /**
         * Not supported
         */
        @Override
        public Collection<V> values() {
            return Collections.emptyList();
        }

        /**
         * Not supported
         */
        @Override
        public Set<Entry<K, V>> entrySet() {
            return Collections.emptySet();
        }
    }
}
