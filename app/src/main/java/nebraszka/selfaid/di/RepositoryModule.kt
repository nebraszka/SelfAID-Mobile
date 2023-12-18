package nebraszka.selfaid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nebraszka.selfaid.data.network.EmotionApiService
import nebraszka.selfaid.data.network.model.EmotionDtoMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

//    @Singleton
//    @Provides
//    fun provideRecipeRepository(
//        emotionApiService: EmotionApiService,
//        emotionDtoMapper: EmotionDtoMapper
//    ): RecipeRepository{
//        return RecipeRepository_Impl(
//            recipeService = recipeService,
//            mapper = recipeMapper
//        )
//    }
}