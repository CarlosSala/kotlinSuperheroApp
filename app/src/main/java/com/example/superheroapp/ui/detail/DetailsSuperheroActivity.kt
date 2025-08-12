package com.example.superheroapp.ui.detail

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.superheroapp.databinding.ActivityDetailsSuperheroBinding
import com.example.superheroapp.ui.MainActivity.Companion.EXTRA_ID
import com.example.superheroapp.ui.model.PowerStatsUI
import com.example.superheroapp.ui.model.SuperheroDetailUI
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class DetailsSuperheroActivity : AppCompatActivity() {

    private val viewModel: SuperheroDetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailsSuperheroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()

        viewModel.getSuperheroDetail(id)

        lifecycleScope.launch {
            viewModel.progressBar.collect { isLoading ->
                binding.pbSuperheroDetail.isVisible = isLoading
                binding.contentLayout.isVisible = !isLoading
            }
        }

        lifecycleScope.launch {
            viewModel.superheroDetailModel.collect { superhero ->
                superhero?.let {
                    createUI(it)
                }
            }
        }
    }

    private fun createUI(superhero: SuperheroDetailUI) {

        Picasso.get().load(superhero.imageUrl).into(binding.ivSuperhero)
        binding.tvSuperheroName.text = superhero.name
        prepareStats(superhero.powerStats)
        binding.tvSuperheroRealName.text = superhero.biography.fullName
        binding.tvPublisher.text = superhero.biography.publisher
    }

    private fun prepareStats(powerStatsUI: PowerStatsUI) {
        updateHeight(binding.viewCombat, powerStatsUI.combat)
        updateHeight(binding.viewDurability, powerStatsUI.durability)
        updateHeight(binding.viewSpeed, powerStatsUI.speed)
        updateHeight(binding.viewStrength, powerStatsUI.strength)
        updateHeight(binding.viewIntelligence, powerStatsUI.intelligence)
        updateHeight(binding.viewPower, powerStatsUI.power)
    }

    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()
    }
}