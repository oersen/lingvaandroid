package dev.atajan.lingva_android.api

import android.util.Log
import dev.atajan.lingva_android.api.entities.LanguagesEntity
import dev.atajan.lingva_android.api.entities.TranslationEntity
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

object LingvaApi {
    private const val lingvaApiV1 = "https://lingva.ml/api/v1/"

    private const val TIME_OUT = 3_000

    private val ktorHttpClient = HttpClient(Android) {

        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger Ktor =>", message)
                }
            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }
    }

    suspend fun getTranslation(
        source: String,
        target: String,
        query: String
    ): TranslationEntity {
        return ktorHttpClient.get("$lingvaApiV1$source/$target/$query")
    }

    suspend fun getSupportedLanguages(): LanguagesEntity {
        return ktorHttpClient.get(lingvaApiV1 + "languages/?:(source|target)")
    }
}
