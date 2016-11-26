package collections;

import java.util.*;

class MapsTasks {
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
        MyHashMap(int capacity) {
            kv = new Pair[capacity];
            this.capacity = capacity;
        }

        private int findKeyLocation(Object key, boolean findPlacementPoint) {
            int initialPos = key.hashCode() % capacity + capacity;      // must be non-negative for correct modulus division
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

        @Override
        public Set<K> keySet() {
            return new AbstractSet<K>() {
                final Set<Entry<K, V>> es = entrySet();

                @Override
                public Iterator<K> iterator() {
                    return new Iterator<K>() {
                        final Iterator<Entry<K, V>> it = es.iterator();

                        @Override
                        public boolean hasNext() {
                            return it.hasNext();
                        }

                        @Override
                        public K next() {
                            return it.next().getKey();
                        }

                        @Override
                        public void remove() {
                            it.remove();
                        }
                    };
                }

                @Override
                public int size() {
                    return es.size();
                }
            };
        }

        @Override
        public Collection<V> values() {
            return new AbstractCollection<V>() {
                final Set<Entry<K, V>> es = entrySet();

                @Override
                public Iterator<V> iterator() {
                    return new Iterator<V>() {
                        final Iterator<Entry<K, V>> it = es.iterator();

                        @Override
                        public boolean hasNext() {
                            return it.hasNext();
                        }

                        @Override
                        public V next() {
                            return it.next().getValue();
                        }

                        @Override
                        public void remove() {
                            it.remove();
                        }
                    };
                }

                @Override
                public int size() {
                    return es.size();
                }
            };
        }

        @Override
        public Set<Entry<K, V>> entrySet() {
            return new AbstractSet<Entry<K, V>>() {
                @Override
                public Iterator<Entry<K, V>> iterator() {

                    return new Iterator<Entry<K, V>>() {
                        int nextI = findNextNonNull(0);

                        private int findNextNonNull(int start) {
                            for (int i = start; i < capacity; i++) {
                                if (kv[i] != null) return i;
                            }
                            return NO_OBJECT;
                        }

                        @Override
                        public boolean hasNext() {
                            return nextI != NO_OBJECT;
                        }

                        @Override
                        public Entry<K, V> next() {
                            if (!hasNext()) throw new NoSuchElementException();
                            final int i = nextI;
                            nextI = findNextNonNull(this.nextI + 1);

                            return new Entry<K, V>() {
                                @Override
                                public K getKey() {
                                    return kv[i].k;
                                }

                                @Override
                                public V getValue() {
                                    return kv[i].v;
                                }

                                @Override
                                public V setValue(V value) {
                                    V oldValue = getValue();
                                    kv[i] = new Pair<>(getKey(), value);
                                    return oldValue;
                                }
                            };
                        }
                    };
                }

                @Override
                public int size() {
                    return numEntries;
                }
            };
        }
    }

    static class SubsetSolver {
        private final Integer[] hashTable;

        SubsetSolver(Set<Integer> setT) {
            this.hashTable = new Integer[setT.size() * 2];
            for (Integer t : setT) {
                int pos = t % hashTable.length + hashTable.length;
                int i;
                do {
                    i = pos++ % hashTable.length;
                } while (hashTable[i] != null);

                hashTable[i] = t;
            }
        }

        boolean containsSet(Set<Integer> setS) {
            for (Integer s : setS) {
                int pos = s % hashTable.length + hashTable.length;
                int i;
                do {
                    i = pos++ % hashTable.length;
                    if (hashTable[i] == null) return false;
                } while (!hashTable[i].equals(s));
            }
            return true;
        }
    }
}
