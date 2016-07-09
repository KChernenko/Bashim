package me.bitfrom.bashim.injection.components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.bitfrom.bashim.core.DataManager;
import me.bitfrom.bashim.core.rest.RestModule;
import me.bitfrom.bashim.core.services.LoadQuotesService;
import me.bitfrom.bashim.core.storage.DbHelper;
import me.bitfrom.bashim.injection.ApplicationContext;
import me.bitfrom.bashim.injection.modules.ActivityModule;
import me.bitfrom.bashim.injection.modules.ApplicationModule;

@Singleton
@Component(modules = {ApplicationModule.class, RestModule.class})
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    ActivityComponent addActivityComponent(ActivityModule activityModule);

    DbHelper providesDbHelper();

    DataManager providesDataManager();

    void inject(LoadQuotesService loadQuotesService);

}