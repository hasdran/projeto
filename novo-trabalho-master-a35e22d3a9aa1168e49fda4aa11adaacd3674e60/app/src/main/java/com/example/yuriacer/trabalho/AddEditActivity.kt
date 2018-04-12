package com.example.yuriacer.trabalho

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_edit.*
import kotlinx.android.synthetic.main.pet_item.view.*

class AddEditActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        if(v == btAdd){
            val newPet = Pet()
            newPet.nome = inputName.text.toString()
            newPet.especie = inputSpecies.text.toString()
            newPet.raca = inputRace.text.toString()
            newPet.peso = inputWeight.text.toString().toDouble()
            newPet.data_nasc = inputDate.text.toString()
            newPet.porte = "vazio"
            if(radioBig.isChecked){
                newPet.porte = "grande"
            } else if(radioMedium.isChecked){
                newPet.porte = "medio"
            } else if (radioSmall.isChecked){
                newPet.porte = "pequeno"
            }

                val db = Room.databaseBuilder(applicationContext,PetDatabase::class.java!!,"tb_pet")
                        .allowMainThreadQueries()
                        .build()

                db.petDao().insert(newPet)

                val v = ArrayList<Pet>()
                db!!.petDao().getAll().forEach{
                    v.add(it)
                    Log.i("@Yuraso", """${it.id}""")
                    Log.i("@Yuraso", """${it.nome}""")
                    Log.i("@Yuraso", """${it.especie}""")
                    Log.i("@Yuraso", """${it.raca}""")
                    Log.i("@Yuraso", """${it.peso}""")
                    Log.i("@Yuraso", """${it.data_nasc}""")
                    Log.i("@Yuraso", """${it.porte}""")
                }

                finish()

        } else if(v == btCancel){
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        btAdd.setOnClickListener(this)
        btCancel.setOnClickListener(this)
    }
}
