package il.co.syntax.finalkotlinproject.ui.detailed_cocktail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import il.co.syntax.finalkotlinproject.databinding.CharacterDetailFragmentBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

class DetailedCocktailFragment : Fragment() {


    private var binding: CharacterDetailFragmentBinding by autoCleared()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //TODO: Observe your character Livedata in your ViewModel

        arguments?.getInt("id")?.let {

            //TODO: update the view model with the id data to trigger the character observer
        }
    }

    private fun updateCharacter(character: Character) {

        //TODO:Uncomment these lines to update the ui with the character
        /*
         binding.name.text = character.name
        binding.gender.text = character.gender
        binding.species.text = character.species
        binding.status.text = character.status
        Glide.with(requireContext()).load(character.picture).circleCrop().into(binding.image)
         */
    }
}