package il.co.syntax.finalkotlinproject.data.repository

import il.co.syntax.finalkotlinproject.data.loca_db.CocktailDao
import il.co.syntax.finalkotlinproject.data.loca_db.IngredientResultsDao
import il.co.syntax.finalkotlinproject.data.remote_db.CocktailRemoteDataSource
import il.co.syntax.finalkotlinproject.utils.performFetchingFromRemote
import il.co.syntax.finalkotlinproject.utils.performFetchingAndSaving
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailRepository @Inject constructor(
    private val remoteDataSource : CocktailRemoteDataSource,
    private val localDataSource : CocktailDao,
    private val ingredientLocalDataSource : IngredientResultsDao
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


    fun getCocktailsByIngredient(name : String) = performFetchingAndSaving(
        {
            ingredientLocalDataSource.getAllResults()},
        {
            withContext(Dispatchers.IO) {
                ingredientLocalDataSource.deleteAllResults()
            }
            remoteDataSource.getCocktailsByIngredient(name)
        },
        {ingredientLocalDataSource.insertResults(it.drinks)},
    )


}