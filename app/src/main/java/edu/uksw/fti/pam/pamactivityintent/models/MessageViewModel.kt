package edu.uksw.fti.pam.pamactivityintent.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.repositories.JSONPlaceholderTypicodeRepository
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel() {
    private var _MessageList = mutableStateListOf<MessageModel>()

    var errorMessage : String by mutableStateOf("")
    val MessageList: List<MessageModel>
        get() = _MessageList

    fun getMessageList() {
        viewModelScope.launch {
            val apiClient = JSONPlaceholderTypicodeRepository.getClient()
            try{
                _MessageList.clear()
                _MessageList.addAll(apiClient.getMessage())
            }
            catch (e: Exception){
                errorMessage = e.message!!
            }
        }
    }
}