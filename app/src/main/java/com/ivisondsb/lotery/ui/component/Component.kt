package com.ivisondsb.lotery.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.ivisondsb.lotery.R

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