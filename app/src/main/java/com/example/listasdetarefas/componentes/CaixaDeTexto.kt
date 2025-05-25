package com.example.listasdetarefas.componentes


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.listasdetarefas.ui.theme.LIGHT_BLUE
import com.example.listasdetarefas.ui.theme.white

@Composable
fun CaixaDeTexto(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int =1,
    keyboardType: KeyboardType = KeyboardType.Text
){
    OutlinedTextField(
        value = value,
        onValueChange,
        modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLines,
        colors = OutlinedTextFieldDefaults.colors(
            //focusedTextColor = black,
            cursorColor = LIGHT_BLUE,
            focusedBorderColor = LIGHT_BLUE,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = LIGHT_BLUE,
            unfocusedLabelColor = LIGHT_BLUE,
            focusedContainerColor = white,
            unfocusedContainerColor = white
        ),
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}




