package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.pamactivityintent.R



@Composable
fun SignUpScreen(
    onClickAction: (
        Context,
        String,
        String,
        String,
        String,
        String) -> Unit
) {
    var usernameInput by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
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
            "SENTIGRAM",
            fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = Color(0xff36a8eb),
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
            value = usernameInput,
            onValueChange = { usernameInput = it },
            label = { Text(text = stringResource(R.string.label_username)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xff36a8eb),
                unfocusedBorderColor = Color.Gray)
        )

        OutlinedTextField(
            value = number,
            onValueChange = { number = it },
            label = { Text(text = stringResource(R.string.number)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xff36a8eb),
                unfocusedBorderColor = Color.Gray)
        )

        OutlinedTextField(
            value = passwordInput,
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
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = stringResource(R.string.label_confirmation)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
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
                if (passwordInput != confirmPassword) {
                    Toast.makeText(context.applicationContext, "Password Tidak Sama", Toast.LENGTH_SHORT).show()
                }
                else {
                    onClickAction(context,firstName,lastName, usernameInput,number, passwordInput)
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





//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview13(){
//    PAMActivityIntentTheme {
//        SignUpScreen({})
//    }
//}

