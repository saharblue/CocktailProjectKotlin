package il.co.syntax.finalkotlinproject.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.databinding.ItemCocktailByNameBinding

class FavoriteCocktailsAdapter(private val listener : CocktailItemListener) :
    RecyclerView.Adapter<FavoriteCocktailsAdapter.CocktailViewHolder>() {

    private val cocktails = ArrayList<Cocktail>()

    class CocktailViewHolder(private val itemBinding: ItemCocktailByNameBinding,
                             private val listener: CocktailItemListener)
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var cocktail: Cocktail

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: Cocktail) {
            this.cocktail = item
            itemBinding.name.text = item.strDrink
            itemBinding.type.text = item.strAlcoholic
            Glide.with(itemBinding.root)
                .load(item.image)
                .circleCrop()
                .into(itemBinding.image)
        }

        override fun onClick(v: View?) {
            listener.onCocktailClick(cocktail.idDrink)
        }
    }

    fun setCocktails(cocktails : Collection<Cocktail>) {
        this.cocktails.clear()
        this.cocktails.addAll(cocktails)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val binding = ItemCocktailByNameBinding.inflate(LayoutInflater.from(parent.context),parent,
            false)
        return CocktailViewHolder(binding,listener)
    }

    fun cocktailAt(position: Int) = cocktails[position]

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) =
        holder.bind(cocktails[position])


    override fun getItemCount() = cocktails.size

    interface CocktailItemListener {
        fun onCocktailClick(cocktailId : Int)
    }
}
