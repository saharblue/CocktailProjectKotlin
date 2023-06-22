package il.co.syntax.finalkotlinproject.ui.random_cocktail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GetRandomCocktailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rotateAnimation = RotateAnimation(
            0f,  // From degree
            360f,  // To degree
            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point X
            Animation.RELATIVE_TO_SELF, 0.5f  // Pivot point Y
        ).apply {
            duration = 2000  // Animation duration in milliseconds (2 seconds here)
            repeatCount = Animation.INFINITE  // The animation will repeat indefinitely
            interpolator = LinearInterpolator()  // Keeps the speed constant
        }

        binding.randomizeButtonImage.setOnClickListener {
            // Hide the image, show the progress bar


            // Start the rotation animation
            binding.randomizeButtonImage.startAnimation(rotateAnimation)

            // After 2-3 seconds, navigate away or do other things
            Handler(Looper.getMainLooper()).postDelayed({
                // Hide the progress bar, show the image

                // Stop the rotation animation
                binding.randomizeButtonImage.clearAnimation()

                // Navigate away or do other things here...
                onButtonClick(-1)
            }, 2000)  // Duration of the delay in milliseconds

        }

    }



    private fun onButtonClick(idDrink: Int){
        findNavController().navigate(
            R.id.action_getRandomCocktailFragment_to_detailedCocktailFragment,
            bundleOf("idDrink" to idDrink)
        )
    }

}