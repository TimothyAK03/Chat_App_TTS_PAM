package edu.uksw.fti.pam.pamactivityintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.uksw.fti.pam.pamactivityintent.ui.screens.SignUpScreen
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
import edu.uksw.fti.pam.pamactivityintent.models.*

class SignActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAMActivityIntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SignUpScreen(onClickAction = ::sendUsernameBackToLoginPage)
                }
            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    PAMActivityIntentTheme {
//        SignUpScreen({})
//    }
//}