package es.etg.dam.pmdm10

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.etg.dam.pmdm10.MainActivity.Companion.database
import es.etg.dam.pmdm10.data.DataAdapter
import es.etg.dam.pmdm10.data.FragmentActionsListener
import es.etg.dam.pmdm10.data.ItemViewModel
import es.etg.dam.pmdm10.data.LogoFragment
import es.etg.dam.pmdm10.data.Maquina
import es.etg.dam.pmdm10.data.MenuFragment
import es.etg.dam.pmdm10.data.PoblacionEntity
import es.etg.dam.pmdm10.data.TitleFragment
import es.etg.dam.pmdm10.data.User
import es.etg.dam.pmdm10.databinding.ActivityMainBinding
import es.etg.dam.pmdm10.databinding.ActivitySecondBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() , View.OnClickListener, FragmentActionsListener {

    companion object{
        const val EXTRA_MAQUINA ="ThirdActivity:Maquina"
        const val EXTRA_PERSONA ="ThirdActivity:User"
        const val ZERO=0
    }
    private lateinit var binding: ActivitySecondBinding
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
            intent.getParcelableExtra(MainActivity.EXTRA_PERSONA, User::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<User>(MainActivity.EXTRA_PERSONA)
        }
        val user_info: TextView=findViewById(R.id.user_info)
        //val poblacion: String=loadData(usuario.id)
        user_info.text=usuario.toString()
        val maquina: TextView=getMaquina("maquina1")
        maquina.setOnClickListener(this)
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
        val datos = ArrayList<String>()
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

    override fun onClickFragmentButton() {
        TODO("Not yet implemented")
    }
}