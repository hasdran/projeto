package com.example.yuriacer.trabalho

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import java.util.*

@Dao
interface PetDao {
    // Seleciona tudo
    @Query("SELECT * from tb_pet")
    fun getAll(): List<Pet>
    //seleciona um pelo id
    @Query("select * from tb_pet where id = :id")
    fun loadPetById(id: Int): Pet

    //filtrar por nome
    @Query("select * from tb_pet where nome = :nome")
    fun loadPetByName(nome: String): List<Pet>
    //filtrar por especie
    @Query("select * from tb_pet where especie = :especie")
    fun loadPetBySpecies(especie: String): List<Pet>
    // insere novo
    @Insert
    fun insert(pet: Pet)

    //atualiza pelo id
    @Query("UPDATE tb_pet set nome = :nome,especie = :especie, raca = :raca, peso = :peso, data_nasc = :data_nasc, porte = :porte where id = :id")
    fun updatePetById(nome: String, especie: String, raca: String, peso: Double, data_nasc: String, porte: String,id: Int)

    //deleta de acordo com id
    @Query("delete from tb_pet where id like :id")
    fun deletePetById(id: Int): Int
}