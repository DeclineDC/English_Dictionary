package com.decline.dictionaryapp.feature_dictionary.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.decline.dictionaryapp.feature_dictionary.domain.model.Word

@Composable
fun WordItem(
    word: Word,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(24.dp, vertical = 30.dp)) {
        Text(
            text = word.word,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
        )
        word.phonetic?.let { phonetic ->
            Text(text = phonetic, fontWeight = FontWeight.Light)
        }
        Spacer(modifier = Modifier.height(16.dp))
        word.origin?.let { origin ->
            Text(fontSize = 18.sp, text = origin)
        }
        Spacer(modifier = modifier.height(16.dp))

        word.meanings.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            meaning.definitions.forEachIndexed { index, definition ->
                Text(
                    fontSize = 18.sp,
                    text = "${index + 1}. ${definition.definition}"

                )
                Spacer(modifier = modifier.height(8.dp))
                definition.example?.let { example ->
                    Text(
                        fontSize = 18.sp,
                        text = "\"$example\"",
                        fontStyle = FontStyle.Italic
                    )
                }
                Spacer(modifier = modifier.height(8.dp))

            }
            Spacer(modifier = modifier.height(16.dp))
        }
    }
}
