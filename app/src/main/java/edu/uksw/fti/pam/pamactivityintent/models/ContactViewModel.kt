package edu.uksw.fti.pam.pamactivityintent.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.repositories.JSONContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel: ViewModel() {
    private var _toDoList= mutableStateListOf<ContactsModel>()

    var errorMessage: String by mutableStateOf("")
    val toDoList: List<ContactsModel>
        get() = _toDoList

    fun getToDoList() {
        viewModelScope.launch{
            val apiClient = JSONContactsRepository.getClient()
            try {
                _toDoList.clear()
                _toDoList.addAll(apiClient.getTodos())
            }
            catch(e: Exception) {
                errorMessage = e.message!!
            }
        }

    }
}