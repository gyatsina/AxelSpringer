package test.gyatsina.wikiatask.repository;

import java.util.List;

public interface Repository<T> {
    void add(T item);

    void remove(T item);

    List<T> getAll();

    void addAll(List<T> list);

    void removeAll();

    int size();

    T findByNumber(int id);
}
