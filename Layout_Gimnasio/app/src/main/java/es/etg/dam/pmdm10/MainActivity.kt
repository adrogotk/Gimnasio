package es.etg.dam.pmdm10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.etg.dam.pmdm10.data.User

class MainActivity : AppCompatActivity() , View.OnClickListener {
    companion object{
        const val EXTRA_PERSONA ="SegundaActivity:Usuario"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val boton: Button = findViewById(R.id.btn_login)
        boton.setOnClickListener(this)
        val nombre = leer()
        if(nombre !=null){
            val txtNombre = findViewById<EditText>(R.id.email_val).setText(nombre);
        }
    }
    override fun onClick (p0: View?){
        val emailVal: EditText=findViewById(R.id.email_val)
        val passwordVal: EditText=findViewById(R.id.password_val)
        val email: String=emailVal.text.toString()
        val password: String=passwordVal.text.toString()
        val usuario: User =User(email, password)
        guardar()
        val intent = Intent(this, SecondActivity::class.java).apply{
            putExtra(MainActivity.EXTRA_PERSONA, usuario)
        }
        startActivity(intent)
    }
    fun guardar(){
        val email= findViewById<EditText>(R.id.email_val).text
        val sharedPref = getPreferences(MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("email", email.toString())
        editor.apply()
    }
    fun leer(): String? {
        val sharedPref = getPreferences(MODE_PRIVATE)
        val email = sharedPref.getString("email", "")
        return email
    }
}