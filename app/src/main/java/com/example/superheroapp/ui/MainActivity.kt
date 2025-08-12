package com.example.superheroapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.databinding.ActivityMainBinding
import com.example.superheroapp.ui.detail.DetailsSuperheroActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: SuperheroViewModel by viewModels()

    companion object {
        const val EXTRA_ID: String = "extra_id"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        lifecycleScope.launch {
            viewModel.progressBar.collect {
                binding.pb.isVisible = it
            }
        }

        lifecycleScope.launch {
            viewModel.superheroModel.collect { myResponse ->
                // val response: SuperheroDataResponse? = myResponse.body()
                if (!myResponse?.response.isNullOrEmpty()) {
                    if (myResponse?.response == "success") {

                        adapter.updateList(myResponse.results)
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Response not successful",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                hideKeyboard()
            }
        }
    }

    private fun initUI() {

        binding.sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty())
                    lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.searchSuperhero(query)
                    }
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


    private fun hideKeyboard() {

        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.clSuperHeroApp.windowToken, 0)
    }

    private fun navigateToDetail(id: String) {

        val intent = Intent(this, DetailsSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}