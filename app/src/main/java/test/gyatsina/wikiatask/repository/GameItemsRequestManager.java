package test.gyatsina.wikiatask.repository;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import test.gyatsina.wikiatask.R;
import test.gyatsina.wikiatask.api.RetrofitWikiaApi;
import test.gyatsina.wikiatask.api.WikiaApi;
import test.gyatsina.wikiatask.event.ErrorEvent;
import test.gyatsina.wikiatask.event.GamingItemsChangedEvent;
import test.gyatsina.wikiatask.models.GamingItemInList;
import test.gyatsina.wikiatask.models.ItemsList;
import test.gyatsina.wikiatask.utils.MyLog;

/**
 * Created by gyatsina 
 */
public class GameItemsRequestManager {
    private static final String CLASS_TAG = "GameItemsRequestManager";
    private final EventBus eventBus;
    private final Repository<GamingItemInList> gamingListRepository;
    private final WikiaApi wikiaApi;
    private final int itemsInBatch = 25;

    public GameItemsRequestManager(EventBus eventBus, Repository<GamingItemInList> gamingListRepository, WikiaApi wikiaApi) {
        this.eventBus = eventBus;
        this.gamingListRepository = gamingListRepository;
        this.wikiaApi = wikiaApi;
    }

    public void cleanGamingListRepository() {
        gamingListRepository.removeAll();
    }

    /**
     * Method getGamingList and getLogoNextItems save items to gamingListRepository
     * This items are represented on main screen
     * gamingListRepository is  refreshed on onPullDownToRefresh action
     */
    public List<GamingItemInList> getGamingList() {
        if (gamingListRepository.size() == 0) {
            wikiaApi.getGamingItemsList(RetrofitWikiaApi.CONTROLLER, RetrofitWikiaApi.GET_LIST, RetrofitWikiaApi.GAMING_HUB,
                    RetrofitWikiaApi.ENG_LANG, 1, new Callback<ItemsList<GamingItemInList>>() {
                        @Override
                        public void success(ItemsList<GamingItemInList> response, Response response2) {
                            gamingListRepository.removeAll();
                            gamingListRepository.addAll(response.getItems());
                            gamingListRepository.setLimit(response.getBatches());
                            eventBus.post(new GamingItemsChangedEvent());
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            MyLog.e(CLASS_TAG, "Failed to get gaming items list: " + retrofitError.getMessage());
                            eventBus.post(new ErrorEvent(R.string.request_error));
                        }
                    });
        }

        List<GamingItemInList> gamingList = gamingListRepository.getAll();

        return gamingList;
    }

    public void getGamingListNextItems() {
        int itemsSaved = gamingListRepository.size();
        int batchToLoad = itemsSaved/itemsInBatch + 1;
        int batchLimit = gamingListRepository.getLimit();
        if (batchToLoad > batchLimit){
            eventBus.post(new GamingItemsChangedEvent());
            return;
        }

        wikiaApi.getGamingItemsList(RetrofitWikiaApi.CONTROLLER, RetrofitWikiaApi.GET_LIST, RetrofitWikiaApi.GAMING_HUB,
                RetrofitWikiaApi.ENG_LANG, batchToLoad, new Callback<ItemsList<GamingItemInList>>() {
                    @Override
                    public void success(ItemsList<GamingItemInList> response, Response response2) {
                        gamingListRepository.addAll(response.getItems());
                        eventBus.post(new GamingItemsChangedEvent());
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        MyLog.e(CLASS_TAG, "Failed to get next page gaming items: " + retrofitError.getMessage());
                        eventBus.post(new ErrorEvent(R.string.request_error));
                    }
                });
    }
}
