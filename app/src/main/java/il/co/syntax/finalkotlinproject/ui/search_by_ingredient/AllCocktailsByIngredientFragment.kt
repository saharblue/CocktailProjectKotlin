package il.co.syntax.finalkotlinproject.ui.search_by_ingredient

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.databinding.CocktailsByIngredientFragmentBinding
import il.co.syntax.finalkotlinproject.utils.Error
import il.co.syntax.finalkotlinproject.utils.Loading
import il.co.syntax.finalkotlinproject.utils.Success
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class AllCocktailsByIngredientFragment : Fragment(), CocktailsByIngredientAdapter.CocktailItemListener {

    private val viewModel : AllCocktailsByIngredientViewModel by viewModels()

    private var binding : CocktailsByIngredientFragmentBinding by autoCleared()

    private  lateinit var  adapter: CocktailsByIngredientAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CocktailsByIngredientFragmentBinding.inflate(inflater,container,false)
        binding.searchIngredient.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.setName(p0.toString())
            }
        })

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CocktailsByIngredientAdapter(this)
        binding.cocktailsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.cocktailsRv.adapter = adapter

        viewModel.cocktails.observe(viewLifecycleOwner) {
            when(it.status) {
                is Loading -> loadingResults()

                is Success -> {
                    if (it.status.data != null)
                    {
                        showItems()
                        adapter.setCocktails(it.status.data.drinks)
                    }
                    else
                    {
                        noResults()
                    }

                }

                is Error -> noResults()
            }
        }

        arguments?.getString("cocktailName")?.let {
            viewModel.setName(it)
        }
    }

    private fun showItems()
    {
        binding.cocktailsRv.visibility = View.VISIBLE
        binding.noResultsTitle.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }

    private fun noResults()
    {
        binding.cocktailsRv.visibility = View.GONE
        binding.noResultsTitle.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun loadingResults()
    {
        binding.cocktailsRv.visibility = View.GONE
        binding.noResultsTitle.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onCocktailClick(cocktailId: Int){
        findNavController().navigate(R.id.action_allCocktailsByIngredientFragment_to_detailedCocktailFragment,
            bundleOf("idDrink" to cocktailId))
    }
}