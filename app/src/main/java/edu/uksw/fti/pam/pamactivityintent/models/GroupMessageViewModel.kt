package edu.uksw.fti.pam.pamactivityintent.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pamactivityintent.repositories.JSONPlaceholderTypicodeRepository
import kotlinx.coroutines.launch

class GroupMessageViewModel : ViewModel() {

    private var _GroupMessageList = mutableStateListOf<GroupMessageModel>()

    var errorMessage : String by mutableStateOf("")
    val GroupMessageList: List<GroupMessageModel>
        get() = _GroupMessageList

    fun getGroupMessageList() {
        viewModelScope.launch {
            val apiClient = JSONPlaceholderTypicodeRepository.getClient()
            try{
                _GroupMessageList.clear()
                _GroupMessageList.addAll(apiClient.getGroupMessage())
            }
            catch (e: Exception){
                errorMessage = e.message!!
            }
        }
    }
}