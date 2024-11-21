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
import es.etg.dam.pmdm10.data.Maquina
import es.etg.dam.pmdm10.data.User

class ThirdActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val EXTRA_PERSONA ="SegundaActivity:Persona"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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