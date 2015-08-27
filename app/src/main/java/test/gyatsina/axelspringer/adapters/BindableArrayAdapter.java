package test.gyatsina.axelspringer.adapters;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gyatsina
 */

public abstract class BindableArrayAdapter<T> extends BindableAdapter<T> {

    private List<T> list;

    public BindableArrayAdapter(Context context) {
        this(context, null);
    }

    public BindableArrayAdapter(Context context, List<T> list) {
        super(context);
        if (list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> newList) {
        if (list == null) {
            this.list = Collections.emptyList();
        } else {
            this.list = newList;
        }
        notifyDataSetChanged();
    }

    public void addItems(List<T> newList) {
        if (null == newList || newList.size() <= 0) {
            return;
        }

        list.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    public T removeItem(int position) {
        T removedItem = list.remove(position);
        notifyDataSetChanged();
        return removedItem;
    }
}
