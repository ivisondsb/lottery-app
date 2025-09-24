package com.ivisondsb.lotery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivisondsb.lotery.ui.theme.Green
import com.ivisondsb.lotery.ui.theme.LoteryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoteryTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        CardLottery("Mega Sena")
    }
}

@Composable
fun CardLottery(name: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .shadow(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(Green)
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.trevo),
                contentDescription = "Trevo",
                modifier.size(100.dp)
            )
            Spacer(modifier.size(10.dp))
            Text(
                name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    HomeScreen()
}