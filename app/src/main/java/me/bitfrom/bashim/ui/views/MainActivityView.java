package me.bitfrom.bashim.ui.views;

import android.support.annotation.NonNull;

import java.util.List;

import me.bitfrom.bashim.core.storage.entities.QuoteEntity;
import me.bitfrom.bashim.ui.base.MvpView;

public interface MainActivityView extends MvpView{

    void showQuotes(@NonNull List<QuoteEntity> quoteEntities);

    void showQuotesIsEmpty();

    void showInternetNotAvailable();

}