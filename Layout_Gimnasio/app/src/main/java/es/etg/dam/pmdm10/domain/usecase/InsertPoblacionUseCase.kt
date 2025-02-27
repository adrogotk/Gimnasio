package es.etg.dam.pmdm10.domain.usecase

import es.etg.dam.pmdm10.data.ClienteRepository
import es.etg.dam.pmdm10.data.PoblacionRepository
import es.etg.dam.pmdm10.domain.model.Cliente
import es.etg.dam.pmdm10.domain.model.Poblacion
import javax.inject.Inject

class InsertPoblacionUseCase @Inject constructor(
    private val repository: PoblacionRepository
) {
    suspend operator fun invoke(poblacion: Poblacion): Long {
        val insert = repository.guardar(poblacion)
        return insert
    }
}