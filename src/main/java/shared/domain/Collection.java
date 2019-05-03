package shared.domain;

import java.util.*;

public abstract class Collection<T> implements List<T> {
    protected ArrayList<T> items;

    public Collection() {
        this.items = new ArrayList<>();
    }

    public Collection(T[] items) {
        this.items = new ArrayList<>(Arrays.asList(items));
    }

    public Collection(List<T> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public int size() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.items.contains(o);
    }

    @Override
    public Iterator iterator() {
        return this.items.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.items.toArray();
    }

    @Override
    public boolean add(Object o) {
        return this.items.add((T) o);
    }

    @Override
    public boolean remove(Object o) {
        return this.items.remove(o);
    }

    @Override
    public boolean addAll(int index, java.util.Collection<? extends T> c) {
        return items.addAll(index, c);
    }

    @Override
    public boolean addAll(java.util.Collection c) {
        return this.items.addAll(c);
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    @Override
    public T get(int index) {
        return this.items.get(index);
    }

    @Override
    public T set(int index, T element) {
        return this.items.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        this.items.add(index, element);
    }

    @Override
    public T remove(int index) {
        return this.items.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.items.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.items.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return this.items.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return this.items.subList(fromIndex, toIndex);
    }

    @Override
    public boolean retainAll(java.util.Collection c) {
        return this.items.retainAll(c);
    }

    @Override
    public boolean removeAll(java.util.Collection c) {
        return this.items.removeAll(c);
    }

    @Override
    public boolean containsAll(java.util.Collection c) {
        return this.items.containsAll(c);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return this.items.toArray((T[]) a);
    }
}
