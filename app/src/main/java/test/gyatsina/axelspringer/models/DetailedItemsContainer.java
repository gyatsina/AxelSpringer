package test.gyatsina.axelspringer.models;

import java.util.HashMap;

/**
 * Created by gyatsina
 */
public class DetailedItemsContainer<T>  {
    private HashMap<Integer, DetailedItemById> items;

    public DetailedItemsContainer(HashMap<Integer, DetailedItemById> items) {
        this.items = items;
    }

    public HashMap<Integer, DetailedItemById> getItems() {
        return items;
    }
}