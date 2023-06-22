package il.co.syntax.finalkotlinproject.ui.search_by_name

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
import il.co.syntax.finalkotlinproject.databinding.CocktailsByNameFragmentBinding
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
        binding.searchName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().length > 2)
                {
                    viewModel.setName(p0.toString())
                }
            }
        })
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CocktailsByNameAdapter(this)
        binding.cocktailsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.cocktailsRv.adapter = adapter

        viewModel.cocktails.observe(viewLifecycleOwner) {
            when(it.status) {
                is Loading -> binding.progressBar.visibility = View.VISIBLE

                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    it.status.data!!.drinks?.let {
                        adapter.setCocktails(it)
                    }
                }

                is Error -> {
                    binding.progressBar.visibility = View.GONE
                    println(it.status.message)
                    Toast.makeText(requireContext(),it.status.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        arguments?.getString("cocktailName")?.let {
            viewModel.setName(it)
        }
    }

    override fun onCocktailClick(cocktailId: Int){
        findNavController().navigate(R.id.action_allCocktailsByNameFragment_to_detailedCocktailFragment,
            bundleOf("idDrink" to cocktailId))
    }
}