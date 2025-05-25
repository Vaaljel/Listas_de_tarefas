package com.example.listasdetarefas.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.listasdetarefas.R
import com.example.listasdetarefas.itemlista.TarefaItem
import com.example.listasdetarefas.model.Tarefa
import com.example.listasdetarefas.ui.theme.Purple40
import com.example.listasdetarefas.ui.theme.white
import com.google.firebase.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListaTarefas(
    navController: NavHostController
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40
                ),
                title = {
                    Text(
                        text = "Lista de Tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white,

                    )
                }
            )
        },
        containerColor = Color.Black,
        floatingActionButton ={
            FloatingActionButton(
                onClick = {
                    navController.navigate("salvarTarefas")
                },
                containerColor = Purple40
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Icon de salvar tarefa!"
                )
            }
        }
    ) { paddingValues ->
        val listaTarefas: MutableList<Tarefa> = mutableListOf(
            Tarefa(
                tarefa = "Jogar futbol",
                descricao = "giydfyfyikfik",
                prioridade = 0
            ),
            Tarefa(
                tarefa = "Ir ao cinema",
                descricao = "giydfyfyikfik",
                prioridade = 1
            ),
            Tarefa(
                tarefa = "ir a universidade",
                descricao = "giydfyfyikfik",
                prioridade = 2
            ),
            Tarefa(
                tarefa = "tarefa 5",
                descricao = "giydfyfyikfik",
                prioridade = 3
            )
        )
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            itemsIndexed(listaTarefas){ position, _ ->
                TarefaItem(position, listaTarefas)
            }
        }
    }
}