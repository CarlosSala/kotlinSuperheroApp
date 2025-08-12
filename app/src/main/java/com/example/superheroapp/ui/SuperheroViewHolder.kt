package com.example.superheroapp.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.databinding.ItemSuperheroBinding
import com.example.superheroapp.ui.model.SuperheroItemUI
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superheroItemUI: SuperheroItemUI, onItemSelected: (String) -> Unit) {

        binding.tvSuperheroName.text = superheroItemUI.name
        Picasso.get().load(superheroItemUI.image.url).into(binding.ivSuperhero)

        // root is all view of each item, since here to detailSuperheroActivity
        binding.root.setOnClickListener { onItemSelected(superheroItemUI.superheroId) }
    }
}