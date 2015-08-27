package test.gyatsina.axelspringer.adapters;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by gyatsina
 */
public final class ViewHolder {

  private ViewHolder() {
    // helper class with static methods
  }

  @SuppressWarnings("unchecked")
  public static <T extends View> T get(View view, int id) {

    SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
    if (viewHolder == null) {
      viewHolder = new SparseArray<View>();
      view.setTag(viewHolder);
    }
    View childView = viewHolder.get(id);
    if (childView == null) {
      childView = view.findViewById(id);
      viewHolder.put(id, childView);
    }
    return (T) childView;
  }
}
