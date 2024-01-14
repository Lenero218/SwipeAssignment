package com.example.swipeassessment.module

import androidx.room.Room
import com.example.swipeassessment.Database.DataSource.ProductLocalDataSource
import com.example.swipeassessment.Database.DataSource.ProductLocalDataSourceImpl
import com.example.swipeassessment.Database.ProductDao
import com.example.swipeassessment.Database.ProductDatabase
import com.example.swipeassessment.Domain.mapper.ProductDetailsMapper
import com.example.swipeassessment.Domain.usecase.GetProductDetailsUseCase
import com.example.swipeassessment.Domain.usecase.PostProductDetailsUseCase
import com.example.swipeassessment.Domain.usecase.SearchProductUseCase
import com.example.swipeassessment.Remote.ProductApi
import com.example.swipeassessment.Remote.data.ProductRemoteDataSource
import com.example.swipeassessment.Remote.data.ProductRemoteDataSourceImpl
import com.example.swipeassessment.Repo.ProductRepo
import com.example.swipeassessment.Repo.ProductRepoImpl
import com.example.swipeassessment.ui.ProductViewModel
import com.example.swipeassessment.Utility.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module{
    single{
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ProductApi::class.java)
    }
}

val databaseModule = module{
    single{
        Room.databaseBuilder(
            androidContext(),
            ProductDatabase::class.java,
            "product_database"
        ).build()
    }
}

val daoModule = module {
    //single<ProductDao>{ProductDatabase.getDatabase(get()).productDao()}
    single<ProductDao> {
        val database = get<ProductDatabase>()
        database.productDao()
    }
}

val dataSourceModule = module{
    single<ProductLocalDataSource>{ProductLocalDataSourceImpl(get())}
    single<ProductRemoteDataSource>{ ProductRemoteDataSourceImpl(get()) }
}

val repoModule = module {

    single<ProductRepo> { ProductRepoImpl(get(),get(),get()) }

}

val mapperModule = module{
    single{ProductDetailsMapper()}
}

val usecaseModule = module {
    single{ GetProductDetailsUseCase(get())}
    single{ SearchProductUseCase(get())}
    single{PostProductDetailsUseCase(get())}
}

val viewModelModule = module {
    viewModel {
        ProductViewModel(get(),get(), get() )
    }
}