package com.emredirican.hbsample.content;

import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;

public interface ContentService {

  @GET("/remaining_seconds") Observable<List<Response>> remainingSeconds();
}