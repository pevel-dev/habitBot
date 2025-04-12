package bot

import com.github.kotlintelegrambot.dispatcher.Dispatcher

interface BotHandler {
    fun registerHandlers(dispatcher: Dispatcher)
} 