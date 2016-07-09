package me.bitfrom.bashim.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;

import me.bitfrom.bashim.BashimApplication;
import me.bitfrom.bashim.injection.components.ActivityComponent;
import me.bitfrom.bashim.injection.modules.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;
    private WeakReference<Context> weakReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weakReference = new WeakReference<>(this);
    }

    @Override
    protected void onDestroy() {
        weakReference.clear();
        super.onDestroy();
        watchForLeaks();
    }

    protected ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = BashimApplication.get(weakReference)
                    .getComponent()
                    .addActivityComponent(new ActivityModule(this));
        }

        return activityComponent;
    }

    private void watchForLeaks() {
        RefWatcher refWatcher = BashimApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}