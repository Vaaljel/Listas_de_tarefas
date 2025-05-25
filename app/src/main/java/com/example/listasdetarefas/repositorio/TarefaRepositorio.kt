package com.example.listasdetarefas.repositorio

import com.example.listasdetarefas.datasource.DataSource

class TarefaRepositorio{
    private val dataSource = DataSource()

    fun salvarTarefa(tarefa: String, descricao: String, prioridade: Int){
        dataSource.salvarTarefa(tarefa,descricao,prioridade)
    }
}