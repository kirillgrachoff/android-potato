package edu.phystech.kirillgrachoff.mipt.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import edu.phystech.kirillgrachoff.mipt.ui.theme.Mipt_studyTheme
import edu.phystech.kirillgrachoff.mipt.ui.viewmodels.login.LoginViewModel

import androidx.lifecycle.viewmodel.compose.viewModel
import edu.phystech.kirillgrachoff.mipt.R
import edu.phystech.kirillgrachoff.mipt.ui.viewmodels.login.LoginAction
import edu.phystech.kirillgrachoff.mipt.ui.viewmodels.login.LoginUiState
import edu.phystech.kirillgrachoff.mipt.ui.viewmodels.login.*

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel()
) {
    val loginUiState by loginViewModel.uiState.collectAsState()
    val doAction = { action: LoginAction -> loginViewModel.confirm(action) }
    Mipt_studyTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center,
        ) {
            LoginScreenImpl(
                loginUiState,
                doAction,
            )
        }
    }
}

@Composable
fun LoginScreenImpl(
    loginUiState: LoginUiState,
    doAction: (LoginAction) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        AppIcon()
        EmailPasswordFields(
            email = loginUiState.login,
            password = loginUiState.password,
            visible = loginUiState.passwordVisible,
            doAction = doAction,
        )
        ButtonLogin(
            doAction = doAction,
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
        Icon(painterResource(R.drawable.logo), "Logo",
            Modifier
                .width(160.dp)
                .height(160.dp))
    }
}

@Composable
fun ButtonLogin(doAction: (LoginAction) -> Unit) {
    Column(
        Modifier.height(120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            { doAction(ForgotPassword) },
            colors = buttonColors(
                backgroundColor = Color(0, 0, 0, 0)
            ),
            elevation = null,
        ) {
            Text(
                R.string.ForgotPassword.toString(),
                fontSize = 13.sp,
            )
        }
        Button(
            { doAction(Login) },
            colors = buttonColors(
                backgroundColor = Color(0, 1, 0, 0x40)
            )
        ) {
            Text("Login")
        }
    }
}

@Composable
fun EmailPasswordFields(email: String, password: String, visible: Boolean, doAction: (LoginAction) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(R.string.LoginTopString.toString())
        EmailField(
            email = email,
            doAction = doAction,
        )
        PasswordField(
            password = password,
            visible = visible,
            doAction = doAction,
        )
    }
}

@Composable
fun EmailField(email: String, doAction: (LoginAction) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = email,
        onValueChange = { doAction(UpdateLogin(it)) },
        label = {
            Text(R.string.LoginEmail.toString())
        },
        leadingIcon = {
            Icon(Icons.Filled.Email, R.string.LoginEmail.toString())
        },
        modifier = modifier
    )
}

@Composable
fun PasswordField(password: String, visible: Boolean, doAction: (LoginAction) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = password,
        onValueChange = { doAction(UpdatePassword(it)) },
        label = {
            Text(R.string.LoginPassword.toString())
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = R.string.LoginPassword.toString()
            )
        },
        visualTransformation = if (visible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            val image = if (visible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            val description = if (visible) {
                R.string.HidePassword.toString()
            } else {
                R.string.ShowPassword.toString()
            }

            IconButton({
                doAction(ChangePasswordVisibility())
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