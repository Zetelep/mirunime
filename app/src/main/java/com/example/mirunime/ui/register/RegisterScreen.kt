package com.example.mirunime.ui.register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mirunime.R
import com.example.mirunime.ui.theme.AlegreyaFontFamily
import com.example.mirunime.ui.theme.AlegreyaSansFontFamily
import com.example.mirunime.ui.components.CTextField
import com.example.mirunime.viewmodel.UserViewModel


@Composable
fun RegisterScreen(
    viewModel: UserViewModel,
    navController: NavHostController
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val userInsertStatus by viewModel.userInsertStatus.observeAsState()
    val context = LocalContext.current


    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxSize()
    ) {


        Box(modifier = Modifier.fillMaxSize()){
            Image(painter = painterResource(id = R.drawable.bg2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
            )

            //Content

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                //logo
                Image(painter = painterResource(id = R.drawable.logo) ,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .height(150.dp)
                        .align(Alignment.Start)
                        .offset(x = (-10).dp)
                )

                Text("Sign Up",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = AlegreyaFontFamily,
                        fontWeight = FontWeight(500),
                        color = Color.White

                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .offset(x = 30.dp, y = (-20).dp)
                )
                Text("Sign up now to find your new anime to watch.",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = AlegreyaSansFontFamily,
                        color = Color(0xB2FFFFFF)
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 30.dp, bottom = 24.dp)
                )

                Spacer(modifier = Modifier.size(120.dp))
                //TextField
                CTextField(
                    onValueChange = {username = it},
                    hint = "Username",
                    value = username,
                )

                CTextField(
                    onValueChange = {password = it},
                    hint = "Password",
                    value = password,
                )

                Spacer(modifier = Modifier.size(45.dp))

                Button(onClick = {
                    viewModel.register(username,password)

                },
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray
                    ),
                    modifier = Modifier
                        .width(240.dp)
                        .height(50.dp)

                ) {
                    Text("Sign Up",
                        fontSize = 21.sp,
                        fontFamily = AlegreyaSansFontFamily,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 12.dp, bottom = 52.dp)
                ) {
                    Text("Already have an account?",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = AlegreyaSansFontFamily,
                            color = Color.White
                        )
                    )
                    Text(" Sign In",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = AlegreyaSansFontFamily,
                            color = Color.White,
                            fontWeight = FontWeight(1000),
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.clickable {
                            navController.navigate("login")
                        }
                    )
                }
            }
        }
    }

    userInsertStatus?.let { result ->
        if (result.isSuccess) {
            if (result.getOrNull() == true) {
                Toast.makeText(context, "User registered successfully", Toast.LENGTH_SHORT).show()
                navController.navigate("login")
            }
        } else if (result.isFailure) {
            Toast.makeText(context,"test",Toast.LENGTH_SHORT).show()
            Toast.makeText(context, result.exceptionOrNull()?.message ?: "An error occurred", Toast.LENGTH_SHORT).show()
            Log.d("adsad","sadadsda")
        }
    }
}


@Preview(name = "landscape", widthDp = 412, heightDp = 915)
@Preview(name = "landscape", widthDp = 915, heightDp = 412)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(UserViewModel(),rememberNavController())
}