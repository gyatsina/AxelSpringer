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
 * Created by gyatsina on 2/24/2015.
 */
public class GameItemsRequestManager {
    private static final String CLASS_TAG = "GameItemsRequestManager";
    private final EventBus eventBus;
    private final Repository<GamingItemInList> gamingListRepository;
    private final WikiaApi wikiaApi;

    public GameItemsRequestManager(EventBus eventBus, Repository<GamingItemInList> gamingListRepository, WikiaApi wikiaApi) {
        this.eventBus = eventBus;
        this.gamingListRepository = gamingListRepository;
        this.wikiaApi = wikiaApi;
    }

    public void cleanGamingListRepository(){
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
                    RetrofitWikiaApi.ENG_LANG, 25, new Callback<ItemsList<GamingItemInList>>() {
                @Override
                public void success(ItemsList<GamingItemInList> response, Response response2) {
                    gamingListRepository.removeAll();
                    gamingListRepository.addAll(response.getItems());
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

//    public void getLogoNextItems() {
//        int itemsSaved = gamingListRepository.size();
//        wikiaApi.getLogosNextPage(itemsSaved, new Callback<ItemsList<GamingItemInList>>() {
//            @Override
//            public void success(ItemsListParcelable<GamingItemInList> response, Response response2) {
//                gamingListRepository.addAll(response.getItems());
//                eventBus.post(new GamingItemsChangedEvent());
//            }
//
//            @Override
//            public void failure(RetrofitError retrofitError) {
//                MyLog.e(CLASS_TAG, "Failed to get next page logos: " + retrofitError.getMessage());
//                eventBus.post(new ErrorEvent(R.string.request_error));
//            }
//        });
//    }
}
