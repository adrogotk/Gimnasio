package es.etg.dam.pmdm10

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.etg.dam.pmdm10.data.DataAdapter
import es.etg.dam.pmdm10.data.ItemViewModel
import es.etg.dam.pmdm10.data.Maquina
import es.etg.dam.pmdm10.data.User

class SecondActivity : AppCompatActivity() , View.OnClickListener {

    companion object{
        const val EXTRA_MAQUINA ="ThirdActivity:Maquina"
        const val EXTRA_PERSONA ="ThirdActivity:User"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val usuario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(MainActivity.EXTRA_PERSONA, User::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<User>(MainActivity.EXTRA_PERSONA)
        }
        val user_info: TextView=findViewById(R.id.user_info)
        user_info.text=usuario.toString()
        val maquina: TextView=getMaquina("maquina1")
        maquina.setOnClickListener(this)
        val data = ArrayList<ItemViewModel>()
        for (i in 1..20) {
            val image = android.R.drawable.arrow_up_float
            val descripcion = "Descripción elemento $i"
            val opcion = "Perfil"
            data.add(ItemViewModel( image, descripcion, opcion))
        }

        val adapter = DataAdapter(data)
        val recyclerview = findViewById<RecyclerView>(R.id.menu)
        recyclerview.layoutManager = LinearLayoutManager(this)

        recyclerview.adapter = adapter
        adapter.setOnClickListener(object :
            DataAdapter.OnClickListener {
            override fun onClick(position: Int, model: ItemViewModel) {
                val msg:String = "Ha saleccionado el elemento ${model.descripcion}"
                Toast.makeText(this@SecondActivity, msg, Toast.LENGTH_SHORT).show()
            }
        })
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
}