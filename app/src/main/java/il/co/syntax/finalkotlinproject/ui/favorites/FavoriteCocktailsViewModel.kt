package il.co.syntax.finalkotlinproject.ui.favorites

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.syntax.finalkotlinproject.data.models.AllCocktailResults
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.data.repository.CocktailRepository
import il.co.syntax.finalkotlinproject.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCocktailsViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {

    val cocktails  = cocktailRepository.getFavoritesCocktailsFromLocal()

    fun deleteCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            cocktailRepository.deleteCocktailFromLocal(cocktail)
        }
    }
}