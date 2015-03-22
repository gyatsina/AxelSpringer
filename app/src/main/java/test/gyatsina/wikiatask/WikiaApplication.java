package test.gyatsina.wikiatask;

import android.app.Application;
import android.content.Context;

import de.greenrobot.event.EventBus;
import test.gyatsina.wikiatask.models.ComplexGameItem;
import test.gyatsina.wikiatask.models.GamingItemInList;
import test.gyatsina.wikiatask.repository.Repository;
import test.gyatsina.wikiatask.repository.RepositoryImpl;

/**
 * Created by gyatsina
 */
public class WikiaApplication extends Application {
    private EventBus eventBus;
    private Repository<ComplexGameItem> gamingRepository;

    public static WikiaApplication from(Context context) {
        Context applicationContext = context.getApplicationContext();
        return ((WikiaApplication) applicationContext);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        gamingRepository = new RepositoryImpl<>();
        eventBus = EventBus.getDefault();
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Repository<ComplexGameItem> getGamingItemsRepository() {
        return gamingRepository;
    }


}
