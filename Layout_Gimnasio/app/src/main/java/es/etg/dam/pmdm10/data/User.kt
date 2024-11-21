package es.etg.dam.pmdm10.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    var email:String,
    var password: String,

    ): Parcelable {
        override fun toString(): String {
            return "$email"
        }
}