package il.co.syntax.finalkotlinproject.ui.search_by_name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.databinding.CocktailDetailFragmentBinding
import il.co.syntax.finalkotlinproject.databinding.SearchByNameFragmentBinding
import il.co.syntax.finalkotlinproject.ui.detailed_cocktail.DetailedCocktailViewModel
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class SearchByNameFragment : Fragment() {

    private var binding: SearchByNameFragmentBinding by autoCleared()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchByNameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}
