package il.co.syntax.finalkotlinproject.data.repository

import il.co.syntax.finalkotlinproject.data.loca_db.CocktailDao
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.data.remote_db.CocktailRemoteDataSource
import il.co.syntax.finalkotlinproject.utils.performFetchingFromRemote
import il.co.syntax.finalkotlinproject.utils.performFetchingFromLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailRepository @Inject constructor(
    private val remoteDataSource : CocktailRemoteDataSource,
    private val localDataSource : CocktailDao,
){

    fun getCocktailsByNameFromRemote(name : String) = performFetchingFromRemote {
        remoteDataSource.getCocktailsByName(name)
    }

    fun getCocktailsByIdFromRemote(id : Int) = performFetchingFromRemote {
        remoteDataSource.getCocktail(id)
    }

    fun getCocktailsByIngredientFromRemote(name : String) = performFetchingFromRemote {
        remoteDataSource.getCocktailsByIngredient(name)
    }

    suspend fun insertCocktailToLocal(cocktail: Cocktail) = withContext(Dispatchers.IO) {
        localDataSource.insertCocktail(cocktail)
    }

    suspend fun deleteCocktailFromLocal(cocktail: Cocktail) = withContext(Dispatchers.IO) {
        localDataSource.deleteCocktail(cocktail)
    }

    fun getCocktailFromLocal(id: Int) = performFetchingFromLocal {
        localDataSource.getCocktail(id)
    }

    fun getFavoritesCocktailsFromLocal() = performFetchingFromLocal {
        localDataSource.getAllCocktails()
    }

    fun getRandomCocktailFromRemote() = performFetchingFromRemote {
        remoteDataSource.getRandomCocktail()
    }
}