package me.bitfrom.bashim.ui.presenters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import me.bitfrom.bashim.core.DataManager;
import me.bitfrom.bashim.core.services.LoadQuotesService;
import me.bitfrom.bashim.injection.ActivityContext;
import me.bitfrom.bashim.ui.base.BasePresenter;
import me.bitfrom.bashim.ui.views.MainActivityView;
import me.bitfrom.bashim.utils.NetworkStateChecker;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    private Context context;
    private DataManager dataManager;

    private Subscription subscription;

    @Inject
    public MainActivityPresenter(@NonNull @ActivityContext Context context,
                                 @NonNull DataManager dataManager) {
        this.context = context;
        this.dataManager = dataManager;
    }

    @Override
    public void attach(MainActivityView mvpView) {
        super.attach(mvpView);
    }

    public void loadQuotes() {
        if (NetworkStateChecker.isNetworkAvailable(context)) {
            context.startService(new Intent(context, LoadQuotesService.class));
        } else {
            if (isViewAttached()) getMvpView().showInternetNotAvailable();
        }
    }

    public void getQuotes() {
        subscription = dataManager.getQuotes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quoteEntities -> {
                    if (! quoteEntities.isEmpty()) getMvpView().showQuotes(quoteEntities);
                    else getMvpView().showQuotesIsEmpty();
                });
    }

    @Override
    public void detach() {
        super.detach();
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
    }
}