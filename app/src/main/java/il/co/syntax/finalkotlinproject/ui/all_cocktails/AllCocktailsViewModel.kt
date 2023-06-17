package il.co.syntax.finalkotlinproject.ui.all_cocktails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import il.co.syntax.finalkotlinproject.data.repository.CocktailRepository
import javax.inject.Inject

@HiltViewModel
class AllCocktailsViewModel @Inject constructor(
    cocktailRepository: CocktailRepository
) : ViewModel() {

    val cocktails  = cocktailRepository.getCocktails()
}