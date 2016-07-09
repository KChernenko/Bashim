package me.bitfrom.bashim.core;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.bitfrom.bashim.core.rest.BashimApi;
import me.bitfrom.bashim.core.rest.models.Quote;
import me.bitfrom.bashim.core.storage.DbHelper;
import me.bitfrom.bashim.core.storage.entities.QuoteEntity;
import me.bitfrom.bashim.utils.ConstantsManager;
import rx.Observable;

@Singleton
public class DataManager {

    private DbHelper dbHelper;
    private BashimApi bashimApi;

    @Inject
    public DataManager(@NonNull DbHelper dbHelper, @NonNull BashimApi bashimApi) {
        this.dbHelper = dbHelper;
        this.bashimApi = bashimApi;
    }

    @NonNull
    public Observable<Quote> loadQuotes() {
        return bashimApi.loadQuotes(ConstantsManager.SITE_PARAM, ConstantsManager.NAME_PARAM,
                100).concatMap(quotes -> dbHelper.insertQuotes(quotes));
    }

    @NonNull
    public Observable<List<QuoteEntity>> getQuotes() {
        return dbHelper.selectQuotes();
    }
}