package il.co.syntax.finalkotlinproject.ui.search_by_name

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
import il.co.syntax.finalkotlinproject.databinding.CocktailsByNameFragmentBinding
import il.co.syntax.finalkotlinproject.databinding.CocktailsFragmentBinding
import il.co.syntax.finalkotlinproject.ui.all_cocktails.CocktailsAdapter
import il.co.syntax.finalkotlinproject.utils.Error
import il.co.syntax.finalkotlinproject.utils.Loading
import il.co.syntax.finalkotlinproject.utils.Success
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class AllCocktailsByNameFragment : Fragment(), CocktailsByNameAdapter.CocktailItemListener {

    private val viewModel : AllCocktailsByNameViewModel by viewModels()

    private var binding : CocktailsByNameFragmentBinding by autoCleared()

    private  lateinit var  adapter: CocktailsByNameAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CocktailsByNameFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cocktailName = arguments?.getString("cocktailName") ?: ""
        viewModel.setName(cocktailName)

        adapter = CocktailsByNameAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter

        viewModel.cocktails.observe(viewLifecycleOwner) {
            when(it.status) {
                is Loading -> binding.progressBar.visibility = View.VISIBLE

                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.setCocktails(it.status.data!!)
                }

                is Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),it.status.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCocktailClick(cocktailId: Int){
        findNavController().navigate(R.id.action_allCocktailsFragment_to_detailedCocktailFragment2,
            bundleOf("idDrink" to cocktailId))
    }
}