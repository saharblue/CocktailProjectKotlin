package il.co.syntax.finalkotlinproject.ui.random_cocktail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.databinding.GetRandomCocktailFragmentBinding
import il.co.syntax.finalkotlinproject.databinding.SearchByNameFragmentBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class GetRandomCocktailFragment : Fragment() {

    private var binding: GetRandomCocktailFragmentBinding by autoCleared()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GetRandomCocktailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.randomizeButton.setOnClickListener {
            onButtonClick(binding.randomizeButton.text.toString())
        }
    }



    private fun onButtonClick(cocktailName: String){
        // Hide the keyboard
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(binding.root.windowToken, 0)

        findNavController().navigate(
            R.id.action_searchByIngredientFragment_to_allCocktailsByIngredientFragment2,
            bundleOf("cocktailName" to cocktailName)
        )
    }

}