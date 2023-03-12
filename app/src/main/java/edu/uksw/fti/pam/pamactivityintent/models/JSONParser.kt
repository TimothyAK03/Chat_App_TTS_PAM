package edu.uksw.fti.pam.pamactivityintent.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.lang.reflect.Type

class JSONParser {
    fun getAccountFromFile(context: Context): List<AccountModel> {
        //pakai lateinit karena var jsonString tidak dapat dinit langsung ketika program berjalan, tapi ketika fungsi dipanggil
        lateinit var jsonString: String

        try {
            val file = File(context.filesDir, "account.json")
            jsonString = file.readText()
            println("jsonStringFuck = ${jsonString}")
        } catch (ioException: IOException) {
            println("Error reading file: ${ioException.message}")
            return emptyList() // or return null
        }

        val listAccount = object : TypeToken<List<AccountModel>>() {}.type
        println("list account FILE DAMNIT ${listAccount}")
        return try {
            //GSOn memparsing, menggunakan method fromJson dari jsonString menjadi object listAccount dengan type list
            Gson().fromJson(jsonString, listAccount)
        } catch (exception: Exception) {
            println("Error parsing JSON (): ${exception.message}")
            emptyList() // or return null
        }
    }

    fun getAccountFromJSON(context: Context): List<AccountModel> {
        //pakai lateinit karena var jsonString tidak dapat dinit langsung ketika program berjalan, tapi ketika fungsi dipanggil
        lateinit var jsonString: String

        try {
            jsonString = context.assets.open("account/account.json")
                .bufferedReader()
                .use { it.readText() }
            println("jsonStringFUCK = ${jsonString}")
        } catch (ioException: IOException) {
            println("Error reading file: ${ioException.message}")
            return emptyList() // or return null
        }

        val listAccount = object : TypeToken<List<AccountModel>>() {}.type
        println("list account FILE DAMNIT ${listAccount}")
        return try {
            //GSOn memparsing, menggunakan method fromJson dari jsonString menjadi object listAccount dengan type list
            Gson().fromJson(jsonString, listAccount)
        } catch (exception: Exception) {
            println("Error parsing JSON: ${exception.message}")
            emptyList() // or return null
        }
    }


    fun writeAccountToJSON(context: Context, accountList: List<AccountModel>): Boolean {
        return try {
            // Convert the account list to a JSON object
            val jsonObject = JSONObject()
            val jsonArray = JSONArray(Gson().toJson(accountList))
            jsonObject.put("accounts", jsonArray)

            val fileOutputStream = context.openFileOutput("account.json", Context.MODE_PRIVATE)
            fileOutputStream.write(jsonObject.toString().toByteArray())
            fileOutputStream.close()

            true
        } catch (ioException: IOException) {
            // print error message and return false if writing failed
            println("Error writing to file: ${ioException.message}")
            false
        }
    }


    fun appendJsonToFile(context: Context, account: AccountModel) {
        val file = File(context.filesDir, "account.json")
        val jsonString = file.readText()
        val jsonArray = if (!jsonString.isEmpty()) {
            JSONArray(jsonString)
        } else {
            JSONArray()
        }
        println("[1]JSON array sblm append = ${jsonArray}")
        val jsonObject = JSONObject()
        jsonObject.put("username", account.username)
        jsonObject.put("password", account.password)
        println("[2]JSON object sblm append = ${jsonObject}")
        jsonArray.put(jsonObject)
        println("[3]JSON array ssdh append = ${jsonArray}")
        file.writeText(jsonArray.toString())
    }

    fun isEmpty(context: Context): Boolean {
        val file = File(context.filesDir, "account.json")
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (ioException: IOException) {
                println("Error creating file: ${ioException.message}")
            }
        }
        val jsonString = file.readText()
        return jsonString.isEmpty()
    }





}