package test.gyatsina.axelspringer.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gyatsina
 */

// This class is basic repository implementation
public class RepositoryImpl<T> implements Repository<T> {
    private List<T> items;
    private int maxBatch;


    public RepositoryImpl() {
        items = Collections.synchronizedList(new ArrayList<T>());
        maxBatch = 1;
    }

    @Override
    public void setLimit(int maxValue) {
        maxBatch = maxValue;
    }

    @Override
    public int getLimit() {
        return maxBatch;
    }

    @Override
    public void add(T item) {
        items.add(item);
    }

    @Override
    public void remove(T item) {
        items.remove(item);
    }

    @Override
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
