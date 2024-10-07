package com.feature.examenmoviles.framework.adapters.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feature.examenmoviles.data.network.model.CharacterBase
import com.feature.examenmoviles.databinding.ItemCharacterBinding


class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(item: CharacterBase, context: Context) {
        // Asignar los datos de la película a los elementos de la UI
        binding.TVName.text = item.name
        binding.TVKi.text = item.ki
        binding.TVMaxKi.text = item.makKi
        binding.TVDescription.text = item.description

        // Cargar la imagen del póster usando Glide
        Glide.with(context)
            .load(item.image) // Ajusta el tamaño del póster según necesites
            .into(binding.IVImage) // Asegúrate de que IVPoster sea el ImageView para el póster
    }
}