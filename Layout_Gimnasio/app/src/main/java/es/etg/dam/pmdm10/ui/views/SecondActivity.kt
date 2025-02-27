package es.etg.dam.pmdm10.ui.views

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
import dagger.hilt.android.AndroidEntryPoint
import es.etg.dam.pmdm10.R
import es.etg.dam.pmdm10.ui.views.MainActivity.Companion.database
import es.etg.dam.pmdm10.data.model.ClienteEntity
import es.etg.dam.pmdm10.ui.viewmodels.ClienteViewModel
import es.etg.dam.pmdm10.ui.fragments.FragmentActionsListener
import es.etg.dam.pmdm10.ui.fragments.LogoFragment
import es.etg.dam.pmdm10.domain.model.Maquina
import es.etg.dam.pmdm10.ui.fragments.MenuFragment
import es.etg.dam.pmdm10.data.rest.TiempoApiService
import es.etg.dam.pmdm10.data.model.TiempoResponse
import es.etg.dam.pmdm10.ui.fragments.TitleFragment
import es.etg.dam.pmdm10.databinding.ActivitySecondBinding
import es.etg.dam.pmdm10.ui.viewmodels.PoblacionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() , View.OnClickListener, FragmentActionsListener {

    companion object{
        const val EXTRA_MAQUINA ="ThirdActivity:Maquina"
        const val EXTRA_PERSONA ="ThirdActivity:User"
        const val EXTRA_USUARIO ="SegundaActivity:Usuario"
        const val MAQUINA="maquina1"
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
        val usuarioId = intent.extras!!.getLong(EXTRA_USUARIO)
        consultar()
        val maquina: TextView=getMaquina(MAQUINA)
        maquina.setOnClickListener(this)
        val user_info=binding.userInfo
        clienteViewModel.cliente.observe(this, Observer { cliente ->
            user_info.text = cliente.nombre
        })
        user_info.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
            clienteViewModel.leerPersona(usuarioId)
            }
        }
    }
    override fun onClick (p0: View?){
        val nombre: String=getMaquina("maquina1").text.toString()
        val maquina: Maquina = Maquina(nombre)
        val intent = Intent(this, ThirdActivity::class.java).apply{
            putExtra(EXTRA_MAQUINA, maquina)
        }
        startActivity(intent)
    }
    fun getMaquina(idMaquina: String): TextView{
        val maquina: TextView = findViewById(R.id.maquina1)
        return maquina
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
            val call = getRetrofit().create(TiempoApiService::class.java).getTiempo(COD_PARLA)
            val tiempo: TiempoResponse? = call.body()

            runOnUiThread {
                if(call.isSuccessful) {
                    binding.temperatura.text= tiempo?.temperatura_actual.toString() + GRADOS
                    binding.precipitaciones.text=tiempo?.precipitacion.toString() + LITROSM2
                }else{
                    Log.v("api error", call.toString())
                }
            }
        }
    }
    override fun onClickFragmentButton() {
        TODO("Not yet implemented")
    }
}