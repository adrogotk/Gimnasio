package es.etg.dam.pmdm10.ui.views

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import es.etg.dam.pmdm10.R
import es.etg.dam.pmdm10.ui.fragments.LogoFragment
import es.etg.dam.pmdm10.domain.model.Maquina
import es.etg.dam.pmdm10.ui.fragments.MenuFragment
import es.etg.dam.pmdm10.ui.fragments.TitleFragment
import es.etg.dam.pmdm10.databinding.ActivityThirdBinding

@AndroidEntryPoint
class ThirdActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val EXTRA_PERSONA ="SegundaActivity:Persona"
        const val CAMERA_REQUEST_CODE = 0
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
        val botonQR: Button = binding.btnActiveQr
        botonQR.setOnClickListener {
            checkCameraPermission()
        }
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
    private fun checkCameraPermission(){
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission()
        } else {
            Toast.makeText(this,"Acceso a la funcionalidad", Toast.LENGTH_SHORT).show()
        }
    }
    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.CAMERA)) {
            Toast.makeText(this,"Conceda permisos en ajustes", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf( android.Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this,"Acceso a la funcionalidad una vez aceptado el permiso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,"Conceda permisos en ajustes", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {
            }
        }
    }
}