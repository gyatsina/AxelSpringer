package test.gyatsina.axelspringer.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;


import de.greenrobot.event.EventBus;
import test.gyatsina.axelspringer.R;
import test.gyatsina.axelspringer.WikiaApplication;
import test.gyatsina.axelspringer.event.ErrorEvent;
import test.gyatsina.axelspringer.utils.Toasts;

/**
 * Created by gyatsina
 */
public class BaseFragment extends Fragment {
    protected static final String CLASS_TAG = "BaseFragment";
    protected static final String CATEGORY_ID_KEY = "categoryIdKey";
    protected static final String SEARCH_WHAT = "what";
    protected static final String SEARCH_WHERE = "where";
    private EventBus eventBus;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        eventBus = WikiaApplication.from(getActivity()).getEventBus();
    }

    @Override
    public void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @SuppressWarnings("unused") // EventBus
    public void onEventMainThread(ErrorEvent errorEvent) {
        if (getActivity() != null) {
            dismissProgressDialog();
            Toasts.showShort(getActivity(), errorEvent.getErrorMessage());
        }
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    protected void showLoadingProgressDialog() {
//        dismissProgressDialog();
        getAppActivity().showProgressDialog(getResources().getString(R.string.progress), getResources().getString(R.string.progress_loading), true);
    }

    protected void dismissProgressDialog() {
        BaseActivity activity = getAppActivity();
        if (activity != null) {
            activity.dismissProgressDialog();
        }
    }

    private BaseActivity getAppActivity() {
        return (BaseActivity) getActivity();
    }
}
