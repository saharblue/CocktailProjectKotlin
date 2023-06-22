package il.co.syntax.finalkotlinproject.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "ingredient_result")
data class IngredientResult (
    val strDrink : String,
    @SerializedName("strDrinkThumb")
    val image : String?,
    @PrimaryKey
    val idDrink : Int,
)