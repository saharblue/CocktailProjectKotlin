package il.co.syntax.finalkotlinproject.ui.detailed_favorite_cocktail

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.databinding.CocktailFavoriteDetailFragmentBinding
import il.co.syntax.finalkotlinproject.databinding.CustomToastLayoutBinding
import il.co.syntax.finalkotlinproject.utils.Error
import il.co.syntax.finalkotlinproject.utils.Loading
import il.co.syntax.finalkotlinproject.utils.Success
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class DetailedFavoriteCocktailFragment : Fragment() {

    private val viewModel : DetailedFavoriteCocktailViewModel by viewModels()
    private var binding: CocktailFavoriteDetailFragmentBinding by autoCleared()
    private lateinit var cocktail: Cocktail


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CocktailFavoriteDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.cocktail.observe(viewLifecycleOwner) {

            when(it.status) {
                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    if (it.status.data != null)
                    {
                        updateCocktail(it.status.data)
                        cocktail = it.status.data
                    }
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

        binding.removeFromFavoritesButton.setOnClickListener {
            deleteFromFavoritesAction()
        }

    }

    private fun deleteFromFavoritesAction()
    {
        viewModel.deleteCocktail(cocktail)
        binding.removeFromFavoritesButton.isEnabled = false
        binding.removeFromFavoritesButton.setBackgroundColor(Color.parseColor("#D3D3D3"))
        val inflater = layoutInflater
        val binding = CustomToastLayoutBinding.inflate(inflater)

        binding.toastMessage.text = "This is a custom toast with vector image."
        binding.toastIcon.setImageResource(R.drawable.cocktail_with_empty_heart)

        val toast = Toast(requireContext())
        toast.duration = Toast.LENGTH_SHORT
        toast.view = binding.root

        // Position the toast in the middle of the screen
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()

    }


    private fun updateCocktail(cocktail: Cocktail) {
        val measures = arrayOf(cocktail.strMeasure1, cocktail.strMeasure2, cocktail.strMeasure3, cocktail.strMeasure4, cocktail.strMeasure5,
            cocktail.strMeasure6, cocktail.strMeasure7, cocktail.strMeasure8, cocktail.strMeasure9, cocktail.strMeasure10,
            cocktail.strMeasure11, cocktail.strMeasure12, cocktail.strMeasure13, cocktail.strMeasure14, cocktail.strMeasure15)

        val ingredients = arrayOf(cocktail.strIngredient1, cocktail.strIngredient2, cocktail.strIngredient3, cocktail.strIngredient4, cocktail.strIngredient5,
            cocktail.strIngredient6, cocktail.strIngredient7, cocktail.strIngredient8, cocktail.strIngredient9, cocktail.strIngredient10,
            cocktail.strIngredient11, cocktail.strIngredient12, cocktail.strIngredient13, cocktail.strIngredient14, cocktail.strIngredient15)

        var ingredientsStr = ""
        val formatString = "%s %s\n"
        for (i in measures.indices) {
            if (measures[i] != null && ingredients[i] != null) {
                ingredientsStr += String.format(formatString, measures[i], ingredients[i])
            }
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