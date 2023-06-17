package il.co.syntax.finalkotlinproject.ui.all_cocktails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.databinding.ItemCocktailBinding

class CocktailsAdapter(private val listener : CocktailItemListener) :
    RecyclerView.Adapter<CocktailsAdapter.CocktailViewHolder>() {

    private val cocktails = ArrayList<Cocktail>()

    class CocktailViewHolder(private val itemBinding: ItemCocktailBinding,
                             private val listener: CocktailItemListener)
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var cocktail: Cocktail

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: Cocktail) {

            //TODO:bind you data
        }

        override fun onClick(v: View?) {

            //TODO:pass the relevant item
        }
    }

    fun setCocktails(cocktails : Collection<Cocktail>) {
        this.cocktails.clear()
        this.cocktails.addAll(cocktails)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val binding = ItemCocktailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CocktailViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) =
        holder.bind(cocktails[position])


    override fun getItemCount() = cocktails.size

    interface CocktailItemListener {
        fun onCocktailClick(characterId : Int)
    }
}


/* inner class CharacterViewHolder(private val itemBinding : ItemCharacterBinding) : RecyclerView.ViewHolder(itemBinding.root) {

     val nameTv = itemBinding.name
     val speciesAndStatusTv = itemBinding.speciesAndStatus
     val characterImageView = itemBinding.image

     init {
         itemBinding.root.setOnClickListener {
             listener.onCharacterClick(characters[adapterPosition].id)
         }
     }
 }

  val character = characters[position]
        holder.nameTv.text = character.name
        holder.speciesAndStatusTv.text = "${character.species} ${character.status}"
        Glide.with(holder.itemView.context).load(character.picture).circleCrop().into(holder.characterImageView)
*/