package il.co.syntax.finalkotlinproject.data.loca_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.data.models.IngredientResult

@Dao
interface IngredientResultsDao {

    @Query("SELECT * FROM ingredient_result")
    fun getAllResults() : LiveData<List<IngredientResult>>

    @Query("SELECT * FROM ingredient_result WHERE idDrink = :id")
    fun getResult(id : Int) : LiveData<IngredientResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: IngredientResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResults(result : List<IngredientResult>)

    @Query("DELETE FROM ingredient_result")
    suspend fun deleteAllResults()
}