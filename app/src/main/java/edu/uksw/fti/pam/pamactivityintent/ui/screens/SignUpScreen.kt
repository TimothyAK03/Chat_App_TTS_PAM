package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.content.Context
import android.content.Intent
import android.system.Os.write
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.reflect.TypeToken
import edu.uksw.fti.pam.pamactivityintent.DataStore.FirstName
import edu.uksw.fti.pam.pamactivityintent.DataStore.LastName
import edu.uksw.fti.pam.pamactivityintent.HomeActivity
import edu.uksw.fti.pam.pamactivityintent.MainActivity
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.models.AccountModel
import edu.uksw.fti.pam.pamactivityintent.models.JSONParser
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
import kotlinx.coroutines.launch
import okhttp3.internal.platform.android.AndroidLogHandler.flush
import org.json.JSONArray
import org.json.JSONObject
import java.io.File


//import android.content.Intent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.material.TextField
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.unit.dp
//import edu.uksw.fti.pam.pamactivityintent.HomeActivity
//import edu.uksw.fti.pam.pamactivityintent.MainActivity
//import edu.uksw.fti.pam.pamactivityintent.R
//
//@Composable
//fun SignUpForm(btnOnClickAction: (String?) -> Unit) {
//    val lContext = LocalContext.current
//    var firstName by remember { mutableStateOf("") }
//    var lastName by remember { mutableStateOf("") }
//    var usernameInput by remember { mutableStateOf("") }
//    var passwordInput by remember { mutableStateOf("") }
//    var cPassword by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(start = 0.dp, top = 0.dp),
//            horizontalArrangement = Arrangement.spacedBy(5.dp)
//        ) {
//            TextField(
//                value = firstName.toString(),
//                onValueChange = { firstName = it },
//                label = { Text(text = stringResource(R.string.label_first)) },
//                modifier = Modifier
//                    .width(160.dp)
//            )
//            TextField(
//                value = lastName.toString(),
//                onValueChange = { lastName = it },
//                label = { Text(text = stringResource(R.string.label_last)) },
//                modifier = Modifier
//                    .width(190.dp)
//            )
//        }
//        TextField(
//            value = usernameInput.toString(),
//            onValueChange = { usernameInput = it },
//            label = { Text(text = stringResource(R.string.label_username)) },
//            modifier = Modifier.fillMaxWidth()
//        )
//        TextField(
//            value = passwordInput.toString(),
//            onValueChange = { passwordInput = it },
//            label = { Text(text = stringResource(R.string.label_password)) },
//            modifier = Modifier.fillMaxWidth(),
//            visualTransformation = PasswordVisualTransformation()
//        )
//        TextField(
//            value = cPassword.toString(),
//            onValueChange = { cPassword = it },
//            label = { Text(text = stringResource(R.string.label_confirmation)) },
//            modifier = Modifier.fillMaxWidth(),
//        )
//        Row(
//            modifier = Modifier
//                .padding(start = 0.dp, top = 0.dp),
//            horizontalArrangement = Arrangement.spacedBy(5.dp)
//        ) {
//            Button(
//                onClick = {
//                    btnOnClickAction(usernameInput)
//                }
//            )
//            {
//                Text(
//                    text = "OK"
//                )
//            }
//        }
//    }
//}



@Composable
fun MainScreen(btnOnClickAction: (String?) -> Unit) {


    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var cPassword by remember { mutableStateOf("") }
    val JSONParser = JSONParser()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = FirstName(context)
    val storeData = LastName(context)
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalArrangement = Arrangement.Center
    )
    {
        Text(
            "TELEGRAM",
            fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = androidx.compose.ui.graphics.Color(0xff36a8eb),
            fontSize = 36.sp
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 130.dp, start = 36.dp, end = 36.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 0.dp, top = 0.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        )
        {
            OutlinedTextField(
                modifier = Modifier
                    .width(140.dp),
                value = firstName,
                label = { Text(text = stringResource(R.string.label_first)) },
                onValueChange = { firstName = it },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff36a8eb),
                    unfocusedBorderColor = Color.Gray)
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(start = 0.dp, top = 0.dp),
                value = lastName,
                label = { Text(text = stringResource(R.string.label_last)) },
                onValueChange = { lastName = it },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff36a8eb),
                    unfocusedBorderColor = Color.Gray)
            )
        }

            OutlinedTextField(
                value = usernameInput.toString(),
                onValueChange = { usernameInput = it },
                label = { Text(text = stringResource(R.string.label_username)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff36a8eb),
                    unfocusedBorderColor = Color.Gray)
            )

            OutlinedTextField(
                value = usernameInput.toString(),
                onValueChange = { passwordInput = it },
                label = { Text(text = stringResource(R.string.label_password)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff36a8eb),
                    unfocusedBorderColor = Color.Gray)
            )

            OutlinedTextField(
                value = cPassword.toString(),
                onValueChange = { cPassword = it },
                label = { Text(text = stringResource(R.string.label_confirmation)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff36a8eb),
                    unfocusedBorderColor = Color.Gray)
            )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 0.dp)
                .height(50.dp),
            colors = buttonColors(Color(0xff36a8eb)),
            onClick = {
                val account = AccountModel(usernameInput, passwordInput)
                JSONParser.appendJsonToFile(context, account)
                scope.launch {
                    dataStore.saveFirst(firstName)
                    storeData.saveLast(lastName)
                    btnOnClickAction(usernameInput)
//                        context.startActivity(
//                            Intent(context, MainActivity::class.java)
//                        )
                }
            }
        )
        {
            // button text
            Text(
                text = "Sign Up",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold

            )
        }
        }
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(start = 0.dp, top = 520.dp),
        contentAlignment = Alignment.BottomEnd
    ){
        Box(
            modifier = Modifier
                .padding(start = 0.dp, top = 20.dp)
                .size(500.dp),
            contentAlignment = Alignment.TopCenter
        ){
            Image(painter = painterResource(id = R.drawable.icon_dream),
                contentDescription = stringResource(id = R.string.icon_dream)
            )
        }
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview13(){
    PAMActivityIntentTheme {
        MainScreen({})
    }
}

