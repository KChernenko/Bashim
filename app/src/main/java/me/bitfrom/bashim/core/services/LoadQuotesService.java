package me.bitfrom.bashim.core.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import me.bitfrom.bashim.BashimApplication;
import me.bitfrom.bashim.core.DataManager;
import rx.Subscription;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class LoadQuotesService extends Service {

    @Inject
    protected DataManager dataManager;

    private WeakReference<Context> contextWeakReference;
    private Subscription subscription;

    @Override
    public void onCreate() {
        super.onCreate();
        contextWeakReference = new WeakReference<>(this);
        BashimApplication.get(contextWeakReference).getComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("Start loading quotes...");
        subscription = dataManager.loadQuotes()
                .subscribeOn(Schedulers.io())
                .doAfterTerminate(() -> {
                    stopSelf(startId);
                    Timber.d("Loading quotes has finished!");})
                .subscribe(quote -> {

                }, throwable -> {
                    Timber.e(throwable, "Error while loading quotes occurred!");
                });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        contextWeakReference.clear();
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}