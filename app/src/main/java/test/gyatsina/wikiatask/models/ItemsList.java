package test.gyatsina.wikiatask.models;

import java.util.List;

/**
 * Created by gyatsina
 */
public class ItemsList<T>  {
    private List<T> items;
    private int next;
    private int total;
    private int batches;
    private int currentBatch;

    public ItemsList(List<T> items, int next, int total, int batches, int currentBatch) {
        this.items = items;
        this.next = next;
        this.total = total;
        this.batches = batches;
        this.currentBatch = currentBatch;
    }

    public List<T> getItems() {
        return items;
    }

    public int getNext() {
        return next;
    }

    public int getTotal() {
        return total;
    }

    public int getBatches() {
        return batches;
    }

    public int getCurrentBatch() {
        return currentBatch;
    }
}