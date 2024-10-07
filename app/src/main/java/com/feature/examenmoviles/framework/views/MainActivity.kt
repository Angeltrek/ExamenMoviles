package com.feature.examenmoviles.framework.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feature.examenmoviles.data.network.model.CharacterAdapter
import com.feature.examenmoviles.data.network.model.CharacterBase
import com.feature.examenmoviles.databinding.ActivityMainBinding
import com.feature.examenmoviles.framework.viewmodel.MainViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        initializeObservers()
        initializeOnSearch()

        // Cargar la primera página de personajes
        viewModel.getCharacters()
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeObservers() {
        viewModel.characterList.observe(this) { characterList ->
            characterList?.let {
                updateRecyclerView(it.items)
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            // Manejar el estado de carga, por ejemplo, mostrando un ProgressBar
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.searchResults.observe(this) { searchResults ->
            // Actualizar la interfaz de usuario con los resultados de búsqueda
            updateRecyclerView(searchResults)
        }
    }

    private fun updateRecyclerView(characters: List<CharacterBase>) {
        if (!::adapter.isInitialized) {
            // Inicializa el adaptador solo la primera vez
            adapter = CharacterAdapter(characters, this)
            binding.RVCharacters.layoutManager = LinearLayoutManager(this)
            binding.RVCharacters.adapter = adapter

            // Agregar un ScrollListener para cargar más personajes al llegar al final
            binding.RVCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == characters.size - 1) {
                        // Se alcanzó el final de la lista
                        viewModel.getCharacters(viewModel.currentPage + 1)  // Cargar más personajes
                    }
                }
            })

            binding.BBack.setOnClickListener {
                if (viewModel.currentPage > 1) {
                    viewModel.getCharacters(viewModel.currentPage - 1)
                } else {
                    Log.d("MainActivity", "Ya estás en la primera página.")
                    Toast.makeText(this, "Ya estás en la primera página.", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            // Si el adaptador ya está inicializado, simplemente actualiza los datos
            adapter.updateCharacters(characters)

            // Desplazar el RecyclerView al inicio
            binding.RVCharacters.scrollToPosition(0) // Desplazar a la parte superior
        }
    }

    private fun initializeOnSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            private var searchJob: Job? = null

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchJob?.cancel()
                if (query != null && query.isNotEmpty()) {
                    Log.d("MainActivity", "Query: $query")
                    viewModel.searchUser(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    delay(500)
                    if (!newText.isNullOrEmpty()) {
                        // Si hay texto en el buscador, ejecuta la búsqueda
                        viewModel.searchUser(newText)
                    } else {
                        // Si el texto está vacío, vuelve al estado paginado
                        viewModel.getCharacters()  // Recargar la lista original
                    }
                }
                return true
            }
        })
    }
}
