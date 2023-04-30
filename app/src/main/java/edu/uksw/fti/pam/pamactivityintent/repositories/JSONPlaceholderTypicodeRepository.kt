package edu.uksw.fti.pam.pamactivityintent.repositories

import edu.uksw.fti.pam.pamactivityintent.models.AboutUsModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JSONPlaceholderTypicodeRepository {



    @GET("AboutUs")
    suspend fun getAboutUs():List<AboutUsModel>

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