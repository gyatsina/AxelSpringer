package test.gyatsina.axelspringer;

import android.app.Application;
import android.content.Context;

import de.greenrobot.event.EventBus;
import test.gyatsina.axelspringer.models.ComplexGameItem;
import test.gyatsina.axelspringer.models.ImagesResponse;
import test.gyatsina.axelspringer.models.ShutterImage;
import test.gyatsina.axelspringer.repository.Repository;
import test.gyatsina.axelspringer.repository.RepositoryImpl;

/**
 * Created by gyatsina
 */
public class WikiaApplication extends Application {
    private EventBus eventBus;
    private Repository<ShutterImage> gamingRepository;

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

    public Repository<ShutterImage> getGamingItemsRepository() {
        return gamingRepository;
    }

//    public Repository<ShutterImage> getGamingItemsRepository() {
//        return gamingRepository;
//    }


}
