package il.co.syntax.finalkotlinproject.ui.search_by_ingredient

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        displayNone()
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

                is Error ->  {
                    if(it.status.message == getString(R.string.no_internet_connection_message_from_api)) {
                        networkError()
                    }
                    else {
                        noResults()
                    }
                }
            }
        }

        arguments?.getString("cocktailName")?.let {
            viewModel.setName(it)
        }

        binding.cocktailsRv.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Hide the keyboard
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        })
    }

    private fun showItems()
    {
        binding.noConnectionVector.visibility = View.GONE
        binding.noConnectionTitle.visibility = View.GONE
        binding.cocktailsRv.visibility = View.VISIBLE
        binding.noResultsTitle.visibility = View.GONE
        binding.cocktailVector.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }

    private fun noResults()
    {
        binding.noConnectionVector.visibility = View.GONE
        binding.noConnectionTitle.visibility = View.GONE
        binding.cocktailsRv.visibility = View.GONE
        binding.noResultsTitle.visibility = View.VISIBLE
        binding.cocktailVector.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun loadingResults()
    {
        binding.noConnectionVector.visibility = View.GONE
        binding.noConnectionTitle.visibility = View.GONE
        binding.cocktailVector.visibility = View.GONE
        binding.cocktailsRv.visibility = View.GONE
        binding.noResultsTitle.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun networkError()
    {
        binding.noConnectionVector.visibility = View.VISIBLE
        binding.noConnectionTitle.visibility = View.VISIBLE
        binding.cocktailVector.visibility = View.GONE
        binding.cocktailsRv.visibility = View.GONE
        binding.noResultsTitle.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }

    private fun displayNone()
    {
        binding.noConnectionVector.visibility = View.GONE
        binding.noConnectionTitle.visibility = View.GONE
        binding.cocktailVector.visibility = View.GONE
        binding.cocktailsRv.visibility = View.GONE
        binding.noResultsTitle.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }

    override fun onCocktailClick(cocktailId: Int){
        findNavController().navigate(R.id.action_allCocktailsByIngredientFragment_to_detailedCocktailFragment,
            bundleOf("idDrink" to cocktailId))
    }
}