package com.example.listasdetarefas.datasource

import com.example.listasdetarefas.model.Tarefa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

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

    fun recuperarTarefa(){
        val listaTarefas: MutableList<Tarefa> = mutableListOf()
        db.collection("tarefa").get().addOnCompleteListener { querySnapshot ->
            if(querySnapshot.isSuccessful){
                for (documento in querySnapshot.result.documents){
                    val tarefa = documento.toObject(Tarefa::class.java)
                    if(tarefa != null){
                        listaTarefas.add(tarefa)
                    }
                }
            }
        }
    }
}

