package il.co.syntax.finalkotlinproject.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    ): View {
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
                    if (it.status.data!!.isNotEmpty())
                    {
                        showItems()
                        adapter.setCocktails(it.status.data)
                    }
                    else
                    {
                        noResults()
                    }

                }

                is Error -> {
                    binding.progressBar.visibility = View.GONE
                    println(it.status.message)
                    Toast.makeText(requireContext(),it.status.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        ItemTouchHelper(object : ItemTouchHelper.Callback() {

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            )= makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val builder = AlertDialog.Builder(requireContext())

                builder.setTitle(getString(R.string.delete_dialog_title))
                builder.setMessage(getString(R.string.delete_dialog_message))
                builder.setPositiveButton(getString(R.string.delete_dialog_pos)) { dialog, _ ->
                    dialog.dismiss()
                    viewModel.deleteCocktail((binding.favoritesRv.adapter as FavoriteCocktailsAdapter)
                        .cocktailAt(viewHolder.adapterPosition))
                    binding.favoritesRv.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
                }
                builder.setNegativeButton(getString(R.string.delete_dialog_neg)) { dialog, _ ->
                    dialog.dismiss()
                    binding.favoritesRv.adapter!!.notifyItemChanged(viewHolder.adapterPosition)
                }
                val alertDialog = builder.create()
                alertDialog.show()
            }
        }).attachToRecyclerView(binding.favoritesRv)

    }

    private fun showItems()
    {
        binding.favoritesRv.visibility = View.VISIBLE
        binding.noResultsTitle.visibility = View.GONE
        binding.cocktailVector.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }

    private fun noResults()
    {
        binding.favoritesRv.visibility = View.GONE
        binding.noResultsTitle.visibility = View.VISIBLE
        binding.cocktailVector.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    override fun onCocktailClick(cocktailId: Int){
        findNavController().navigate(R.id.action_favoriteCocktailsFragment_to_detailedFavoriteCocktailFragment,
            bundleOf("idDrink" to cocktailId))
    }
}