package es.etg.dam.pmdm10.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface TiempoApiService {
    @GET
    suspend fun getTiempo(@Url url:String): Response<TiempoResponse>

}