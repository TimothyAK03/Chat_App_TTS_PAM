package edu.uksw.fti.pam.pamactivityintent.models


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pamactivityintent.HomeActivity


fun doAuth(
    context: Context,
    username: String,
    password: String
) {
    val auth = Firebase.auth
    auth.signInWithEmailAndPassword(username, password)
        .addOnCompleteListener(context as Activity) { task ->
            if (task.isSuccessful) {
                val intent = Intent(context, HomeActivity::class.java)
                context.startActivity(intent)
                (context).finish()
            } else {
                Toast.makeText(context.applicationContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
}

fun sendUsernameBackToLoginPage(
    context: Context,
    nama: String?,
    username: String?,
    password: String?
) {
    val auth = Firebase.auth
    val db = Firebase.firestore
    auth.createUserWithEmailAndPassword(username!!, password!!)
        .addOnCompleteListener(context as Activity) { task->
            if (task.isSuccessful) {
                val userUid = task.result.user?.uid
                val userData = UserProfile(nama!!)
                db.collection("users")
                    .document(userUid!!)
                    .set(userData)
                    .addOnCompleteListener {
                        val result = Intent().putExtra("username", username)
                        context.setResult(Activity.RESULT_OK, result)
                        (context).finish()
                    }
            } else {
                Toast.makeText(context.applicationContext, "Error Create User", Toast.LENGTH_SHORT).show()
            }
        }
}
