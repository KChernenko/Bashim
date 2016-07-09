package me.bitfrom.bashim.core.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import me.bitfrom.bashim.core.storage.entities.QuoteEntity;
import me.bitfrom.bashim.injection.ApplicationContext;
import me.bitfrom.bashim.utils.ConstantsManager;

public class DbOpenHelper extends SQLiteOpenHelper{

    @Inject
    public DbOpenHelper(@NonNull @ApplicationContext Context context) {
        super(context, ConstantsManager.DATABASE_NAME, null, ConstantsManager.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(QuoteEntity.CREATE_TABLE);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuoteEntity.TABLE_NAME);
        onCreate(db);
    }
}