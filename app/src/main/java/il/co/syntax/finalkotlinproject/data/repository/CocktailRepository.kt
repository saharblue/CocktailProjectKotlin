package il.co.syntax.finalkotlinproject.data.repository

import il.co.syntax.finalkotlinproject.data.loca_db.CocktailDao
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.data.remote_db.CocktailRemoteDataSource
import il.co.syntax.finalkotlinproject.utils.performFetchingFromRemote
import il.co.syntax.finalkotlinproject.utils.performFetchingAndSaving
import il.co.syntax.finalkotlinproject.utils.performFetchingFromLocal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailRepository @Inject constructor(
    private val remoteDataSource : CocktailRemoteDataSource,
    private val localDataSource : CocktailDao,
){

    fun getCocktails() = performFetchingAndSaving(
        {localDataSource.getAllCocktails()},
        {remoteDataSource.getCocktails()},
        {localDataSource.insertCocktails(it.drinks)}
    )

    fun getCocktail(id : Int) = performFetchingAndSaving(
        {localDataSource.getCocktail(id)},
        {remoteDataSource.getCocktail(id)},
        {localDataSource.insertCocktail(it.drinks[0])}
    )

    fun getCocktailsByNameFromRemote(name : String) = performFetchingFromRemote {
        remoteDataSource.getCocktailsByName(name)
    }

    fun getCocktailsByIdFromRemote(id : Int) = performFetchingFromRemote {
        remoteDataSource.getCocktail(id)
    }

    fun getCocktailsByIngredientFromRemote(name : String) = performFetchingFromRemote {
        remoteDataSource.getCocktailsByIngredient(name)
    }

    suspend fun insertCocktailToLocal(cocktail: Cocktail) = localDataSource.insertCocktail(cocktail)

    suspend fun deleteCocktailFromLocal(cocktail: Cocktail) = localDataSource.deleteCocktail(cocktail)

    fun getFavoritesCocktailsFromLocal() = performFetchingFromLocal {
        localDataSource.getAllCocktails()
    }

    fun getRandomCocktailFromRemote() = performFetchingFromRemote {
        remoteDataSource.getRandomCocktail()
    }
}