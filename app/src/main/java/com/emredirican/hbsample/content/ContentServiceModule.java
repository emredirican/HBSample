package com.emredirican.hbsample.content;

import com.emredirican.hbsample.util.rest.EndPoints;
import com.emredirican.hbsample.PerApplication;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

@Module public class ContentServiceModule {

  @Provides @PerApplication public ContentService provideService(
      CallAdapter.Factory callAdapterFactory, Converter.Factory converterFactory,
      OkHttpClient httpClient
  ) {
    return new Retrofit.Builder().addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .client(httpClient)
        .baseUrl(EndPoints.CONTENT)
        .build()
        .create(ContentService.class);
  }
}
