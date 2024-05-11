package com.example.wazitoecommerce.ui.theme.screens.signup

//name email password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.data.AuthViewModel
import com.example.wazitoecommerce.navigation.LOGIN_URL
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme
import com.example.wazitoecommerce.ui.theme.lBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController:NavHostController){
    Column(modifier = Modifier
        .background(color = Color.Blue)
        .fillMaxSize()
        .padding(end = 20.dp, start = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Box {
            Text(
                text = "Create an account",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                color = Color.White,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .padding(10.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),) {
                Text(
                    text = "Register Here",
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                    color = lBlue,
                )
            }//login stuff
            Spacer(modifier = Modifier.height(20.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()) {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var name by remember { mutableStateOf("") }

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Enter your Name *") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Enter your Email *") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password *") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )


                Spacer(modifier = Modifier.height(20.dp))


                Row {
                    val context = LocalContext.current
                    val authViewModel = AuthViewModel(navController, context)

                    Button(onClick = {authViewModel.signup(name, email, password) }) {
                        Text(text = "Register")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(onClick = {
                        navController.navigate(LOGIN_URL)
                    }) {
                        Text(text = "Login Instead")
                    }

                }
            }
        }





    }
}

@Composable
@Preview(showBackground = true)
fun SignupScreenPreview(){
    WazitoECommerceTheme {
        SignupScreen(navController = rememberNavController())
    }
}