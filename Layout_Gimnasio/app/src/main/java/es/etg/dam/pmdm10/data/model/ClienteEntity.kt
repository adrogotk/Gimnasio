package es.etg.dam.pmdm10.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize


@Entity (tableName = "cliente")
data class ClienteEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var nombre:String = "",
    var poblacion: Long = 0
): Parcelable {
}
