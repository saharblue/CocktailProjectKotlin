package il.co.syntax.finalkotlinproject.ui.detailed_favorite_cocktail

import androidx.lifecycle.*
import il.co.syntax.finalkotlinproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.data.repository.CocktailRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedFavoriteCocktailViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository): ViewModel(){
    private val _id = MutableLiveData<Int>()

    private val _cocktail = _id.switchMap { id ->
        cocktailRepository.getCocktailFromLocal(id)
    }

    val cocktail : LiveData<Resource<Cocktail>> = _cocktail

    fun setId(id: Int) {
        _id.value = id
    }

    fun deleteCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            cocktailRepository.deleteCocktailFromLocal(cocktail)
        }
    }
}