package me.bitfrom.bashim.core.rest;

import java.util.List;

import me.bitfrom.bashim.core.rest.models.Quote;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface BashimApi {

    @GET("get")
    Observable<List<Quote>> loadQuotes(@Query("site") String site,
                                       @Query("name") String name,
                                       @Query("num") int number);
}
