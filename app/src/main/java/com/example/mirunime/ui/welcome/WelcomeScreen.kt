package com.example.mirunime.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    
    Box(
        modifier = modifier.fillMaxSize()
    ){
        //bg
        Image(painter = painterResource(id = R.drawable.bg),
            contentDescription =null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize())

        //Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.logo),
                contentDescription =null,
                modifier = Modifier
                    .width(390.dp)
                    .height(280.dp),
                contentScale = ContentScale.Fit)

            Text("WELCOME",
                fontSize = 40.sp,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight(700),
                color = Color.White
            )
            Text("To",
                fontSize = 20.sp,
                fontFamily = AlegreyaSansFontFamily,
                fontWeight = FontWeight(700),
                color = Color.Black)

            Text("ミルNIME",
                fontSize = 40.sp,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight(1000),
                color = Color.Black)

            Spacer(modifier = Modifier.size(250.dp))

            Button(onClick = {
                          navController.navigate("login")
            },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                ),
                modifier = Modifier
                    .width(240.dp)
                    .height(50.dp)

            ) {
                Text("Sign in",
                    fontSize = 21.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    fontWeight = FontWeight(500),
                    color = Color.White
                )
            }

            Row(
                modifier = Modifier.padding(top = 12.dp, bottom = 52.dp)
            ) {
                Text("Don't have an account?",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = AlegreyaSansFontFamily,
                        color = Color.Black
                    )
                )
                Text(" Sign Up",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = AlegreyaSansFontFamily,
                        color = Color.Black,
                        fontWeight = FontWeight(1000),
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.clickable {
                        navController.navigate("register")
                    }
                )
            }
        }

    }
}


@Preview(name = "landscape", widthDp = 412, heightDp = 915)
@Preview(name = "landscape", widthDp = 915, heightDp = 412)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen(rememberNavController())
}
