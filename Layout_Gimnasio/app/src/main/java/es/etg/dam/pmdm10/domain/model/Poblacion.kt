package es.etg.dam.pmdm10.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Poblacion(
    var id: Long=0,
    var nombre:String

): Parcelable {
    override fun toString(): String {
        return "$nombre"
    }
}
