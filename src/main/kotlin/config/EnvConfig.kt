package config

import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import java.io.File

object EnvConfig {
    private val dotenv: Dotenv by lazy {
        val envFile = File(".env")
        if (envFile.exists()) {
            dotenv {
                directory = "./"
                ignoreIfMalformed = true
                ignoreIfMissing = true
            }
        } else {
            dotenv {
                ignoreIfMalformed = true
                ignoreIfMissing = true
            }
        }
    }

    /**
     * Получение значения переменной окружения.
     * @param key ключ переменной
     * @param defaultValue значение по умолчанию, если переменная не найдена
     * @return значение переменной окружения или значение по умолчанию
     */
    fun get(key: String, defaultValue: String? = null): String {
        return try {
            // Пробуем получить из .env файла или из переменных окружения системы
            dotenv[key] ?: System.getenv(key) ?: defaultValue ?: throw IllegalArgumentException("Переменная окружения $key не найдена")
        } catch (e: Exception) {
            if (defaultValue != null) {
                return defaultValue
            }
            throw e
        }
    }

    /**
     * Получение токена бота.
     * @param defaultValue значение по умолчанию, если переменная не найдена
     * @return токен бота
     */
    fun getBotToken(defaultValue: String? = null): String {
        return get("BOT_TOKEN", defaultValue)
    }
} 