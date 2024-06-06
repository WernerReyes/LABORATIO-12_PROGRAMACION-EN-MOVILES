package com.reyes.werner.poketinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reyes.werner.poketinder.databinding.ItemPokemonBinding

class PokemonAdapter (
    var list: List<PokemonResponse>
): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    inner  class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = ItemPokemonBinding.bind(itemView)

        fun bind(pokemon: PokemonResponse) {
            binding.tvName.text = pokemon.name
            Glide
                .with(itemView)
                .load(pokemon.getPokemonImage(pokemon.url))
                .into(binding.ivPokemon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = list[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = list.size


}
