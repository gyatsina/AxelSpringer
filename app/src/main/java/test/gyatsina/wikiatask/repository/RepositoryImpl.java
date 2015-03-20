package test.gyatsina.wikiatask.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositoryImpl<T> implements Repository<T> {
    private List<T> items;


    public RepositoryImpl() {
        items = Collections.synchronizedList(new ArrayList<T>());
    }

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    public List<T> getAll() {
        return items;
    }

    @Override
    public void addAll(List<T> list) {
        items.addAll(list);
    }

    @Override
    public int size() {
        return items.size();
    }

    public T findByNumber(int id) {
        return items.get(id);
    }

    @Override
    public void removeAll() {
        items.clear();
    }
}
