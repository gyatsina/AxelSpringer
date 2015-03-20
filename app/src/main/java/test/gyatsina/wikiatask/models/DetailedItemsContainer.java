package test.gyatsina.wikiatask.models;

import java.util.HashMap;
import java.util.List;

public class DetailedItemsContainer<T>  {
    private HashMap<Integer, DetailedItemById> items;

    public DetailedItemsContainer(HashMap<Integer, DetailedItemById> items) {
        this.items = items;
    }

    public HashMap<Integer, DetailedItemById> getItems() {
        return items;
    }
}