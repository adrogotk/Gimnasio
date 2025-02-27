package es.etg.dam.pmdm10.domain.usecase

import es.etg.dam.pmdm10.data.ClienteRepository
import es.etg.dam.pmdm10.domain.model.Cliente
import javax.inject.Inject

class InsertClienteUseCase @Inject constructor(
    private val repository: ClienteRepository
) {
    suspend operator fun invoke(cliente: Cliente): Long {
        val insert = repository.guardar(cliente)
        return insert
    }
}