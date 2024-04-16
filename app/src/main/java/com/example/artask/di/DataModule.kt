package com.example.artask.di

import android.app.Application
import androidx.room.Room
import com.example.data.api.ArticleApi
import com.example.data.api.ArticleApi.Companion.BASE_URL
import com.example.data.client.ArticleClient
import com.example.data.database.ArticleDatabase
import com.example.data.repository.ArticleRepoImpl
import com.example.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

  @Singleton
  @Provides

  fun provideApi(): ArticleApi {
    val logging = HttpLoggingInterceptor().apply {
      this.level = HttpLoggingInterceptor.Level.BODY
    };
    val client = OkHttpClient.Builder()
      .addInterceptor(logging)
      .build()
    return Retrofit.Builder().baseUrl(BASE_URL).client(client)
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
      .create()
  }

  @Provides
  fun provideRepo (articleClient: ArticleClient) : ArticleRepository {
    return ArticleRepoImpl(articleClient)
  }

  @Provides
  fun provideDatabase(application: Application) : ArticleDatabase{
    return Room.databaseBuilder(
      application,
      ArticleDatabase::class.java,
      "articlesdatabase"
    )
      .fallbackToDestructiveMigration()
      .build()
  }
}
