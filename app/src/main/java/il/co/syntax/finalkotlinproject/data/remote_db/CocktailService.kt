package il.co.syntax.finalkotlinproject.data.remote_db

import il.co.syntax.finalkotlinproject.data.models.AllCocktailResults
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CocktailService {

    @GET("search.php?s=margarita")
    suspend fun getAllCocktails() : Response<AllCocktailResults>

    @GET("lookup.php?i={id}")
    suspend fun getCocktail(@Path("id") idDrink : Int) : Response<Cocktail>
}