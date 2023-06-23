package il.co.syntax.finalkotlinproject.data.loca_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.data.models.IngredientResult

@Database(entities = [Cocktail::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cocktailDao() : CocktailDao

    companion object {

        @Volatile
        private var instance : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase =
            instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"cocktails")
                    .fallbackToDestructiveMigration().build().also {
                        instance = it
                    }
            }
    }
}