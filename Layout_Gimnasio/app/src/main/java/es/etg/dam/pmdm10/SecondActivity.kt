package es.etg.dam.pmdm10

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import es.etg.dam.pmdm10.MainActivity.Companion.database
import es.etg.dam.pmdm10.data.ClienteEntity
import es.etg.dam.pmdm10.data.ClienteViewModel
import es.etg.dam.pmdm10.data.FragmentActionsListener
import es.etg.dam.pmdm10.data.LogoFragment
import es.etg.dam.pmdm10.data.Maquina
import es.etg.dam.pmdm10.data.MenuFragment
import es.etg.dam.pmdm10.data.TiempoApiService
import es.etg.dam.pmdm10.data.TiempoResponse
import es.etg.dam.pmdm10.data.TitleFragment
import es.etg.dam.pmdm10.databinding.ActivitySecondBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondActivity : AppCompatActivity() , View.OnClickListener, FragmentActionsListener {

    companion object{
        const val EXTRA_MAQUINA ="ThirdActivity:Maquina"
        const val EXTRA_PERSONA ="ThirdActivity:User"
        const val ZERO=0
        const val COD_MADRID="28"
        const val COD_PARLA="28106"
        const val GRADOS="º"
        const val LITROSM2="l/m2"
        const val BASE_URL: String = "https://www.el-tiempo.net/api/json/v2/provincias/%s" +
                                     "/municipios/"
    }
    private lateinit var binding: ActivitySecondBinding
    private val clienteViewModel: ClienteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val bundle = Bundle()
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<LogoFragment>(binding.logoFragmento.id, args=bundle)
                add<TitleFragment>(binding.titleFragmento.id, args=bundle)
                add<MenuFragment>(binding.menuFragmento.id, args=bundle)
            }
        }
        val usuario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(MainActivity.EXTRA_PERSONA, ClienteEntity::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<ClienteEntity>(MainActivity.EXTRA_PERSONA)
        }
        //val poblacion: String=loadData(usuario!!.id)
        consultar()
        //binding.userInfo.text=usuario.toString()
        val maquina: TextView=getMaquina("maquina1")
        maquina.setOnClickListener(this)
        val user_info=binding.userInfo
        //user_info.text=usuario.toString()
        clienteViewModel.cliente.observe(this, Observer { cliente ->
            user_info.text = cliente.nombre
        })
        user_info.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
            clienteViewModel.leerPersona(usuario!!.id)
            }
        }
    }
    override fun onClick (p0: View?){
        val nombre: String=getMaquina("maquina1").text.toString()
        val maquina: Maquina=Maquina(nombre)
        val intent = Intent(this, ThirdActivity::class.java).apply{
            putExtra(SecondActivity.EXTRA_MAQUINA, maquina)
        }
        startActivity(intent)
    }
    fun getMaquina(idMaquina: String): TextView{
        val maquina: TextView = findViewById(R.id.maquina1)
        return maquina
    }
    fun loadData(usuarioId: Long): String {
        var poblacionNombre: String=""
        CoroutineScope(Dispatchers.IO).launch {
            val poblacionDao = database.poblacionDao()
            val poblaciones = poblacionDao.getPoblacion(usuarioId)
            val poblacionCliente=poblaciones.get(ZERO)
            val poblacionEntity=poblacionCliente.poblacion
            poblacionNombre=poblacionEntity.nombre
        }
        return poblacionNombre
    }
    private fun getRetrofit(): Retrofit {
        val url= BASE_URL.format(COD_MADRID)
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun consultar(){
        CoroutineScope(Dispatchers.IO).launch {
            //Consulto el tiempo de la provincia
            val call = getRetrofit().create(TiempoApiService::class.java).getTiempo(COD_PARLA)
            // A la respuesta le pido que me de el TiempoResponse
            val tiempo: TiempoResponse? = call.body()

            //este código se ejecuta en el thread principal (ui)
            runOnUiThread {
                //Se ejecutó correctamente el servicio
                if(call.isSuccessful) {
                    binding.temperatura.text= tiempo?.temperatura_actual.toString() + GRADOS
                    binding.precipitaciones.text=tiempo?.precipitacion.toString() + LITROSM2
                    //Toast.makeText(this@SecondActivity,"ok", Toast.LENGTH_LONG).show()
                }else{//Da error la ejecución del servicio
                    Log.v("api error", call.toString())
                    //Toast.makeText(this@SecondActivity,"Error al consultar el servicio", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    override fun onClickFragmentButton() {
        TODO("Not yet implemented")
    }
}