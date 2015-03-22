package test.gyatsina.wikiatask.ui;

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

import test.gyatsina.wikiatask.R;
import test.gyatsina.wikiatask.WikiaApplication;
import test.gyatsina.wikiatask.adapters.GamingItemsAdapter;
import test.gyatsina.wikiatask.api.RetrofitWikiaApi;
import test.gyatsina.wikiatask.event.GamingItemsChangedEvent;
import test.gyatsina.wikiatask.models.ComplexGameItem;
import test.gyatsina.wikiatask.models.GamingItemInList;
import test.gyatsina.wikiatask.repository.GameItemsRequestManager;
import test.gyatsina.wikiatask.repository.Repository;
import test.gyatsina.wikiatask.utils.MyLog;

public class GamingItemsListFragment extends BaseFragment {
    private static final String CLASS_TAG = "GamingItemsListFragment";
    private PullToRefreshListView refreshableListView;
    private GamingItemsAdapter gamingItemsAdapter;
    private GameItemsRequestManager gamingRequestManager;
    private static final String LIST_STATE = "listState";
    private static final String PARCEL_STATE = "parcelState";
    private int mListState = 0;
    private int testSelection = 11;
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
//            parcelState = savedInstanceState.getParcelable(PARCEL_STATE);
//            MyLog.v("---------!!!!!!!!!!!!!!!-----------1-mListState= ", mListState);
//            MyLog.v("---------!!!!!!!!!!!!!!!------------getLastVisiblePosition= ", gamingItemsListView.getLastVisiblePosition());
////            gamingItemsListView.post(new Runnable() {
////                @Override
////                public void run() {
////                    gamingItemsListView.requestFocusFromTouch();
////                    gamingItemsListView.setSelection(mListState);
////                    gamingItemsListView.requestFocus();
////                    gamingItemsAdapter.notifyDataSetChanged();
////                }
////            });
//            if (gamingItemsListView.getLastVisiblePosition() != mListState) {
//                gamingItemsListView.clearFocus();
//                gamingItemsListView.setSelection(mListState);
//                gamingItemsListView.requestFocus();
//
//                MyLog.v("---------!!!!!!!!!!!!!!!-----------2-mListState= ", mListState);
//            }
//
//            gamingItemsListView.onRestoreInstanceState(parcelState);
        }
        final int position = mListState;
        MyLog.v("***onActivityCreated  setSelection   selection ", position);

//        gamingItemsListView.setSelection(21);
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
//        Repository<GamingItemInList> gamingRepository = WikiaApplication.from(getActivity()).getGamingItemsRepository();
        Repository<ComplexGameItem> gamingRepository = WikiaApplication.from(getActivity()).getGamingItemsRepository();
        gamingRequestManager = new GameItemsRequestManager(getEventBus(), gamingRepository, RetrofitWikiaApi.getWikiaApi(getActivity()));

        refreshableListView = (PullToRefreshListView) fragmentView.findViewById(R.id.main_listview);
        gamingItemsListView = refreshableListView.getRefreshableView();
        gamingItemsListView.setDividerHeight(1);
        gamingItemsAdapter = new GamingItemsAdapter(getActivity());
        gamingItemsListView.setAdapter(gamingItemsAdapter);
        int selection = -1;
        if (savedInstanceState != null) {
            selection = savedInstanceState.getInt(LIST_STATE);
            testSelection = selection;
        } else {

        }

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
                MyLog.v("***setSelection    testSelection ", testSelection);
                MyLog.v("***setSelection   selection ", selection);
//            }
//        }, 1000);

        gamingItemsListView.setSelection(testSelection);

//        if (savedInstanceState != null) {
//            parcelState = savedInstanceState.getParcelable(PARCEL_STATE);
//            gamingItemsListView.onRestoreInstanceState(parcelState);
//
//            mListState = savedInstanceState.getInt(LIST_STATE);
//            gamingItemsAdapter.notifyDataSetChanged();
//            gamingItemsListView.clearFocus();
//            gamingItemsListView.post(new Runnable() {
//
//                @Override
//                public void run() {
//                    gamingItemsListView.setSelection(mListState);
//                    gamingItemsAdapter.notifyDataSetChanged();
//                }
//            });
//        }

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

    private void updateGamingList() {
//        List<GamingItemInList> gamingList = gamingRequestManager.getGamingList();
        List<ComplexGameItem> gamingList = gamingRequestManager.getGamingList();
        showGamingList(gamingList);
    }

    @SuppressWarnings("unused") // EventBus
    public void onEventMainThread(GamingItemsChangedEvent event) {
        updateGamingList();
    }

//    private void showGamingList(List<GamingItemInList> gamingList) {
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
