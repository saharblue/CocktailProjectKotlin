package il.co.syntax.finalkotlinproject.ui.random_cocktail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.databinding.GetRandomCocktailFragmentBinding
import il.co.syntax.fullarchitectureretrofithiltkotlin.utils.autoCleared

@AndroidEntryPoint
class GetRandomCocktailFragment : Fragment() {

    private var binding: GetRandomCocktailFragmentBinding by autoCleared()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GetRandomCocktailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rotateAnimation = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 2000
            repeatCount = 0
            interpolator = AccelerateInterpolator() // To get the speed up effect
        }

        val scaleAnimation = ScaleAnimation(
            1f, 0f, // Start and end scales for the X axis
            1f, 0f, // Start and end scales for the Y axis
            Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point X
            Animation.RELATIVE_TO_SELF, 0.5f // Pivot point Y
        ).apply {
            duration = 2000 // The duration in milliseconds
            repeatCount = 0
            interpolator = AccelerateInterpolator() // To get the speed up effect
        }

        // Create a set and add both animations
        val animationSet = AnimationSet(true).apply {
            addAnimation(rotateAnimation)
            addAnimation(scaleAnimation)
        }

        binding.randomizeButtonImage.setOnClickListener {
            binding.randomizeButtonImage.startAnimation(animationSet)

            handler.postDelayed({
                binding.randomizeButtonImage.visibility = View.GONE
                binding.getRandomCocktailTitle.visibility = View.GONE
                binding.randomizeButtonImage.clearAnimation()
                // Navigate away or do other things here...
                onButtonClick()
            }, 2000)
        }
    }

    private fun onButtonClick(){
        findNavController().navigate(
            R.id.action_getRandomCocktailFragment_to_detailedCocktailFragment,
            bundleOf("idDrink" to -1)
        )
    }

}