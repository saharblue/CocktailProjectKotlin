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
    private lateinit var cocktail: Cocktail


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
                    updateCocktail(it.status.data!!.drinks[0])
                    cocktail = it.status.data.drinks[0]
                    binding.cocktailCl.visibility = View.VISIBLE
                }
                is Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.cocktailCl.visibility = View.GONE
                }
                is Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),it.status.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        arguments?.getInt("idDrink")?.let {

            viewModel.setId(it)
        }

        binding.addToFavoritesButton.setOnClickListener {
            viewModel.addToFavorites(cocktail)
        }

    }


    @SuppressLint("SetTextI18n")
    private fun updateCocktail(cocktail: Cocktail) {

        var ingredientsStr = ""

        val measure1 = cocktail.strMeasure1
        val ingredient1 = cocktail.strIngredient1

        if (measure1 != null && ingredient1 != null) {
            ingredientsStr += "$measure1 $ingredient1\n"
        }

        val measure2 = cocktail.strMeasure2
        val ingredient2 = cocktail.strIngredient2

        if (measure2 != null && ingredient2 != null) {
            ingredientsStr += "$measure2 $ingredient2\n"
        }

        val measure3 = cocktail.strMeasure3
        val ingredient3 = cocktail.strIngredient3

        if (measure3 != null && ingredient3 != null) {
            ingredientsStr += "$measure3 $ingredient3\n"
        }

        val measure4 = cocktail.strMeasure4
        val ingredient4 = cocktail.strIngredient4
        if (measure4 != null && ingredient4 != null) {
            ingredientsStr += "$measure4 $ingredient4\n"
        }

        val measure5 = cocktail.strMeasure5
        val ingredient5 = cocktail.strIngredient5
        if (measure5 != null && ingredient5 != null) {
            ingredientsStr += "$measure5 $ingredient5\n"
        }

        val measure6 = cocktail.strMeasure6
        val ingredient6 = cocktail.strIngredient6
        if (measure6 != null && ingredient6 != null) {
            ingredientsStr += "$measure6 $ingredient6\n"
        }

        val measure7 = cocktail.strMeasure7
        val ingredient7 = cocktail.strIngredient7
        if (measure7 != null && ingredient7 != null) {
            ingredientsStr += "$measure7 $ingredient7\n"
        }

        val measure8 = cocktail.strMeasure8
        val ingredient8 = cocktail.strIngredient8
        if (measure8 != null && ingredient8 != null) {
            ingredientsStr += "$measure8 $ingredient8\n"
        }

        val measure9 = cocktail.strMeasure9
        val ingredient9 = cocktail.strIngredient9
        if (measure9 != null && ingredient9 != null) {
            ingredientsStr += "$measure9 $ingredient9\n"
        }

        val measure10 = cocktail.strMeasure10
        val ingredient10 = cocktail.strIngredient10
        if (measure10 != null && ingredient10 != null) {
            ingredientsStr += "$measure10 $ingredient10\n"
        }

        val measure11 = cocktail.strMeasure11
        val ingredient11 = cocktail.strIngredient11
        if (measure11 != null && ingredient11 != null) {
            ingredientsStr += "$measure11 $ingredient11\n"
        }

        val measure12 = cocktail.strMeasure12
        val ingredient12 = cocktail.strIngredient12
        if (measure12 != null && ingredient12 != null) {
            ingredientsStr += "$measure12 $ingredient12\n"
        }

        val measure13 = cocktail.strMeasure13
        val ingredient13 = cocktail.strIngredient13
        if (measure13 != null && ingredient13 != null) {
            ingredientsStr += "$measure13 $ingredient13\n"
        }

        val measure14 = cocktail.strMeasure14
        val ingredient14 = cocktail.strIngredient14
        if (measure14 != null && ingredient14 != null) {
            ingredientsStr += "$measure14 $ingredient14\n"
        }

        val measure15 = cocktail.strMeasure15
        val ingredient15 = cocktail.strIngredient15
        if (measure15 != null && ingredient15 != null) {
            ingredientsStr += "$measure15 $ingredient15\n"
        }

        binding.name.text = cocktail.strDrink
        binding.cocktailName.text = cocktail.strDrink
        binding.cocktailType.text = cocktail.strAlcoholic
        binding.cocktailGlass.text = cocktail.strGlass
        binding.cocktailIngredients1.text = ingredientsStr
        binding.cocktailInstructions.text = cocktail.strInstructions
        Glide.with(requireContext()).load(cocktail.image).circleCrop().into(binding.image)
    }
}