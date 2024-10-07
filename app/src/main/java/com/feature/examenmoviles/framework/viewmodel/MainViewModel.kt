package com.feature.examenmoviles.framework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.examenmoviles.data.network.model.CharacterBase
import com.feature.examenmoviles.data.network.model.CharactersObject
import com.feature.examenmoviles.domain.CharacterByNameRequirement
import com.feature.examenmoviles.domain.CharacterListRequirement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val characterListRequirement = CharacterListRequirement()
    private val characterByNameRequirement = CharacterByNameRequirement()
    val isLoading = MutableLiveData<Boolean>() // Para manejar el estado de carga
    val characterList = MutableLiveData<CharactersObject?>()
    val searchResults = MutableLiveData<List<CharacterBase>>() // Para almacenar los resultados de búsqueda

    var currentPage = 1 // Página actual
    var totalPages = 1  // Número total de páginas (se actualizará con cada solicitud)

    fun getCharacters(page: Int = currentPage, limit: Int = 10) {
        if (page <= totalPages) {  // Verificamos si hay más páginas para cargar
            isLoading.postValue(true)  // Indicamos que la carga está en progreso
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val result: CharactersObject? = characterListRequirement(page, limit)
                    Log.d("MainViewModel", result.toString())
                    result?.let {
                        totalPages = it.meta.totalPages  // Actualizamos el número total de páginas
                        currentPage++  // Incrementamos la página actual para la próxima solicitud
                        characterList.postValue(result)
                        Log.d("MainViewModel", "Characters fetched successfully")
                    }
                } catch (e: Exception) {
                    Log.e("MainViewModel", "Error fetching characters: ${e.localizedMessage}")
                } finally {
                    isLoading.postValue(false)  // Indicamos que la carga ha finalizado
                }
            }
        } else {
            Log.d("MainViewModel", "No more pages to load.")
        }
    }

    fun searchUser(name: String) {
        isLoading.postValue(true)  // Indicamos que la carga está en progreso

        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Reiniciar la página y el total de páginas al buscar
                currentPage = 1
                totalPages = 1

                val result: List<CharacterBase> = characterByNameRequirement(name)

                Log.d("MainViewModel", result.toString())
                // Aquí puedes actualizar el LiveData con los resultados de búsqueda
                // Puedes usar otro MutableLiveData para almacenar los resultados de búsqueda si lo prefieres
                // Actualizar el LiveData con los resultados de búsqueda
                searchResults.postValue(result) // Almacena los resultados de búsqueda

            } catch (e: Exception) {
                Log.e("MainViewModel", "Error searching user: ${e.localizedMessage}")
            } finally {
                isLoading.postValue(false)  // Indicamos que la carga ha finalizado
            }
        }
    }
}
