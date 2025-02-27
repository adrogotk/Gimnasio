package es.etg.dam.pmdm10.domain.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Maquina (
    var nombre:String,

): Parcelable {
    override fun toString(): String {
        return "$nombre"
    }
}