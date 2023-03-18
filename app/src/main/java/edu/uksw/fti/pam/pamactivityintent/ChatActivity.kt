package edu.uksw.fti.pam.pamactivityintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import edu.uksw.fti.pam.pamactivityintent.models.TodosModel
import edu.uksw.fti.pam.pamactivityintent.ui.screens.ChatScreen
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

class ChatActivity : ComponentActivity() {

    private val chatt: TodosModel by lazy {
        intent?.getSerializableExtra(CHAT_ID) as TodosModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAMActivityIntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    ChatScreen(chatt = chatt)
                }
            }
        }
    }
    companion object {
        private const val CHAT_ID = "chat_id"
        fun newIntent(context: Context, chatt: TodosModel) =
            Intent(context, ChatActivity::class.java).apply {
                putExtra(CHAT_ID,chatt)
            }
    }
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    PAMActivityIntentTheme {
//        Greeting("Android")
//    }
//}