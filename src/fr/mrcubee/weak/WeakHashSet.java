package fr.mrcubee.weak;

import java.util.*;

public class WeakHashSet<T> extends AbstractSet<T> implements Set<T>, Cloneable
{
    private final transient WeakHashMap<T, Object> map;
    private static final Object PRESENT = new Object();

    private WeakHashSet(int initialCapacity, float loadFactor, boolean dummy) {
        this.map = new WeakHashMap<T, Object>(initialCapacity, loadFactor);
    }

    public WeakHashSet() {
        this.map = new WeakHashMap<T, Object>();
    }

    public WeakHashSet(Collection<? extends T> collection) {
        this.map = new WeakHashMap<T, Object>(Math.max((int)((float)collection.size() / 0.75F) + 1, 16));
        this.addAll(collection);
    }

    public WeakHashSet(int initialCapacity, float loadFactor) {
        this.map = new WeakHashMap<T, Object>(initialCapacity, loadFactor);
    }

    public WeakHashSet(int initialCapacity) {
        this.map = new WeakHashMap(initialCapacity);
    }

    public Iterator<T> iterator() {
        return this.map.keySet().iterator();
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean contains(Object object) {
        return this.map.containsKey(object);
    }

    public boolean add(T element) {
        return this.map.put(element, PRESENT) == null;
    }

    public boolean remove(Object object) {
        return this.map.remove(object) == PRESENT;
    }

    public void clear() {
        this.map.clear();
    }

    @Override
    public Object clone() {
        WeakHashSet<T> result = new WeakHashSet<T>();

        result.addAll(this);
        return result;
    }

    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException();
    }
}
