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

import test.gyatsina.axelspringer.AxelSpringerApplication;
import test.gyatsina.axelspringer.R;
import test.gyatsina.axelspringer.adapters.ImageItemsAdapter;
import test.gyatsina.axelspringer.api.RetrofitShutterStockApi;
import test.gyatsina.axelspringer.event.FlowerImagesChangedEvent;
import test.gyatsina.axelspringer.models.ShutterImage;
import test.gyatsina.axelspringer.repository.ImagesRequestManager;
import test.gyatsina.axelspringer.repository.Repository;

/*
This fragment represents flower images in Scrollable ListView.
PullDownToRefresh will clean all loaded items
PullUpToRefresh will load more items up to limit
 */
public class ItemsListFragment extends BaseFragment {
    public static final String CLASS_TAG = ItemsListFragment.class.getName();
    private PullToRefreshListView refreshableListView;
    private ImageItemsAdapter itemsAdapter;
    private ImagesRequestManager imageItemsRequestManager;
    private static final String LIST_STATE = "listState";
    private static final String PARCEL_STATE = "parcelState";
    private int mListState = 0;
    private Parcelable parcelState;
    private ListView itemstemsListView;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int motionPosition = itemstemsListView.getLastVisiblePosition();
        parcelState = itemstemsListView.onSaveInstanceState();
        outState.putInt(LIST_STATE, motionPosition);
        outState.putParcelable(PARCEL_STATE, parcelState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            mListState = savedInstanceState.getInt(LIST_STATE);
            itemstemsListView.setSelection(mListState);
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
        updateItemsList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.list_fragment, container, false);
        Repository<ShutterImage> flowerImagesRepository = AxelSpringerApplication.from(getActivity()).getFlowerImagesRepository();
        imageItemsRequestManager = new ImagesRequestManager(getEventBus(), flowerImagesRepository, RetrofitShutterStockApi.getShutterStockApi(getActivity()));

        refreshableListView = (PullToRefreshListView) fragmentView.findViewById(R.id.main_listview);
        itemstemsListView = refreshableListView.getRefreshableView();
        itemstemsListView.setDividerHeight(1);
        itemsAdapter = new ImageItemsAdapter(getActivity());
        itemstemsListView.setAdapter(itemsAdapter);

        refreshableListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshableListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                showLoadingProgressDialog();
                imageItemsRequestManager.cleanFlowerImagesRepository();
                updateItemsList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                imageItemsRequestManager.getMoreFlowerImagesList();
            }
        });

        return fragmentView;
    }

    private void updateItemsList() {
        List<ShutterImage> flowerItemsList = imageItemsRequestManager.getSavedFlowerImages();
        showItemsList(flowerItemsList);
    }

    @SuppressWarnings("unused") // EventBus
    public void onEventMainThread(FlowerImagesChangedEvent event) {
        updateItemsList();
    }

    private void showItemsList(List<ShutterImage> flowerItemsList) {
        itemsAdapter.setList(flowerItemsList);
        if (!flowerItemsList.isEmpty()) {
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
