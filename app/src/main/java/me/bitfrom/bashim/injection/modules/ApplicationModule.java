package me.bitfrom.bashim.injection.modules;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import me.bitfrom.bashim.injection.ApplicationContext;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides @NonNull
    Application providesApplication() {return application;}

    @Provides @NonNull
    @ApplicationContext
    Context providesContext() {return application;}
}