package nebraszka.selfaid.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nebraszka.selfaid.R
import nebraszka.selfaid.data.domain.DomainMapper
import nebraszka.selfaid.data.domain.model.Emotion
import nebraszka.selfaid.data.local.entities.EmotionEntity
import nebraszka.selfaid.data.local.entities.EmotionEntityMapper
import nebraszka.selfaid.data.network.SelfAIDNetworkDataSource
import nebraszka.selfaid.data.network.models.EmotionDto
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
    fun provideEmotionDtoMapper(): DomainMapper<EmotionDto, Emotion>  {
        return EmotionDtoMapper()
    }

    @Singleton
    @Provides
    fun provideEmotionEntityMapper(): DomainMapper<EmotionEntity, Emotion>  {
        return EmotionEntityMapper()
    }

    @Singleton
    @Provides
    fun provideSelfAIDNetworkDataSource(): SelfAIDNetworkDataSource {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5257/api/")
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