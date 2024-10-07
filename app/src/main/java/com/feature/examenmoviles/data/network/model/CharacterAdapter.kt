package com.feature.examenmoviles.data.network.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feature.examenmoviles.framework.adapters.viewholders.CharacterViewHolder
import com.feature.examenmoviles.databinding.ItemCharacterBinding

class CharacterAdapter (
    private var data: List<CharacterBase>,
    private val context: Context
) : RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    // Método para actualizar la lista de personajes
    fun updateCharacters(newData: List<CharacterBase>) {
        data = newData
        notifyDataSetChanged()  // Notifica al adaptador que los datos han cambiado
    }
}