package me.bitfrom.bashim.injection.modules;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import me.bitfrom.bashim.injection.ActivityContext;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides @NonNull
    Activity providesActivity() {return activity;}

    @Provides @NonNull
    @ActivityContext
    Context providesContext() {return activity;}
}