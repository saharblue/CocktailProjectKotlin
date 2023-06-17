package il.co.syntax.finalkotlinproject.ui.all_cocktails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.databinding.CocktailsFragmentBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

class AllCocktailsFragment : Fragment(), CocktailsAdapter.CharacterItemListener {

    private var binding : CocktailsFragmentBinding by autoCleared()

    private  lateinit var  adapter: CocktailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CocktailsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CocktailsAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter


        //TODO: observe your data
    }

    override fun onCharacterClick(characterId: Int) {
       findNavController().navigate(R.id.action_allCharactersFragment_to_singleCharacterFragment,
           bundleOf("id" to characterId))
    }
}