package com.example.listasdetarefas.datasource

import com.google.firebase.firestore.FirebaseFirestore

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    fun salvarTarefa(tarefa: String, descricao: String, prioridade: Int ){

        val tarefaMap = hashMapOf(
            "tarefa" to tarefa,
            "descricao" to descricao,
            "prioridade" to prioridade
        )

        db.collection("tarefa").document(tarefa).set(tarefaMap).addOnCompleteListener {

        }.addOnFailureListener {

        }

    }
}