package com.emredirican.hbsample.util.rest;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class RestServicesModule {

  @Provides public OkHttpClient provideClient(@LoggingInterceptor Interceptor loggingInterceptor) {
    return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
  }

  @Provides @LoggingInterceptor public Interceptor provideLoggingInterceptor() {
    return new HttpLoggingInterceptor();
  }

  @Provides public Converter.Factory provideGsonConverterFactory(@Named("ForRest") Gson gson) {
    return GsonConverterFactory.create(gson);
  }

  @Provides public CallAdapter.Factory provideCallAdapterFactory() {
    return RxJava2CallAdapterFactory.create();
  }

  @Named("ForRest") @Provides public Gson provideGsonForRest() {
    return new Gson();
  }

}
