package es.etg.dam.pmdm10.domain.usecase

import es.etg.dam.pmdm10.data.ClienteRepository
import es.etg.dam.pmdm10.domain.model.Cliente
import javax.inject.Inject

class GetClientePorNombreUseCase @Inject constructor(
    private val repository: ClienteRepository
) {
    suspend operator fun invoke(nombre: String): Cliente {
        val cliente = repository.getClientePorNombre(nombre)
        return cliente
    }
}