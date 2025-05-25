package com.example.listasdetarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listasdetarefas.ui.theme.ListasDeTarefasTheme
import com.example.listasdetarefas.view.ListaTarefas
import com.example.listasdetarefas.view.SalvarTarefas

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListasDeTarefasTheme { // Aqui vamos configurar todas as telas da aplicação-- organizar as rotas da nossa aplicação

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "ListaTarefas"){
                    composable(
                        route  ="ListaTarefas"
                    ){
                        ListaTarefas(navController)
                    }

                    composable(
                        route = "salvarTarefas"
                    ){
                        SalvarTarefas(navController)
                    }

                }

            }
        }
    }
}

