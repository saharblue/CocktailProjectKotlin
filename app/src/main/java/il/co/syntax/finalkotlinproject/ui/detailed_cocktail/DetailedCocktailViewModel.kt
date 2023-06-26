package il.co.syntax.finalkotlinproject.ui.detailed_cocktail

import androidx.lifecycle.*
import il.co.syntax.finalkotlinproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.data.models.DetailedCocktail
import il.co.syntax.finalkotlinproject.data.repository.CocktailRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedCocktailViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository): ViewModel(){
    private val _id = MutableLiveData<Int>()

    private val _cocktail = _id.switchMap { id ->
        if (id != -1) {
            cocktailRepository.getCocktailsByIdFromRemote(id)
        } else {
            cocktailRepository.getRandomCocktailFromRemote()
        }
    }

    val cocktail : LiveData<Resource<DetailedCocktail>> = _cocktail

    fun setId(id: Int) {
        _id.value = id
    }

    fun addToFavorites(cocktail: Cocktail) {
        viewModelScope.launch {
            cocktailRepository.insertCocktailToLocal(cocktail)
        }
    }
}