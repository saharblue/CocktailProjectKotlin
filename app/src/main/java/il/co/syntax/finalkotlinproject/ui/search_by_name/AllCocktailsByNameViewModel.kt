package il.co.syntax.finalkotlinproject.ui.search_by_name

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
class AllCocktailsByNameViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {
    private val _name = MutableLiveData<String>()


    private val _cocktails = _name.switchMap {
        cocktailRepository.getCocktailsByNameFromRemote(it)
    }

    val cocktails : LiveData<Resource<AllCocktailResults>> = _cocktails


    fun setName(name: String) {
        _name.value = name
    }
}