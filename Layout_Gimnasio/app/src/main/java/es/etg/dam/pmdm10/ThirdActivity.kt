package es.etg.dam.pmdm10

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import es.etg.dam.pmdm10.data.LogoFragment
import es.etg.dam.pmdm10.data.Maquina
import es.etg.dam.pmdm10.data.MenuFragment
import es.etg.dam.pmdm10.data.TitleFragment
import es.etg.dam.pmdm10.data.User
import es.etg.dam.pmdm10.databinding.ActivitySecondBinding
import es.etg.dam.pmdm10.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val EXTRA_PERSONA ="SegundaActivity:Persona"
    }
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<LogoFragment>(binding.avatar.id)
                add<TitleFragment>(binding.titleFragmento.id)
                add<MenuFragment>(binding.menu.id)
            }
        }
        val boton: Button = findViewById(R.id.btn_log_out)
        boton.setOnClickListener(this)
        val maquina = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(SecondActivity.EXTRA_MAQUINA, Maquina::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Maquina>(SecondActivity.EXTRA_MAQUINA)
        }
        val nombre_maquina: TextView =findViewById(R.id.nombreMaquina)
        nombre_maquina.text=maquina.toString()
    }

    override fun onClick (p0: View?){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}