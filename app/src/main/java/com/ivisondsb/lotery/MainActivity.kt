package com.ivisondsb.lotery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivisondsb.lotery.ui.component.LoItemType
import com.ivisondsb.lotery.ui.component.LoNumberTextField
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
            LoItemType(name = "Mega Sena")
            Text(
                text = stringResource(R.string.announcement),
                fontStyle = FontStyle.Italic,
                modifier = modifier
                    .wrapContentSize()
                    .padding(14.dp),
            )
            LoNumberTextField(
                value = qtdNumbers,
                label = R.string.bets,
                placeholder = R.string.bets_quantity
            ) {
                if (it.length < 3) {
                    qtdNumbers = validateInput(it)
                }
            }
            LoNumberTextField(
                value = qtdBets,
                label = R.string.mega_rule,
                placeholder = R.string.quantity,
                imeAction = ImeAction.Done
            ) {
                if (it.length < 3) {
                    qtdBets = validateInput(it)
                }
            }
            OutlinedButton(
                enabled = qtdNumbers.isNotEmpty() && qtdBets.isNotEmpty(),
                onClick = {}
            ) {
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoItemType(
                name = name,
                color = Color.White,
                bgColor = Green
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

@Preview
@Composable
private fun FormScreenPreview() {
    FormScreen()
}