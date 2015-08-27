package test.gyatsina.axelspringer.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import test.gyatsina.axelspringer.R;
import test.gyatsina.axelspringer.WikiaApplication;
import test.gyatsina.axelspringer.adapters.GamingItemsAdapter;
import test.gyatsina.axelspringer.api.RetrofitWikiaApi;
import test.gyatsina.axelspringer.event.GamingItemsChangedEvent;
import test.gyatsina.axelspringer.models.ComplexGameItem;
import test.gyatsina.axelspringer.repository.GameItemsRequestManager;
import test.gyatsina.axelspringer.repository.Repository;

public class GamingItemsListFragment extends BaseFragment {
    public static final String CLASS_TAG = "GamingItemsListFragment";
    private PullToRefreshListView refreshableListView;
    private GamingItemsAdapter gamingItemsAdapter;
    private GameItemsRequestManager gamingRequestManager;
    private static final String LIST_STATE = "listState";
    private static final String PARCEL_STATE = "parcelState";
    private int mListState = 0;
    private Parcelable parcelState;
    private ListView gamingItemsListView;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int motionPosition = gamingItemsListView.getLastVisiblePosition();
        parcelState = gamingItemsListView.onSaveInstanceState();
        outState.putInt(LIST_STATE, motionPosition);
        outState.putParcelable(PARCEL_STATE, parcelState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            mListState = savedInstanceState.getInt(LIST_STATE);
            gamingItemsListView.setSelection(mListState);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoadingProgressDialog();
        updateGamingList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.list_fragment, container, false);
        Repository<ComplexGameItem> gamingRepository = WikiaApplication.from(getActivity()).getGamingItemsRepository();
        gamingRequestManager = new GameItemsRequestManager(getEventBus(), gamingRepository, RetrofitWikiaApi.getWikiaApi());

        refreshableListView = (PullToRefreshListView) fragmentView.findViewById(R.id.main_listview);
        gamingItemsListView = refreshableListView.getRefreshableView();
        gamingItemsListView.setDividerHeight(1);
        gamingItemsAdapter = new GamingItemsAdapter(getActivity());
        gamingItemsListView.setAdapter(gamingItemsAdapter);

        refreshableListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshableListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                showLoadingProgressDialog();
                gamingRequestManager.cleanGamingListRepository();
                updateGamingList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                gamingRequestManager.getGamingListNextItems();
            }
        });

        return fragmentView;
    }

    private void updateGamingList() {
        List<ComplexGameItem> gamingList = gamingRequestManager.getGamingList();
        showGamingList(gamingList);
    }

    @SuppressWarnings("unused") // EventBus
    public void onEventMainThread(GamingItemsChangedEvent event) {
        updateGamingList();
    }

    private void showGamingList(List<ComplexGameItem> gamingList) {
        gamingItemsAdapter.setList(gamingList);
        if (!gamingList.isEmpty()) {
            dismissProgressDialog();
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshableListView.onRefreshComplete();
            }
        }, 100);

    }
}
