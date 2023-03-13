package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.accounts.Account
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.uksw.fti.pam.pamactivityintent.HomeActivity
import edu.uksw.fti.pam.pamactivityintent.contracts.SignUpContract
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.SignActivity
import edu.uksw.fti.pam.pamactivityintent.models.AccountModel
import edu.uksw.fti.pam.pamactivityintent.models.*
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme
import java.io.IOException
import androidx.compose.foundation.lazy.LazyColumn


//fun getAccountFromJSON(context: Context): List<AccountModel> {
//    //pakai lateinit karena var jsonString tidak dapat dinit langsung ketika program berjalan, tapi ketika fungsi dipanggil
//    lateinit var jsonString: String
//    try {
//        jsonString = context.assets.open("account/account.json")
//            .bufferedReader()
//            .use { it.readText() }
//    } catch (ioException: IOException) {
//        println("Error reading file: ${ioException.message}")
//        return emptyList() // or return null
//    }
//
//    val listAccount = object : TypeToken<List<AccountModel>>() {}.type
//    return try {
//        //GSOn memparsing, menggunakan method fromJson dari jsonString menjadi object listAccount dengan type list
//        Gson().fromJson(jsonString, listAccount)
//    } catch (exception: Exception) {
//        println("Error parsing JSON: ${exception.message}")
//        emptyList() // or return null
//    }
//}


internal fun doAuth(
    usernameInput: String,
    passwordInput: String,
    accountList: List<AccountModel>
): Boolean {
    for (account in accountList) {
        println(account.username)
        println(usernameInput)
        println(account.password)
        println(passwordInput)
        if (usernameInput == account.username && passwordInput == account.password) {
            return true
        }
    }
    return false
}


@Composable
fun LoginForm(context : Context) {
    val JSONParser = JSONParser()
    val lContext = LocalContext.current
    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    val getUsernameFromSignedUpForm = rememberLauncherForActivityResult(
        contract = SignUpContract(),
        onResult = { usernameInput = it!! }
    )
    val accountList : List<AccountModel> = JSONParser.getAccountFromJSON(lContext)
    if (JSONParser.isEmpty(context)) {
        for (account in accountList) {
            JSONParser.appendJsonToFile(lContext, account)
        }
    }
    else
        println("[!] Sudah ada file berisi di account.json")


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
    Box(
        modifier = Modifier
            .padding(start = 0.dp, top = 94.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .padding(start = 0.dp, top = 20.dp)
                .size(212.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_login),
                contentDescription = stringResource(id = R.string.icon_login)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 36.dp, end = 36.dp, top = 300.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = usernameInput.toString(),
            onValueChange = { usernameInput = it },
            label = { Text(text = stringResource(R.string.label_username)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xff36a8eb),
                unfocusedBorderColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)

//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Number
        )
        OutlinedTextField(
            value = passwordInput.toString(),
            onValueChange = { passwordInput = it },
            label = { Text(text = stringResource(R.string.label_password)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xff36a8eb),
                unfocusedBorderColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(8.dp)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(top = 12.dp),
            colors = buttonColors(Color(0xff36a8eb)),
            onClick = {

                val accountList: List<AccountModel> = JSONParser.getAccountFromFile(context)
                println("Account List: $accountList")
                val isAuthtenticated = doAuth(usernameInput, passwordInput, accountList)
                if (isAuthtenticated) {
                    lContext.startActivity(
                        Intent(lContext, HomeActivity::class.java)
                            .apply { putExtra("username", usernameInput) }
                    )
                }
            },
            shape = RoundedCornerShape(8.dp)
        )
        {
            Text(
                text = "Sign In",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Create an Account ?",
                color = Color.Gray,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable(
                        onClick = {
//                        lContext.startActivity(
//                            Intent(lContext, SignActivity::class.java)
                            getUsernameFromSignedUpForm.launch("")
                        }
                    )
            )
        }
    }
}


//            Button(
//                modifier = Modifier,
//                colors = buttonColors(Color(0xff36a8eb)),
//                    onClick = {
////                        lContext.startActivity(
////                            Intent(lContext, SignActivity::class.java)
//                    getUsernameFromSignedUpForm.launch("")
//                },
//            )
//            {
//                Text(
//                    text = "SIGN UP",
//                    fontSize = 16.sp,
//                    color = Color.White,
//                    fontWeight = FontWeight.Normal
//                )
//            }
//        }




@Preview(showBackground = true)
@Composable
fun DefaultPreview10(){
    PAMActivityIntentTheme {

    }
}
