package edu.uksw.fti.pam.pamactivityintent.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.repositories.JSONPlaceholderTypicodeRepository
import kotlinx.coroutines.launch

class AboutUsViewModel : ViewModel() {
    private var _AboutUsList = mutableStateListOf<AboutUsModel>()

    var errorMessage : String by mutableStateOf("")
    val AboutUsList: List<AboutUsModel>
        get() = _AboutUsList

    fun getAboutUsList() {
        viewModelScope.launch {
            val apiClient = JSONPlaceholderTypicodeRepository.getClient()
            try{
                _AboutUsList.clear()
                _AboutUsList.addAll(apiClient.getAboutUs())
            }
            catch (e: Exception){
                errorMessage = e.message!!
            }
        }
    }
}