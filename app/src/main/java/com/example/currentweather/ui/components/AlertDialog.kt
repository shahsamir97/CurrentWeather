package com.example.currentweather.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currentweather.R
import com.example.currentweather.ui.theme.CurrentWeatherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialog(onDismissRequest: () -> Unit, title: String, actionButtonTitle: String, onClickActionButton: () -> Unit) {
    BasicAlertDialog(onDismissRequest = onDismissRequest) {
        Card {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = onClickActionButton
                ) {
                    Text(text = actionButtonTitle)
                }
            }
        }
    }
}

@Preview
@Composable
fun AlertDialogPreview() {
    CurrentWeatherTheme {
        AlertDialog(
            onDismissRequest = {},
            title = stringResource(R.string.something_went_wrong),
            actionButtonTitle = stringResource(R.string.try_again),
            onClickActionButton = {}
        )
    }
}