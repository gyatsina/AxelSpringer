package test.gyatsina.testproject;

import android.app.Application;
import android.content.Context;

import de.greenrobot.event.EventBus;
import test.gyatsina.testproject.models.ShutterImage;
import test.gyatsina.testproject.repository.Repository;
import test.gyatsina.testproject.repository.RepositoryImpl;

/**
 * Created by gyatsina
 */
public class TestApplication extends Application {
    private EventBus eventBus;
    private Repository<ShutterImage> gamingRepository;

    public static TestApplication from(Context context) {
        Context applicationContext = context.getApplicationContext();
        return ((TestApplication) applicationContext);
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

    public Repository<ShutterImage> getFlowerImagesRepository() {
        return gamingRepository;
    }
}
