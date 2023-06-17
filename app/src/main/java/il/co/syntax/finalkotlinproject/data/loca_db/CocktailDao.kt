package il.co.syntax.finalkotlinproject.data.loca_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import il.co.syntax.finalkotlinproject.data.models.Cocktail

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktails")
    fun getAllCocktails() : LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktails WHERE id = :id")
    fun getCharacter(id : Int) : LiveData<Cocktail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(cocktail: Cocktail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktails(cocktail : List<Cocktail>)
}