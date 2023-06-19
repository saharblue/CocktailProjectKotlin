package il.co.syntax.finalkotlinproject.data.remote_db

import il.co.syntax.finalkotlinproject.data.models.AllCocktailResults
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.data.models.DetailedCocktail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CocktailService {

    @GET("search.php?s=margarita")
    suspend fun getAllCocktails() : Response<AllCocktailResults>

    @GET("lookup.php")
    suspend fun getCocktail(@Query("i") id : Int) : Response<DetailedCocktail>

    @GET("search.php")
    suspend fun getCocktailsByName(@Query("s") name : String) : Response<AllCocktailResults>

}