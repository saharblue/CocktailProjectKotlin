package il.co.syntax.finalkotlinproject.ui.search_by_ingredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.databinding.SearchByIngredientFragmentBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class SearchByIngredientFragment : Fragment() {

    private var binding: SearchByIngredientFragmentBinding by autoCleared()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchByIngredientFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}