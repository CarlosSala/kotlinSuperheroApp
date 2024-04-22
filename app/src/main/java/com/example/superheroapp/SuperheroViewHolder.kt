package com.example.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superheroItemResponse: SuperheroItemResponse, onItemSelected: (String) -> Unit) {

        binding.tvSuperheroName.text = superheroItemResponse.name
        Picasso.get().load(superheroItemResponse.image.url).into(binding.ivSuperhero)

        // root is all view of each item, since here to detailSuperheroActivity
        binding.root.setOnClickListener { onItemSelected(superheroItemResponse.superheroId) }
    }
}