package edu.uksw.fti.pam.pamactivityintent.repositories

import edu.uksw.fti.pam.pamactivityintent.models.GroupMessageModel
import edu.uksw.fti.pam.pamactivityintent.models.MessageModel
import edu.uksw.fti.pam.pamactivityintent.models.TodosModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JSONPlaceholderTypicodeRepository {
    @GET("posts")
    suspend fun getTodos():List<TodosModel>


    @GET("Message")
    suspend fun getMessage():List<MessageModel>

    @GET("GroupMessage")
    suspend fun getGroupMessage():List<GroupMessageModel>

    companion object{
        var _apiClient: JSONPlaceholderTypicodeRepository? = null


        fun getClient(): JSONPlaceholderTypicodeRepository{
            if(_apiClient == null){
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/Nicolaus1337/Json/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(JSONPlaceholderTypicodeRepository::class.java)
            }

            return _apiClient!!;
        }

    }
}