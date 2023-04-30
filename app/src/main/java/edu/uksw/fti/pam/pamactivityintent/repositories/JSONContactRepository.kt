package edu.uksw.fti.pam.pamactivityintent.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JSONContactsRepository {
    @GET("Contact")
    suspend fun getTodos(): List<ContactsModel>

    companion object {
        var _apiClient: JSONContactsRepository? = null

        fun getClient(): JSONContactsRepository {
            if (_apiClient == null) {
                _apiClient =  Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/danielrichard0/ContactsChatApp/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(JSONContactsRepository::class.java)
            }
            return _apiClient!!;
        }


    }
}