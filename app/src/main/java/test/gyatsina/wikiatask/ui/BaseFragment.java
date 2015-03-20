package test.gyatsina.wikiatask.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;



import de.greenrobot.event.EventBus;
import test.gyatsina.wikiatask.R;
import test.gyatsina.wikiatask.WikiaApplication;
import test.gyatsina.wikiatask.event.ErrorEvent;
import test.gyatsina.wikiatask.utils.Toasts;

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

    public void hideViewElement(View view) {
        view.setVisibility(View.GONE);
    }

    protected void showProgressDialog(final String title, final String message, final boolean cancelable) {
        getAppActivity().showProgressDialog(title, message, cancelable);
    }

    protected void showLoadingProgressDialog() {
//        dismissProgressDialog();
        getAppActivity().showProgressDialog(getResources().getString(R.string.progress), getResources().getString(R.string.progress_loading), true);
    }

    protected void showProgressDialog(String title, final String message) {
        getAppActivity().showProgressDialog(title, message);
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
