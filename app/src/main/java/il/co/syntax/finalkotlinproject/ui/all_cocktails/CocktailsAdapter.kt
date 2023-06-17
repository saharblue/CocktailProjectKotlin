package il.co.syntax.finalkotlinproject.ui.all_cocktails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import il.co.syntax.finalkotlinproject.data.models.Cocktail
import il.co.syntax.finalkotlinproject.databinding.ItemCocktailBinding

class CocktailsAdapter(private val listener : CharacterItemListener) :
    RecyclerView.Adapter<CocktailsAdapter.CharacterViewHolder>() {

    private val characters = ArrayList<Cocktail>()

    class CharacterViewHolder(private val itemBinding: ItemCocktailBinding,
                              private val listener: CharacterItemListener)
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var character: Cocktail

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

    fun setCharacters(characters : Collection<Cocktail>) {
        this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCocktailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(characters[position])


    override fun getItemCount() = characters.size

    interface CharacterItemListener {
        fun onCharacterClick(characterId : Int)
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