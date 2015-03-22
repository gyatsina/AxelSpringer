package test.gyatsina.wikiatask.repository;

import java.util.ArrayList;
import java.util.HashMap;
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
import test.gyatsina.wikiatask.models.ComplexGameItem;
import test.gyatsina.wikiatask.models.DetailedItemById;
import test.gyatsina.wikiatask.models.DetailedItemsContainer;
import test.gyatsina.wikiatask.models.GamingItemInList;
import test.gyatsina.wikiatask.models.ItemsList;
import test.gyatsina.wikiatask.utils.MyLog;

/**
 * Created by gyatsina
 */
public class GameItemsRequestManager {
    private static final String CLASS_TAG = "GameItemsRequestManager";
    private final EventBus eventBus;
    private final Repository<ComplexGameItem> gamingListRepository;
    private final WikiaApi wikiaApi;
    private final int itemsInBatch = 25;

    public GameItemsRequestManager(EventBus eventBus, Repository<ComplexGameItem> gamingListRepository, WikiaApi wikiaApi) {
        this.eventBus = eventBus;
        this.gamingListRepository = gamingListRepository;
        this.wikiaApi = wikiaApi;
    }

    public void cleanGamingListRepository() {
        gamingListRepository.removeAll();
    }

    /**
     * Method getGamingList and getGamingListNextItems save items to gamingListRepository
     * These items are represented on main screen
     * gamingListRepository is  refreshed on onPullDownToRefresh action
     */
    public List<ComplexGameItem> getGamingList() {
        if (gamingListRepository.size() == 0) {
            wikiaApi.getGamingItemsList(RetrofitWikiaApi.CONTROLLER, RetrofitWikiaApi.GET_LIST, RetrofitWikiaApi.GAMING_HUB,
                    RetrofitWikiaApi.ENG_LANG, 1, new Callback<ItemsList<GamingItemInList>>() {
                        @Override
                        public void success(ItemsList<GamingItemInList> response, Response response2) {
                            final ItemsList<GamingItemInList> gamingItemsInList = response;
                            String idsForDetails = formDetailedIdsParameter(gamingItemsInList);
                            wikiaApi.getDetailedItemsById(RetrofitWikiaApi.CONTROLLER, RetrofitWikiaApi.GET_DETAILS, idsForDetails,
                                    new Callback<DetailedItemsContainer<HashMap<Integer, DetailedItemById>>>() {
                                        @Override
                                        public void success(DetailedItemsContainer<HashMap<Integer, DetailedItemById>> responseDetailed, Response response2) {
                                            final ArrayList<ComplexGameItem> complexGameItems = formListForRepositorySave(responseDetailed, gamingItemsInList);
                                            gamingListRepository.removeAll();
                                            gamingListRepository.addAll(complexGameItems);
                                            gamingListRepository.setLimit(gamingItemsInList.getBatches());
                                            eventBus.post(new GamingItemsChangedEvent());
                                        }

                                        @Override
                                        public void failure(RetrofitError retrofitError) {
                                            MyLog.e(CLASS_TAG, "Failed to get detailed gaming items list: " + retrofitError.getMessage());
                                            eventBus.post(new ErrorEvent(R.string.request_error));
                                        }
                                    });
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            MyLog.e(CLASS_TAG, "Failed to get general gaming items list: " + retrofitError.getMessage());
                            eventBus.post(new ErrorEvent(R.string.request_error));
                        }
                    });
        }

        List<ComplexGameItem> gamingList = gamingListRepository.getAll();

        return gamingList;
    }

    public void getGamingListNextItems() {
        int itemsSaved = gamingListRepository.size();
        int batchToLoad = itemsSaved / itemsInBatch + 1;
        int batchLimit = gamingListRepository.getLimit();
        if (batchToLoad > batchLimit) {
            eventBus.post(new GamingItemsChangedEvent());
            return;
        }

        wikiaApi.getGamingItemsList(RetrofitWikiaApi.CONTROLLER, RetrofitWikiaApi.GET_LIST, RetrofitWikiaApi.GAMING_HUB,
                RetrofitWikiaApi.ENG_LANG, batchToLoad, new Callback<ItemsList<GamingItemInList>>() {
                    @Override
                    public void success(ItemsList<GamingItemInList> response, Response response2) {
                        final ItemsList<GamingItemInList> gamingItemsInList = response;
                        String idsForDetails = formDetailedIdsParameter(gamingItemsInList);
                        wikiaApi.getDetailedItemsById(RetrofitWikiaApi.CONTROLLER, RetrofitWikiaApi.GET_DETAILS, idsForDetails,
                                new Callback<DetailedItemsContainer<HashMap<Integer, DetailedItemById>>>() {
                                    @Override
                                    public void success(DetailedItemsContainer<HashMap<Integer, DetailedItemById>> responseDetailed, Response response2) {
                                        final ArrayList<ComplexGameItem> complexGameItems = formListForRepositorySave(responseDetailed, gamingItemsInList);
                                        gamingListRepository.addAll(complexGameItems);
                                        eventBus.post(new GamingItemsChangedEvent());
                                    }

                                    @Override
                                    public void failure(RetrofitError retrofitError) {
                                        MyLog.e(CLASS_TAG, "Failed to get detailed gaming items list: " + retrofitError.getMessage());
                                        eventBus.post(new ErrorEvent(R.string.request_error));
                                    }
                                });
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        MyLog.e(CLASS_TAG, "Failed to get next page gaming items: " + retrofitError.getMessage());
                        eventBus.post(new ErrorEvent(R.string.request_error));
                    }
                });
    }

    private String formDetailedIdsParameter(ItemsList<GamingItemInList> gamingItemsInList) {
        String idsForDetails = "";
        for (GamingItemInList item : gamingItemsInList.getItems()) {
            idsForDetails = idsForDetails + "," + item.getId();
        }
        return idsForDetails;
    }

    private ArrayList<ComplexGameItem> formListForRepositorySave(DetailedItemsContainer<HashMap<Integer, DetailedItemById>> responseDetailed, ItemsList<GamingItemInList> gamingItemsInList) {
        final ArrayList<ComplexGameItem> complexGameItems = new ArrayList<>();
        for (GamingItemInList gItem : gamingItemsInList.getItems()) {
            int id = gItem.getId();
            String name = gItem.getName();
            String url = gItem.getDomain();
            String image = responseDetailed.getItems().get(id).getImage();
            complexGameItems.add(new ComplexGameItem(id, name, url, image));
        }
        return complexGameItems;
    }
}
