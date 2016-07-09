package me.bitfrom.bashim;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;

import me.bitfrom.bashim.injection.components.ApplicationComponent;
import me.bitfrom.bashim.injection.components.DaggerApplicationComponent;
import me.bitfrom.bashim.injection.modules.ApplicationModule;
import timber.log.Timber;

public class BashimApplication extends Application {

    private ApplicationComponent applicationComponent;
    private RefWatcher refWatcher;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            MultiDex.install(this);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initTimber();
        initLeakCanary();
        initStetho();
    }

    public static BashimApplication get(WeakReference<Context> contextWeakReference) {
        return (BashimApplication) contextWeakReference.get().getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }

        return applicationComponent;
    }

    public static RefWatcher getRefWatcher(Context context) {
        BashimApplication application = (BashimApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initLeakCanary() {
        refWatcher = LeakCanary.install(this);
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }
}