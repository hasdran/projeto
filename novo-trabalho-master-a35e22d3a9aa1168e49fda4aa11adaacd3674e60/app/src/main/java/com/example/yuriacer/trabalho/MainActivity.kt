package com.example.yuriacer.trabalho

import android.arch.persistence.room.Room
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.yuriacer.trabalho.R.id.*
import com.example.yuriacer.trabalho.R.menu.menu_main
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //define o banco
        val db = Room.databaseBuilder(applicationContext,PetDatabase::class.java!!,"tb_pet")
                .allowMainThreadQueries()
                .build()


        tabela.adapter = PetAdapter(applicationContext,db!!.petDao().getAll())
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        tabela.layoutManager = layout

    }

    override fun onResume() {
        super.onResume()
        //define o banco
        val db = Room.databaseBuilder(applicationContext,PetDatabase::class.java!!,"tb_pet")
                .allowMainThreadQueries()
                .build()


        tabela.adapter = PetAdapter(applicationContext, db!!.petDao().getAll())
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        tabela.layoutManager = layout
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // infla o menu, lambad expression
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        action_insert -> {
            chamarAdicionar()
            true
        }

        search_name -> {

            dialogoProcurarNome(1)

            true
        }
        search_species -> {
            dialogoProcurarNome(2)
            true
        }
        else -> false
    }

    fun chamarAdicionar() {
        val intent = Intent(this, AddEditActivity::class.java)
        startActivity(intent)
    }

    fun dialogoProcurarNome(status: Int){

        val alert = AlertDialog.Builder(this)
        var editTextNome:EditText?=null

        // Builder
        with (alert) {
            setTitle("Preencha o campo")
            if(status == 1) {
                setMessage("Nome que deseja procurar:")
            }else if(status == 2){
                setMessage("Espécie que deseja procurar:")
            }
            // Add any  input field here
            editTextNome=EditText(context)
            editTextNome!!.inputType = InputType.TYPE_CLASS_TEXT

            val db = Room.databaseBuilder(applicationContext,PetDatabase::class.java!!,"tb_pet")
                    .allowMainThreadQueries()
                    .build()
            setPositiveButton("OK") {
                dialog, whichButton ->
                //showMessage("display the game score or anything!")
                dialog.dismiss()
                var nome=editTextNome!!.text.toString()
                if(status == 1) {
                    tabela.adapter = PetAdapter(applicationContext,db!!.petDao().loadPetByName(nome))
                } else if (status == 2){
                    tabela.adapter = PetAdapter(applicationContext,db!!.petDao().loadPetBySpecies(nome))
                }

            }

            setNegativeButton("NO") {
                dialog, whichButton ->
                //showMessage("Close the game or anything!")
                dialog.dismiss()
                tabela.adapter = PetAdapter(applicationContext,db!!.petDao().getAll())
            }
            val dialog = alert.create()
            dialog.setView(editTextNome)
            dialog.show()
        }
    }
}