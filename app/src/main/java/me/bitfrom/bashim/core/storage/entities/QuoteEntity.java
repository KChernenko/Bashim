package me.bitfrom.bashim.core.storage.entities;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

@AutoValue
abstract public class QuoteEntity implements QuoteModel, Parcelable {

    public static final Factory<QuoteEntity> FACTORY = new Factory<>(AutoValue_QuoteEntity::new);

    public static final RowMapper<QuoteEntity> MAPPER_SELECT_ALL = FACTORY.select_allMapper();

}