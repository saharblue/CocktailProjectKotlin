package il.co.syntax.finalkotlinproject.ui.detailed_cocktail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.databinding.CocktailDetailFragmentBinding
import il.co.syntax.finalkotlinproject.utils.Error
import il.co.syntax.finalkotlinproject.utils.Loading
import il.co.syntax.finalkotlinproject.utils.Success
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class DetailedCocktailFragment : Fragment() {

    private val viewModel : DetailedCocktailViewModel by viewModels()
    private var binding: CocktailDetailFragmentBinding by autoCleared()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CocktailDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.cocktail.observe(viewLifecycleOwner) {
            when(it.status) {
                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    updateCocktail(it.status.data!!)
                    binding.cocktailCl.visibility = View.VISIBLE
                }
                is Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.cocktailCl.visibility = View.GONE
                }
                is Error -> {
                    binding.progressBar.visibility = View.GONE
                    println(it.status.message)
                    Toast.makeText(requireContext(),it.status.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        arguments?.getInt("idDrink")?.let {

            viewModel.setId(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateCocktail(cocktail: Cocktail) {

        binding.name.text = cocktail.strDrink
        binding.cocktailName.text = cocktail.strDrink
        binding.cocktailType.text = cocktail.strAlcoholic
        binding.cocktailGlass.text = cocktail.strGlass
        binding.cocktailIngredients.text = "${cocktail.strMeasure1} ${cocktail.strIngredient1}"
        binding.cocktailInstructions.text = cocktail.strInstructions
        Glide.with(requireContext()).load(cocktail.image).circleCrop().into(binding.image)
    }
}