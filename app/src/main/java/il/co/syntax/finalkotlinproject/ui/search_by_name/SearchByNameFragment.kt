package il.co.syntax.finalkotlinproject.ui.search_by_name

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
         // Hide the keyboard
         val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
         imm?.hideSoftInputFromWindow(binding.root.windowToken, 0)

        findNavController().navigate(
            R.id.action_searchByNameFragment_to_allCocktailsByNameFragment2,
            bundleOf("cocktailName" to cocktailName))
    }

}
