package edu.phystech.kirillgrachoff.mipt.ui.viewmodels.places

import edu.phystech.kirillgrachoff.mipt.R
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

class Client {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun get(): CatalogResponse {
        return client.get {
            method = HttpMethod.Get
            url {
                protocol = URLProtocol.HTTP
                host = R.string.endpoint_url.toString()
                path("catalog")
            }
        }.body()
    }
}
