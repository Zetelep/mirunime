package com.example.mirunime.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mirunime.ui.theme.AlegreyaSansFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTextField(
    onValueChange: (String) -> Unit = {},
    hint: String,
    value: String
) {
    val textState = remember { mutableStateOf(value) }

    TextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
            onValueChange(it)
        },
        placeholder = {
            Text(text = hint,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    color = Color(0xFFBEC2C2),
                    fontWeight = FontWeight(1000)

                )
            )
        },
        textStyle = TextStyle(
            color = Color.White, // Set the color for user input text here
            fontSize = 25.sp, // Set the size for user input text here
            fontFamily = AlegreyaSansFontFamily
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color(0xFFBEC2C2),
            unfocusedIndicatorColor = Color(0xFFBEC2C2),
            focusedTextColor = Color(0xFFBEC2C2),
            unfocusedTextColor = Color(0xFFBEC2C2)
        )

    )
}