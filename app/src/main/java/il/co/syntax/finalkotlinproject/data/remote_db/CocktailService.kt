package il.co.syntax.finalkotlinproject.data.remote_db

import il.co.syntax.finalkotlinproject.data.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CocktailService {

    @GET("random.php")
    suspend fun getRandomCocktail() : Response<DetailedCocktail>

    @GET("lookup.php")
    suspend fun getCocktail(@Query("i") id : Int) : Response<DetailedCocktail>

    @GET("search.php")
    suspend fun getCocktailsByName(@Query("s") name : String) : Response<AllCocktailResults>

    @GET("filter.php")
    suspend fun getCocktailsByIngredient(@Query("i") name : String) : Response<IngredientSearchResults>

}