package com.ivisondsb.lotery.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivisondsb.lotery.R
import com.ivisondsb.lotery.ui.theme.Green

@Composable
fun LoNumberTextField(
    value: String,
    @StringRes label: Int, // É um Int por causa que os arquivos R são referenciados como inteiros.
    @StringRes placeholder: Int,
    imeAction: ImeAction = ImeAction.Next,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        maxLines = 1,
        label = {
            Text(stringResource(id = label))
        },
        placeholder = {
            Text(stringResource(id = placeholder))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        onValueChange = onValueChanged

    )
}

@Composable
fun LoItemType(
    modifier: Modifier = Modifier,
    name: String,
    color: Color = Color.Black,
    bgColor: Color = Color.Transparent
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .wrapContentSize()
            .background(bgColor)
    ) {
        Image(
            painter = painterResource(R.drawable.trevo),
            contentDescription = stringResource(R.string.clover),
            modifier
                .size(100.dp)
                .padding(10.dp)
        )
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            color = color,
            fontSize = 18.sp,
            modifier = modifier
                .padding(10.dp, 0.dp)

        )
        Spacer(modifier.padding(1.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun LoNumberPreview() {
    LoNumberTextField(
        value = "7",
        label = R.string.mega_rule,
        placeholder = R.string.quantity,
        imeAction = ImeAction.Done
    ) {}
}

@Preview(showBackground = true)
@Composable
private fun LoItemPreview() {
    LoItemType(
        name = "Test",
        color = Color.White,
        bgColor = Green
    )
}