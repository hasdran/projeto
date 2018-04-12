package com.example.yuriacer.trabalho

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Ignore
import java.util.*

@Entity(tableName = "tb_pet")
class Pet {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    @ColumnInfo(name = "nome")
    var nome: String? = null
    @ColumnInfo(name = "especie")
    var especie: String? = null
    @ColumnInfo(name = "raca")
    var raca: String? = null
    @ColumnInfo(name = "peso")
    var peso: Double? = null
    @ColumnInfo(name = "data_nasc")
    var data_nasc: String? = null
    @ColumnInfo(name = "porte")
    var porte: String? = null
// formato da tabela
}