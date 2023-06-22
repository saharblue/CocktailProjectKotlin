package il.co.syntax.finalkotlinproject.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.syntax.finalkotlinproject.data.models.AllCocktailResults
import il.co.syntax.finalkotlinproject.data.repository.CocktailRepository
import il.co.syntax.finalkotlinproject.utils.Resource
import javax.inject.Inject

@HiltViewModel
class FavoriteCocktailsViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {

    val cocktails  = cocktailRepository.getFavoritesCocktailsFromLocal()
}