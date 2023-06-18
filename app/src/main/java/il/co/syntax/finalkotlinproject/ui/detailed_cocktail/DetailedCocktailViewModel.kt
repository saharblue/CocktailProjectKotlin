package il.co.syntax.finalkotlinproject.ui.detailed_cocktail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import il.co.syntax.finalkotlinproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.data.repository.CocktailRepository
import javax.inject.Inject

@HiltViewModel
class DetailedCocktailViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository): ViewModel(){
    private val _id = MutableLiveData<Int>()

    private val _cocktail = _id.switchMap {
        cocktailRepository.getCocktail(it)
    }

    val cocktail : LiveData<Resource<Cocktail>> = _cocktail

    fun setId(id: Int) {
        _id.value = id
    }
}