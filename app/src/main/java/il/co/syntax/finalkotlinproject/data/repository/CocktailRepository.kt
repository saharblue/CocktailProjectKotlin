package il.co.syntax.finalkotlinproject.data.repository

import il.co.syntax.finalkotlinproject.data.loca_db.CocktailDao
import il.co.syntax.finalkotlinproject.data.remote_db.CocktailRemoteDataSource
import il.co.syntax.finalkotlinproject.utils.performFetchingAndSaving
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailRepository @Inject constructor(
    private val remoteDataSource : CocktailRemoteDataSource,
    private val localDataSource : CocktailDao
){

    fun getCocktails() = performFetchingAndSaving(
        {localDataSource.getAllCocktails()},
        {remoteDataSource.getCocktails()},
        {localDataSource.insertCocktails(it.results)}
    )

    fun getCocktail(id : Int) = performFetchingAndSaving(
        {localDataSource.getCocktail(id)},
        {remoteDataSource.getCocktail(id)},
        {localDataSource.insertCocktail(it)}
    )
}