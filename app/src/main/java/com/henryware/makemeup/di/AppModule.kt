package com.henryware.makemeup.di

import android.content.Context
import androidx.room.Room
import com.henryware.makemeup.data.local.ProductsDatabase
import com.henryware.makemeup.data.remote.MakeUpApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://makeup-api.herokuapp.com"

    @Singleton
    @Provides
    fun getAPI(retrofit: Retrofit): MakeUpApi {
        return retrofit.create(MakeUpApi::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideProductsDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        ProductsDatabase::class.java,
        "products.db"
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideBrandsDao(db: ProductsDatabase) = db.getBrandsDao()
}
