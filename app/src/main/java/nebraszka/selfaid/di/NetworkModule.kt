package nebraszka.selfaid.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nebraszka.selfaid.R
import nebraszka.selfaid.data.local.entities.EmotionEntityMapper
import nebraszka.selfaid.data.network.SelfAIDNetworkDataSource
import nebraszka.selfaid.data.network.models.EmotionDtoMapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideEmotionDtoMapper(): EmotionDtoMapper {
        return EmotionDtoMapper()
    }

    @Singleton
    @Provides
    fun provideEmotionEntityMapper(): EmotionEntityMapper {
        return EmotionEntityMapper()
    }

    @Singleton
    @Provides
    fun provideSelfAIDNetworkDataSource(): SelfAIDNetworkDataSource {
        return Retrofit.Builder()
            .baseUrl(R.string.api_url.toString())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(SelfAIDNetworkDataSource::class.java)
    }

    /**
     * I might include proper authentication later on food2fork.ca
     * For now just hard code a token.
     */
    @Singleton
    @Provides
    @Named("auth_token")
    fun provideAuthToken(): String{
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }

}