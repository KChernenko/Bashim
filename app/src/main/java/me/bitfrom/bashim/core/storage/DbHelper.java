package me.bitfrom.bashim.core.storage;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.bitfrom.bashim.core.rest.models.Quote;
import me.bitfrom.bashim.core.storage.entities.QuoteEntity;
import rx.Observable;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@Singleton
public class DbHelper {

    private BriteDatabase db;

    @Inject
    public DbHelper(@NonNull DbOpenHelper dbOpenHelper) {
        db = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper, Schedulers.io());
    }

    public Observable<List<QuoteEntity>> selectQuotes() {
        return db.createQuery(QuoteEntity.TABLE_NAME, QuoteEntity.SELECT_ALL)
               .mapToList(QuoteEntity.MAPPER_SELECT_ALL::map);
    }

    public Observable<Quote> insertQuotes(@NonNull List<Quote> quotes) {
        return Observable.defer(() -> {
            BriteDatabase.Transaction transaction = db.newTransaction();
            Quote quote = null;

            try {
                db.delete(QuoteEntity.TABLE_NAME, null);

                for (int i = 0; i < quotes.size(); i++) {
                    quote = quotes.get(i);

                    long result = db.insert(QuoteEntity.TABLE_NAME,
                            QuoteEntity.FACTORY.marshal()
                                .link(quote.getLink())
                                .content(quote.getContent())
                                .asContentValues(),
                            SQLiteDatabase.CONFLICT_REPLACE);

                    if (result < 0) Timber.e("Failed to insert data: " + result);
                }
                transaction.markSuccessful();
            } finally {
                transaction.end();
            }

            return Observable.just(quote);
        });
    }
}