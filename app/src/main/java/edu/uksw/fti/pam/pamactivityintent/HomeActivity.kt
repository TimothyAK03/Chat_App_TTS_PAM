package edu.uksw.fti.pam.pamactivityintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import edu.uksw.fti.pam.pamactivityintent.ui.screens.BottomNavigationMainScreenView
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setContent {
            PAMActivityIntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                        BottomNavigationMainScreenView{
                            startActivity(ChatActivity.newIntent(this, it))
                        }


                    }
                }
            }
        }
    }



