package com.example.yuriacer.trabalho

import android.arch.persistence.room.Room
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import kotlinx.android.synthetic.main.pet_item.view.*

class PetAdapter (context: Context,private val pets: List<Pet>):
        RecyclerView.Adapter<PetAdapter.ViewHolder>(){
    val db = Room.databaseBuilder(context,PetDatabase::class.java!!,"tb_pet")
            .allowMainThreadQueries()
            .build()
    override fun getItemCount() = pets.size

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.pet_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = pets[position]
        holder?.let {
            it.preencherView(pet)
            it.itemView.btRemover.setOnClickListener{
            }
            it.itemView.btAlterar.setOnClickListener{

            }
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun preencherView(pet: Pet) {
            val petId = pet.id
            val nome = itemView.txNome
            val especie = itemView.txEspecie
            val raca = itemView.txRaca
            val peso = itemView.txPeso
            val data_nasc = itemView.txData
            val porte = itemView.txPorte

            nome.text = pet.nome
            especie.text = pet.especie
            raca.text = pet.raca
            peso.text = pet.peso.toString()
            data_nasc.text = pet.data_nasc
            porte.text = pet.porte
        }

    }
}