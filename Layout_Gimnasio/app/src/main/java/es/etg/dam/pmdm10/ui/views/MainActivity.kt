package es.etg.dam.pmdm10.ui.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import es.etg.dam.pmdm10.R
import es.etg.dam.pmdm10.ui.views.SecondActivity.Companion.ZERO
import es.etg.dam.pmdm10.data.model.ClienteDatabase
import es.etg.dam.pmdm10.data.model.ClienteEntity
import es.etg.dam.pmdm10.data.model.PoblacionEntity
import es.etg.dam.pmdm10.databinding.ActivityMainBinding
import es.etg.dam.pmdm10.domain.model.Cliente
import es.etg.dam.pmdm10.domain.model.Poblacion
import es.etg.dam.pmdm10.ui.viewmodels.ClienteViewModel
import es.etg.dam.pmdm10.ui.viewmodels.PoblacionViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , View.OnClickListener {
    companion object{
        const val EXTRA_PERSONA ="SegundaActivity:Usuario"
        const val EXTRA_POBLACION="SegundaActivity:Poblacion"
        lateinit var database: ClienteDatabase
        const val DATABASE_NAME = "cliente-db"
        const val MADRID: Long=28
    }
    private lateinit var binding: ActivityMainBinding
    private val clienteViewModel: ClienteViewModel by viewModels()
    private val poblacionViewModel: PoblacionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val boton: Button = findViewById(R.id.btn_login)
        boton.setOnClickListener(this)
        database =  Room.databaseBuilder(this,
            ClienteDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
    override fun onClick (p0: View?){
        val nombre: String = binding.nombreVal.text.toString()
        val poblacionText: String = binding.poblacionVal.text.toString()
        val clienteId=guardar(nombre, poblacionText)
        val intent = Intent(this, SecondActivity::class.java).apply{
            putExtra(EXTRA_PERSONA, clienteId)
        }
        startActivity(intent)
    }

    fun guardar(nombre: String, poblacionNombre: String): Long{
            var poblacionId: Long=0
            val resultado = CompletableDeferred<Long>()
            CoroutineScope(Dispatchers.IO).launch {
                var id: Long
                var poblacion=poblacionViewModel.getPoblacionPorNombre(poblacionNombre.uppercase())
                var cliente=clienteViewModel.getPersonaPorNombre(nombre.uppercase())
                if (cliente.nombre.uppercase()==""){
                    if (poblacion.nombre.uppercase()==""){
                        poblacion= Poblacion(0, poblacionNombre.uppercase())
                        poblacionId=poblacionViewModel.guardarPoblacion(poblacion)
                    }
                    else{
                        poblacionId=poblacion.id
                    }
                    cliente=Cliente(0, nombre.uppercase(), poblacionId)
                    id=clienteViewModel.guardarPersona(cliente)
                }
                else{
                    id=cliente.id
                }
                resultado.complete(id)
            }
        return runBlocking { resultado.await() }

    }
    fun loadData(usuarioId: Long): Long{
        var poblacionId: Long=0
        CoroutineScope(Dispatchers.IO).launch {
            val cliente =clienteViewModel.leerPersona(usuarioId)
            val poblacion = poblacionViewModel.leerPoblacion(cliente.poblacion)
            if (poblacion.id>0) {
                poblacionId=poblacion.id
            }
        }
        return poblacionId
    }
}