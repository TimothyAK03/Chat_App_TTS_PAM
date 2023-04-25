package edu.uksw.fti.pam.pamactivityintent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.uksw.fti.pam.pamactivityintent.ui.screens.LoginForm
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
import edu.uksw.fti.pam.pamactivityintent.models.doAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAMActivityIntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LoginForm(onSignAction = ::doAuth)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginFormPreview() {
    PAMActivityIntentTheme {

    }
}