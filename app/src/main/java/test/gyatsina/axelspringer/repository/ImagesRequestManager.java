package test.gyatsina.axelspringer.repository;

import java.util.ArrayList;
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
import test.gyatsina.axelspringer.models.ImagesResponse;
import test.gyatsina.axelspringer.models.ShutterImage;
import test.gyatsina.axelspringer.utils.MyLog;

/**
 * Created by gyatsina
 */
public class ImagesRequestManager {
    private static final String CLASS_TAG = ImagesRequestManager.class.getName();
    private final EventBus eventBus;
    private final Repository<ShutterImage> flowerImagesRepository;
    private final ShutterStockApi shutterStockApi;

    public ImagesRequestManager(EventBus eventBus, Repository<ShutterImage> gamingListRepository, ShutterStockApi shutterStockApi1) {
        this.eventBus = eventBus;
        this.flowerImagesRepository = gamingListRepository;
        this.shutterStockApi = shutterStockApi1;
    }

    public void cleanGamingListRepository() {
        flowerImagesRepository.removeAll();
    }

    /**
     * Method getMoreFlowerImagesList  saves items to flowerImagesRepository
     * These items are represented on main screen
     * flowerImagesRepository is  refreshed on onPullDownToRefresh action
     */
    public void getMoreFlowerImagesList() {
        int pageToLoad = RetrofitShutterStockApi.DEFAULT_PAGE;
        if (flowerImagesRepository.size() == 0) {
            flowerImagesRepository.removeAll();
            flowerImagesRepository.setLimit(RetrofitShutterStockApi.LIMIT);
        } else {
            pageToLoad = getPageToload();
            if (pageToLoad == 0){
                return;
            }
        }

        shutterStockApi.getFlowerImages(RetrofitShutterStockApi.FLOWER_QUERY, RetrofitShutterStockApi.PER_PAGE,
                pageToLoad, new Callback<ImagesResponse>() {
                    @Override
                    public void success(ImagesResponse response, Response response2) {
                        final ArrayList<ShutterImage> flowerImages = (ArrayList) response.getData();
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

    private int getPageToload() {
        int itemsSaved = flowerImagesRepository.size();
        int pageLimit = flowerImagesRepository.getLimit();
        if (itemsSaved >= pageLimit) {
            eventBus.post(new FlowerImagesChangedEvent());
            return 0;
        }

        int currentPage = (itemsSaved / RetrofitShutterStockApi.PER_PAGE);
        int pageToLoad = currentPage + 1;

        return pageToLoad;
    }

    public List<ShutterImage> getSavedFlowerImages() {
        List<ShutterImage> gamingList = flowerImagesRepository.getAll();
        if (gamingList.size() == 0) {
            getMoreFlowerImagesList();
        }

        return gamingList;
    }
}
