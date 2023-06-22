package il.co.syntax.finalkotlinproject.ui.favorites

import android.os.Bundle
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
import il.co.syntax.finalkotlinproject.databinding.FavoriteCocktailsFragmentBinding
import il.co.syntax.finalkotlinproject.utils.Error
import il.co.syntax.finalkotlinproject.utils.Loading
import il.co.syntax.finalkotlinproject.utils.Success
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class FavoriteCocktailsFragment : Fragment(), FavoriteCocktailsAdapter.CocktailItemListener {

    private val viewModel : FavoriteCocktailsViewModel by viewModels()

    private var binding : FavoriteCocktailsFragmentBinding by autoCleared()

    private  lateinit var  adapter: FavoriteCocktailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoriteCocktailsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteCocktailsAdapter(this)
        binding.favoritesRv.layoutManager = LinearLayoutManager(requireContext())
        binding.favoritesRv.adapter = adapter

        viewModel.cocktails.observe(viewLifecycleOwner) {
            when(it.status) {
                is Loading -> binding.progressBar.visibility = View.VISIBLE

                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.setCocktails(it.status.data!!)
                }

                is Error -> {
                    binding.progressBar.visibility = View.GONE
                    println(it.status.message)
                    Toast.makeText(requireContext(),it.status.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    override fun onCocktailClick(cocktailId: Int){
        findNavController().navigate(R.id.action_allCocktailsByNameFragment_to_detailedCocktailFragment,
            bundleOf("idDrink" to cocktailId))
    }
}