package com.example.superheroapp.ui.detail

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.superheroapp.data.network.PowerStatsResponse
import com.example.superheroapp.data.network.SuperheroApiClient
import com.example.superheroapp.data.network.SuperheroDetailResponse
import com.example.superheroapp.databinding.ActivityDetailsSuperheroBinding
import com.example.superheroapp.ui.MainActivity.Companion.BASE_URL
import com.example.superheroapp.ui.MainActivity.Companion.EXTRA_ID
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailsSuperheroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsSuperheroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()

        getSuperheroDetail(id)
    }

    private fun getSuperheroDetail(id: String) {

        lifecycleScope.launch(Dispatchers.IO) {

            val superheroDetail =
                getRetrofit().create(SuperheroApiClient::class.java).getSuperheroDetail(id)

            if (superheroDetail.body() != null) {
                runOnUiThread {
                    createUI(superheroDetail.body()!!)

                }
            }
        }
    }

    private fun createUI(superhero: SuperheroDetailResponse) {

        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvSuperheroName.text = superhero.name
        prepareStats(superhero.powerstats)
        binding.tvSuperheroRealName.text = superhero.biography.fullName
        binding.tvPublisher.text = superhero.biography.publisher
    }

    private fun prepareStats(powerStats: PowerStatsResponse) {
        updateHeight(binding.viewCombat, powerStats.combat)
        updateHeight(binding.viewDurability, powerStats.durability)
        updateHeight(binding.viewSpeed, powerStats.speed)
        updateHeight(binding.viewStrength, powerStats.strength)
        updateHeight(binding.viewIntelligence, powerStats.intelligence)
        updateHeight(binding.viewPower, powerStats.power)
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

    private fun getRetrofit(): Retrofit {

        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}