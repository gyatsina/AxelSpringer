package test.gyatsina.wikiatask.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import test.gyatsina.wikiatask.R;
import test.gyatsina.wikiatask.WikiaApplication;
import test.gyatsina.wikiatask.adapters.GamingItemsAdapter;
import test.gyatsina.wikiatask.api.RetrofitWikiaApi;
import test.gyatsina.wikiatask.event.GamingItemsChangedEvent;
import test.gyatsina.wikiatask.models.GamingItemInList;
import test.gyatsina.wikiatask.repository.GameItemsRequestManager;
import test.gyatsina.wikiatask.repository.Repository;

public class GamingItemsListFragment extends BaseFragment {
    private static final String CLASS_TAG = "GamingItemsListFragment";
    private PullToRefreshListView refreshableListView;
    private GamingItemsAdapter gamingItemsAdapter;
    private GameItemsRequestManager gamingRequestManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.list_fragment, container, false);
        Repository<GamingItemInList> gamingRepository = WikiaApplication.from(getActivity()).getGamingItemsRepository();
        gamingRequestManager = new GameItemsRequestManager(getEventBus(), gamingRepository, RetrofitWikiaApi.getWikiaApi(getActivity()));

        refreshableListView = (PullToRefreshListView) fragmentView.findViewById(R.id.main_listview);
        ListView gamingItemsListView = refreshableListView.getRefreshableView();
        gamingItemsListView.setDividerHeight(1);
//        logosItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int listViewPosition, long id) {
//                if (!gamingItemsAdapter.isEmpty()) {
//                    MainActivity activity = (MainActivity) getActivity();
//                    LogoItemInList logo = gamingItemsAdapter.getItem(listViewPosition - 1);
//                    String contentId = logo.getId();
//                    activity.goToDetailCategoryScreen(ScreenIdNames.LOGO_DETAILS, contentId);
//                } else {
//                    MyLog.e(CLASS_TAG, "Failed to click on item, empty adapter");
//                }
//            }
//        });
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

//                    gamingRequestManager.getLogoNextItems();

            }
        });


//        refreshableListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
//        refreshableListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
//                if (logoMode == LogoMode.ALL) {
//                    gamingRequestManager.getLogoNextItems();
//                    refreshableListView.onRefreshComplete();
//                } else if (logoMode == LogoMode.CATEGORY_ID) {
//                    gamingRequestManager.getLogosNextPageByCategoryId(mCategoryId);
//                    refreshableListView.onRefreshComplete();
//                } else if (logoMode == LogoMode.SEARCH_MORE_LOGO) {
//                    gamingRequestManager.getLogosNextPageByLocation(searchWhat, searchWhere);
//                    refreshableListView.onRefreshComplete();
//                }
//
//            }
//        });

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoadingProgressDialog();
        updateGamingList();
    }

    private void updateGamingList() {
        List<GamingItemInList> gamingList = gamingRequestManager.getGamingList();
        showGamingList(gamingList);
    }

    @SuppressWarnings("unused") // EventBus
    public void onEventMainThread(GamingItemsChangedEvent event) {
        updateGamingList();
    }

    private void showGamingList(List<GamingItemInList> gamingList) {
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
