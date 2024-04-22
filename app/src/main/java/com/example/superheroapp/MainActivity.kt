package com.example.superheroapp

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {

        const val BASE_URL: String = "https://superheroapi.com/"
        const val EXTRA_ID: String = "extra_id"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {

        binding.sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                if (!query.isNullOrEmpty())
                    searchByName(query.lowercase())

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

        adapter = SuperheroAdapter { superheroId -> navigateToDetail(superheroId) }
        binding.rvSuperHeroApp.setHasFixedSize(true)
        binding.rvSuperHeroApp.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHeroApp.adapter = adapter
    }

    private fun searchByName(query: String) {

        binding.pb.isVisible = true

        // secondary thread
        CoroutineScope(Dispatchers.IO).launch {

            val myResponse: Response<SuperheroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperHeroes(query)

            withContext(Dispatchers.Main) {

                val response: SuperheroDataResponse? = myResponse.body()

                if (myResponse.isSuccessful) {

                    if (response?.response == "success") {

                        adapter.updateList(response.superheroes)
                        binding.pb.isVisible = false

                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Response not successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.pb.isVisible = false
                    }

                } else {

                    Toast.makeText(
                        this@MainActivity,
                        "Service not response",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.pb.isVisible = false
                }

                hideKeyboard()
            }
        }
    }

    private fun hideKeyboard() {

        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.clSuperHeroApp.windowToken, 0)
    }

    private fun getRetrofit(): Retrofit {

        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String) {

        val intent = Intent(this, DetailsSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}