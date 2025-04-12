package bot

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.logging.LogLevel
import config.BotConfig
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.allInstances
import org.kodein.di.instance
import kotlin.system.exitProcess

class TelegramBot(override val di: DI) : DIAware {
    private val config: BotConfig by di.instance()
    
    private val handlers: List<BotHandler> by di.allInstances()
    
    private lateinit var botInstance: Bot

    private fun buildBot(): Bot {
        if (config.token.isBlank()) {
            throw IllegalArgumentException("Токен бота не может быть пустым. Проверьте переменную окружения BOT_TOKEN или .env файл.")
        }
        
        println("Используется токен бота: ${config.token.take(10)}...")
        
        return bot {
            token = config.token
            logLevel = LogLevel.All()
            
            dispatch {
                handlers.forEach { handler ->
                    try {
                        handler.registerHandlers(this)
                    } catch (e: Exception) {
                        println("Ошибка при регистрации обработчика ${handler::class.simpleName}: ${e.message}")
                    }
                }
            }
        }
    }
    
    fun start() {
        try {
            println("Запуск телеграм-бота...")
            println("Зарегистрировано обработчиков: ${handlers.size}")
            handlers.forEach { println("- ${it::class.simpleName}") }
            
            botInstance = buildBot()
            botInstance.startPolling()
            
            println("Бот успешно запущен")
        } catch (e: Exception) {
            println("Ошибка при запуске бота: ${e.message}")
            exitProcess(1)
        }
    }

    fun stop() {
        if (::botInstance.isInitialized) {
            botInstance.stopPolling()
            println("Бот остановлен")
        }
    }
}
