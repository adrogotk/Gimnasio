package es.etg.dam.pmdm10.data.provider

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.etg.dam.pmdm10.data.dao.ClienteDao
import es.etg.dam.pmdm10.data.model.ClienteDatabase
import es.etg.dam.pmdm10.ui.views.MainActivity
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ClienteProvider {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ClienteDatabase {
        return Room.databaseBuilder(
            context,
            ClienteDatabase::class.java,
            MainActivity.DATABASE_NAME
        ).build()
    }
}