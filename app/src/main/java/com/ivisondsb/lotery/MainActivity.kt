package com.ivisondsb.lotery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivisondsb.lotery.ui.theme.Green
import com.ivisondsb.lotery.ui.theme.LoteryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoteryTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AppRouter.HOME.route
                ) {
                    composable(AppRouter.HOME.route) {
                        HomeScreen {
                            navController.navigate(AppRouter.FORM.route)
                        }
                    }
                    composable(AppRouter.FORM.route) {
                        FormScreen()
                    }
                }
            }
        }
    }
}

enum class AppRouter(val route: String) {
    HOME("home"),
    FORM("form")
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        CardLottery(
            "Mega Sena",
            onClick = onClick
        )
    }
}

@Composable
fun FormScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var qtdNumbers by remember { mutableStateOf("") }
        var qtdBets by remember { mutableStateOf("") }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Spacer(modifier.size(20.dp))
            Image(
                painter = painterResource(R.drawable.trevo),
                contentDescription = stringResource(R.string.clover),
                modifier
                    .size(100.dp)
                    .padding(10.dp)
            )
            Text(
                text = "Mega Sena",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.announcement),
                fontStyle = FontStyle.Italic,
                modifier = modifier
                    .wrapContentSize()
                    .padding(14.dp),
            )
            OutlinedTextField(
                value = qtdNumbers,
                maxLines = 1,
                label = {
                    Text(stringResource(id = R.string.mega_rule))
                },
                placeholder = {
                    Text(stringResource(id = R.string.quantity))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                onValueChange = {
                    if (it.length < 3) {
                        qtdNumbers = validateInput(it)
                    }
                }
            )
            OutlinedTextField(
                value = qtdBets, maxLines = 1, label = {
                    Text(stringResource(R.string.bets))
                },
                placeholder = {
                    Text(stringResource(id = R.string.bets_quantity))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                onValueChange = {
                    if (it.length < 3) {
                        qtdBets = validateInput(it)
                    }
                }
            )
            OutlinedButton(onClick = {}) {
                Text(stringResource(R.string.bets_generate))
            }
        }
    }
}

@Composable
fun CardLottery(name: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .wrapContentSize()
            .clickable {
                onClick()
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .wrapContentSize()
                .background(Green)
        ) {
            Image(
                painter = painterResource(R.drawable.trevo),
                contentDescription = stringResource(R.string.clover),
                modifier = modifier
                    .size(100.dp)
                    .padding(10.dp)
            )
            Text(
                name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = modifier.padding(
                    8.dp,
                    0.dp,
                    8.dp,
                    8.dp
                )
            )
        }
    }
}

fun validateInput(text: String): String {
    return text.filter { it.isDigit() }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    HomeScreen { }
}

@Preview()
@Composable
private fun FormScreenPreview() {
    FormScreen()
}