package com.hafizrahmadhani.core.di

import com.hafizrahmadhani.core.data.MovieRepository
import com.hafizrahmadhani.core.data.source.local.LocalDataSource
import com.hafizrahmadhani.core.data.source.remote.RemoteDataSource
import com.hafizrahmadhani.core.data.source.remote.network.ApiDataSource
import com.hafizrahmadhani.core.domain.repository.IMovieRepository
import com.hafizrahmadhani.core.domain.usecase.InteractorMovie
import com.hafizrahmadhani.core.domain.usecase.MovieUseCase
import androidx.room.Room
import com.hafizrahmadhani.core.data.source.local.database.MovieDatabase
import com.hafizrahmadhani.core.data.source.local.entity.Converter
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val coreModule = module {
    single <IMovieRepository> {MovieRepository(get(), get())}
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single<MovieUseCase> { InteractorMovie(get()) }
}

val databaseModule = module{
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(ApiDataSource::class.java) }
}

val networkModule = module{
    single {
        val passphrase: ByteArray = net.sqlcipher.database.SQLiteDatabase.getBytes("iMovie_Rahmadhani".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(androidContext(), MovieDatabase::class.java, DB_NAME)
            .addTypeConverter(Converter())
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    single { get<MovieDatabase>().movieDao() }
}

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val DB_NAME = "MOVIE_DB.db"