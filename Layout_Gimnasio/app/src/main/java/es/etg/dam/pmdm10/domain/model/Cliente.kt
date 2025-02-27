package es.etg.dam.pmdm10.domain.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cliente (
    var id: Long=0,
    var nombre:String,
    var poblacion: Long

    ): Parcelable {
        override fun toString(): String {
            return "$nombre"
        }
}