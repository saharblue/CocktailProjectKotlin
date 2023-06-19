package il.co.syntax.finalkotlinproject.ui.search_by_name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.databinding.CocktailDetailFragmentBinding
import il.co.syntax.finalkotlinproject.databinding.SearchByNameFragmentBinding
import il.co.syntax.finalkotlinproject.ui.all_cocktails.CocktailsAdapter
import il.co.syntax.finalkotlinproject.ui.detailed_cocktail.DetailedCocktailViewModel
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class SearchByNameFragment :  Fragment() {

    private var binding: SearchByNameFragmentBinding by autoCleared()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchByNameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            onButtonClick(binding.searchCocktail.text.toString())
        }
    }

     private fun onButtonClick(cocktailName: String){
        findNavController().navigate(
            R.id.action_allCocktailsFragment_to_detailedCocktailFragment2,
            bundleOf("cocktailName" to cocktailName))
    }

}
