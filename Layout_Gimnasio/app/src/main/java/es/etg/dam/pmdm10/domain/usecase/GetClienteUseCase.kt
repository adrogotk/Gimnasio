package es.etg.dam.pmdm10.domain.usecase

import es.etg.dam.pmdm10.data.ClienteRepository
import es.etg.dam.pmdm10.data.model.ClienteEntity
import es.etg.dam.pmdm10.domain.model.Cliente
import javax.inject.Inject

class GetClienteUseCase @Inject constructor(
    private val repository: ClienteRepository
) {
    suspend operator fun invoke(id: Long): Cliente {
        val cliente = repository.leer(id)
        return cliente
    }
}