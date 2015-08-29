package test.gyatsina.axelspringer.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import test.gyatsina.axelspringer.R;
import test.gyatsina.axelspringer.api.RetrofitShutterStockApi;
import test.gyatsina.axelspringer.api.ShutterStockApi;
import test.gyatsina.axelspringer.event.ErrorEvent;
import test.gyatsina.axelspringer.event.FlowerImagesChangedEvent;
import test.gyatsina.axelspringer.models.ComplexGameItem;
import test.gyatsina.axelspringer.models.DetailedItemById;
import test.gyatsina.axelspringer.models.DetailedItemsContainer;
import test.gyatsina.axelspringer.models.GamingItemInList;
import test.gyatsina.axelspringer.models.ImagesResponse;
import test.gyatsina.axelspringer.models.ItemsList;
import test.gyatsina.axelspringer.models.ShutterImage;
import test.gyatsina.axelspringer.utils.MyLog;

/**
 * Created by gyatsina
 */
public class GameItemsRequestManager {
    private static final String CLASS_TAG = "GameItemsRequestManager";
    private final EventBus eventBus;
    private final Repository<ShutterImage> flowerImagesRepository;
    private final ShutterStockApi shutterStockApi;
    private final int itemsInBatch = 25;
    private ImagesResponse images;

//    public GameItemsRequestManager(EventBus eventBus, Repository<ComplexGameItem> flowerImagesRepository, ShutterStockApi wikiaApi) {
    public GameItemsRequestManager(EventBus eventBus, Repository<ShutterImage> gamingListRepository, ShutterStockApi wikiaApi) {
        this.eventBus = eventBus;
        this.flowerImagesRepository = gamingListRepository;
        this.shutterStockApi = wikiaApi;
    }

    public void cleanGamingListRepository() {
        flowerImagesRepository.removeAll();
    }

//    public ImagesResponse getFlowerImagesList() {
//        ImagesResponse images;
//        shutterStockApi.getFlowerImages(RetrofitShutterStockApi.FLOWER_QUERY, RetrofitShutterStockApi.DEFAULT_PER_PAGE,
//                new Callback<ImagesResponse>() {
//                    @Override
//                    public void success(ImagesResponse response, Response response2) {
//                        String url = response.getData().get(0).getAssets().getLargeThumb().getUrl();
//                        setData(response);
//                    }
//
//                    @Override
//                    public void failure(RetrofitError retrofitError) {
//                        MyLog.e(CLASS_TAG, "Failed to get flower items list: " + retrofitError.getMessage());
//                        eventBus.post(new ErrorEvent(R.string.request_error));
//                    }
//                });
//
//        return getData();
//    }
//
//    private void setData(ImagesResponse response) {
//        images = response;
//    }
//
//    private ImagesResponse getData() {
//        return images;
//    }

    /**
     * Method getFlowerImagesList and getGamingListNextItems save items to flowerImagesRepository
     * These items are represented on main screen
     * flowerImagesRepository is  refreshed on onPullDownToRefresh action
     */
    public List<ShutterImage> getFlowerImagesList() {
        if (flowerImagesRepository.size() == 0) {
            shutterStockApi.getFlowerImages(RetrofitShutterStockApi.FLOWER_QUERY, RetrofitShutterStockApi.DEFAULT_PER_PAGE,
                    new Callback<ImagesResponse>() {
                        @Override
                        public void success(ImagesResponse response, Response response2) {
                            final ArrayList<ShutterImage> flowerImages = (ArrayList)response.getData();
                                            flowerImagesRepository.removeAll();
                                            flowerImagesRepository.addAll(flowerImages);
                                            eventBus.post(new FlowerImagesChangedEvent());
                                        }



                        @Override
                        public void failure(RetrofitError retrofitError) {
                            MyLog.e(CLASS_TAG, "Failed to get general flower items list: " + retrofitError.getMessage());
                            eventBus.post(new ErrorEvent(R.string.request_error));
                        }
                    });
        }

        List<ShutterImage> gamingList = flowerImagesRepository.getAll();

        return gamingList;
    }

//    public void getGamingListNextItems() {
//        int itemsSaved = flowerImagesRepository.size();
//        int batchToLoad = itemsSaved / itemsInBatch + 1;
//        int batchLimit = flowerImagesRepository.getLimit();
//        if (batchToLoad > batchLimit) {
//            eventBus.post(new FlowerImagesChangedEvent());
//            return;
//        }
//
//        shutterStockApi.getGamingItemsList(RetrofitShutterStockApi.CONTROLLER, RetrofitShutterStockApi.GET_LIST, RetrofitShutterStockApi.GAMING_HUB,
//                RetrofitShutterStockApi.ENG_LANG, batchToLoad, new Callback<ItemsList<GamingItemInList>>() {
//                    @Override
//                    public void success(ItemsList<GamingItemInList> response, Response response2) {
//                        final ItemsList<GamingItemInList> gamingItemsInList = response;
//                        String idsForDetails = formDetailedIdsParameter(gamingItemsInList);
//                        shutterStockApi.getDetailedItemsById(RetrofitShutterStockApi.CONTROLLER, RetrofitShutterStockApi.GET_DETAILS, idsForDetails,
//                                new Callback<DetailedItemsContainer<HashMap<Integer, DetailedItemById>>>() {
//                                    @Override
//                                    public void success(DetailedItemsContainer<HashMap<Integer, DetailedItemById>> responseDetailed, Response response2) {
//                                        final ArrayList<ComplexGameItem> complexGameItems = formListForRepositorySave(responseDetailed, gamingItemsInList);
//                                        flowerImagesRepository.addAll(complexGameItems);
//                                        eventBus.post(new FlowerImagesChangedEvent());
//                                    }
//
//                                    @Override
//                                    public void failure(RetrofitError retrofitError) {
//                                        MyLog.e(CLASS_TAG, "Failed to get detailed gaming items list: " + retrofitError.getMessage());
//                                        eventBus.post(new ErrorEvent(R.string.request_error));
//                                    }
//                                });
//                    }
//
//                    @Override
//                    public void failure(RetrofitError retrofitError) {
//                        MyLog.e(CLASS_TAG, "Failed to get next page gaming items: " + retrofitError.getMessage());
//                        eventBus.post(new ErrorEvent(R.string.request_error));
//                    }
//                });
//    }

}
