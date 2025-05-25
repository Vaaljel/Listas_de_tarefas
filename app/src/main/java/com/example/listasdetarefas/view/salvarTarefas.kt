package com.example.listasdetarefas.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.listasdetarefas.componentes.Botao
import com.example.listasdetarefas.componentes.CaixaDeTexto
import com.example.listasdetarefas.constantes.Constantes
import com.example.listasdetarefas.repositorio.TarefaRepositorio
import com.example.listasdetarefas.ui.theme.Purple40
import com.example.listasdetarefas.ui.theme.RADIO_BUTTON_GREEN_DISABLED
import com.example.listasdetarefas.ui.theme.RADIO_BUTTON_GREEN_SELECTED
import com.example.listasdetarefas.ui.theme.RADIO_BUTTON_RED_DISABLED
import com.example.listasdetarefas.ui.theme.RADIO_BUTTON_RED_SELECTED
import com.example.listasdetarefas.ui.theme.RADIO_BUTTON_YELLOW_DISABLED
import com.example.listasdetarefas.ui.theme.RADIO_BUTTON_YELLOW_SELECTED
import com.example.listasdetarefas.ui.theme.white
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SalvarTarefas(
    navController: NavHostController

) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tarefasRepositorio = TarefaRepositorio()

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40
                ),
                title = {
                    Text(
                        text = "Salvar  Tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white
                    )
                }
            )
        }
    ) { innerPadding ->


        var tituloTarefa by remember {
            mutableStateOf(value = "")
        }

        var descricaoTarefa by remember {
            mutableStateOf(value = "")
        }

        var prioridadeSelecionada by remember { mutableStateOf(-1) } // 0: Baixa, 1: Média, 2: Alta




        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){

            CaixaDeTexto(
                value = tituloTarefa,
                onValueChange ={
                    tituloTarefa = it
                } ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                label = "Titulo Tarefa",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )
            CaixaDeTexto(
                value = descricaoTarefa,
                onValueChange ={
                    descricaoTarefa = it
                } ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                label = "Descrição",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Nível de Prioridade")
                RadioButton(
                    selected =  prioridadeSelecionada == 1,
                    onClick = {
                        prioridadeSelecionada = 1
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_GREEN_DISABLED,
                        selectedColor = RADIO_BUTTON_GREEN_SELECTED
                    )
                )
                RadioButton(
                    selected =  prioridadeSelecionada == 2,
                    onClick = {
                        prioridadeSelecionada = 2
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_YELLOW_DISABLED,
                        selectedColor = RADIO_BUTTON_YELLOW_SELECTED
                    )
                )
                RadioButton(
                    selected =  prioridadeSelecionada == 3,
                    onClick = {
                        prioridadeSelecionada = 3
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_RED_DISABLED,
                        selectedColor = RADIO_BUTTON_RED_SELECTED
                    )
                )
            }
            Botao(
                onClick = {
                    scope.launch {
                        if (tituloTarefa.isBlank()) {
                            Toast.makeText(context, "Título da tarefa é obrigatório!", Toast.LENGTH_SHORT).show()
                            return@launch
                        }

                        if (descricaoTarefa.isBlank()) {
                            Toast.makeText(context, "Descrição da tarefa é obrigatória!", Toast.LENGTH_SHORT).show()
                            return@launch
                        }

                        // Determina a prioridade: se nenhuma marcada, será SEM_PRIORIDADE
                        val prioridadeFinal = if (prioridadeSelecionada in 1..3) {
                            prioridadeSelecionada
                        } else {
                            Constantes.SEM_PRIORIDADE
                        }

                        // Salvar em background
                        launch(Dispatchers.IO) {
                            tarefasRepositorio.salvarTarefa(
                                tituloTarefa,
                                descricaoTarefa,
                                prioridadeFinal
                            )
                        }
                        // Mensagem de sucesso
                        Toast.makeText(context, "Tarefa salva com sucesso!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(20.dp),
                texto = "Salvar"
            )

        }
    }

}


