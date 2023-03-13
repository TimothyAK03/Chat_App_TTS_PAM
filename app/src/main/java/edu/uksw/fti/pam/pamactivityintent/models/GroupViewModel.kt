package edu.uksw.fti.pam.pamactivityintent.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.repositories.JSONPlaceholderTypicodeRepository
import kotlinx.coroutines.launch

class GroupViewModel : ViewModel()  {
    private var _GroupList = mutableStateListOf<GroupModel>()

    var errorMessage : String by mutableStateOf("")
    val GroupList: List<GroupModel>
        get() = _GroupList

    fun getGroupList() {
        viewModelScope.launch {
            val apiClient = JSONPlaceholderTypicodeRepository.getClient()
            try{
                _GroupList.clear()
                _GroupList.addAll(apiClient.getGroup())
            }
            catch (e: Exception){
                errorMessage = e.message!!
            }
        }
    }
}