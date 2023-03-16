package edu.phystech.kirillgrachoff.mipt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import edu.phystech.kirillgrachoff.mipt.ui.theme.Mipt_studyTheme

@Composable
fun LoginScreen() {
    Mipt_studyTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center,
        ) {
            LoginScreenImpl()
        }
    }
}

@Composable
fun LoginScreenImpl() {
    val email = remember {
        mutableStateOf( "" )
    }

    val password = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        AppIcon()
        EmailPasswordFields(
            email = email,
            password = password,
        )
        ButtonLogin(
            email = email,
            password = password,
        )
    }
}

@Composable
fun AppIcon() {
    Box(
        Modifier
            .height(200.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        //
    }
}

@Composable
fun ButtonLogin(email: MutableState<String>, password: MutableState<String>) {
    Column(
        Modifier.height(120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            { TODO("Forgot password") },
            colors = buttonColors(
                backgroundColor = Color(0, 0, 0, 0)
            ),
            elevation = null,
        ) {
            Text(
                "forgot your password?",
                fontSize = 13.sp,
            )
        }
        Button(
            { TODO("No runtime") },
            colors = buttonColors(
                backgroundColor = Color(0, 1, 0, 0x40)
            )
        ) {
            Text("Login")
        }
    }
}

@Composable
fun EmailPasswordFields(email: MutableState<String>, password: MutableState<String>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Login to Your Account")
        EmailField(
            email = email,
        )
        PasswordField(
            password = password,
        )
    }
}

@Composable
fun EmailField(email: MutableState<String>, modifier: Modifier = Modifier) {
    TextField(
        value = email.value,
        onValueChange = { email.value = it },
        label = {
            Text("Email")
        },
        leadingIcon = {
            Icon(Icons.Filled.Email, "Email")
        },
        modifier = modifier
    )
}

@Composable
fun PasswordField(password: MutableState<String>, modifier: Modifier = Modifier) {
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    TextField(
        value = password.value,
        onValueChange = { password.value = it },
        label = {
            Text("Password")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Lock, contentDescription = "Password")
        },
        visualTransformation = if (passwordVisible.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            val image = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            val description = if (passwordVisible.value) {
                "Hide password"
            } else {
                "Show password"
            }

            IconButton({
                passwordVisible.value = !passwordVisible.value
            }) {
                Icon(image, description)
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}