package es.etg.dam.pmdm10

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import es.etg.dam.pmdm10.SecondActivity.Companion.ZERO
import es.etg.dam.pmdm10.data.ClienteDatabase
import es.etg.dam.pmdm10.data.ClienteEntity
import es.etg.dam.pmdm10.data.PoblacionDao
import es.etg.dam.pmdm10.data.PoblacionEntity
import es.etg.dam.pmdm10.data.User
import es.etg.dam.pmdm10.databinding.ActivityMainBinding
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class MainActivity : AppCompatActivity() , View.OnClickListener {
    companion object{
        const val EXTRA_PERSONA ="SegundaActivity:Usuario"
        const val EXTRA_POBLACION="SegundaActivity:Poblacion"
        lateinit var database: ClienteDatabase
        const val DATABASE_NAME = "cliente-db"
        const val MADRID: Long=28
    }
    private lateinit var binding: ActivityMainBinding
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
        val nombre = leer()
        if(nombre !=null){
            val txtNombre = findViewById<EditText>(R.id.nombre_val).setText(nombre);
        }
        MainActivity.database =  Room.databaseBuilder(this,
            ClienteDatabase::class.java,
            DATABASE_NAME).build()
    }
    override fun onClick (p0: View?){
        /*val emailVal: EditText=findViewById(R.id.nombre_val)
        val passwordVal: EditText=findViewById(R.id.password_val)
        val email: String=emailVal.text.toString()
        val password: String=passwordVal.text.toString()
        val usuario: User =User(email, password)
        guardarPreferencias()*/
        val nombre: String = binding.nombreVal.text.toString()
        val poblacionText: String = binding.poblacionVal.text.toString()
        val clienteId=guardar(nombre, poblacionText)
        val poblacionId=loadData(clienteId)
        val usuario: ClienteEntity=ClienteEntity(clienteId, nombre, poblacionId)
        val intent = Intent(this, SecondActivity::class.java).apply{
            putExtra(MainActivity.EXTRA_POBLACION, poblacionId);
            putExtra(MainActivity.EXTRA_PERSONA, usuario)
        }
        startActivity(intent)
    }
    fun guardarPreferencias(){
        val email= findViewById<EditText>(R.id.nombre_val).text
        val sharedPref = getPreferences(MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("email", email.toString())
        editor.apply()
    }

    fun guardar(nombre: String, poblacion: String): Long{
            val poblacionObjeto = PoblacionEntity(0, poblacion)
           // val cliente = ClienteEntity(0, nombre,poblacion);
            var poblacionId: Long=0
            val clienteDao = database.clienteDao()
            val poblacionDao = database.poblacionDao()
            var id: Long=0
            val resultado = CompletableDeferred<Long>()
            CoroutineScope(Dispatchers.IO).launch {
                var id_corrutina: Long
                val poblacion=poblacionDao.getPoblacionNombre(poblacion)
                val clienteNombre=clienteDao.getCliente(nombre)
                if (clienteNombre.count()<1){
                    if (poblacion.count()<1){
                        poblacionId=poblacionDao.insert(poblacionObjeto)
                    }
                    else{
                        poblacionId=poblacion.get(ZERO).id
                    }
                    val cliente = ClienteEntity(0, nombre, poblacionId);
                    id_corrutina=clienteDao.insert(cliente)
                    Log.v("id corrutina: ", id_corrutina.toString())
                }
                else{
                    id_corrutina=clienteNombre.get(ZERO).id
                    Log.v("id corrutina: ", id_corrutina.toString())
                }
                resultado.complete(id_corrutina)
            }
        return runBlocking { resultado.await() }

    }
    fun leer(): String? {
        val sharedPref = getPreferences(MODE_PRIVATE)
        val email = sharedPref.getString("email", "")
        return email
    }
    fun loadData(usuarioId: Long): Long{
        var poblacionId: Long=0
        CoroutineScope(Dispatchers.IO).launch {
            val poblacionDao = database.poblacionDao()
            val poblaciones = poblacionDao.getPoblacion(usuarioId)
            if (poblaciones.count()>0) {
                val poblacionCliente = poblaciones.get(ZERO)
                val poblacionEntity=poblacionCliente.poblacion
                poblacionId=poblacionEntity.id
            }
        }
        return poblacionId
    }
}