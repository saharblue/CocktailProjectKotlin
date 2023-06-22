package il.co.syntax.finalkotlinproject.ui.search_by_ingredient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.data.models.IngredientResult
import il.co.syntax.finalkotlinproject.databinding.ItemCocktailBinding
import il.co.syntax.finalkotlinproject.databinding.ItemCocktailByIngredientBinding
import il.co.syntax.finalkotlinproject.databinding.ItemCocktailByNameBinding

class CocktailsByIngredientAdapter(private val listener : CocktailItemListener) :
    RecyclerView.Adapter<CocktailsByIngredientAdapter.CocktailViewHolder>() {

    private val cocktails = ArrayList<IngredientResult>()

    class CocktailViewHolder(private val itemBinding: ItemCocktailByIngredientBinding,
                             private val listener: CocktailItemListener)
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var cocktail: IngredientResult

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: IngredientResult) {
            this.cocktail = item
            itemBinding.name.text = item.strDrink
            Glide.with(itemBinding.root)
                .load(item.image)
                .circleCrop()
                .into(itemBinding.image)
        }

        override fun onClick(v: View?) {
            listener.onCocktailClick(cocktail.idDrink)
        }
    }

    fun setCocktails(cocktails : Collection<IngredientResult>) {
        this.cocktails.clear()
        this.cocktails.addAll(cocktails)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val binding = ItemCocktailByIngredientBinding.inflate(LayoutInflater.from(parent.context),parent,
            false)
        return CocktailViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) =
        holder.bind(cocktails[position])


    override fun getItemCount() = cocktails.size

    interface CocktailItemListener {
        fun onCocktailClick(cocktailId : Int)
    }
}

