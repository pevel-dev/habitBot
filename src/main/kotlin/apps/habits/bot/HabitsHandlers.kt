package apps.habits.bot

import bot.BotHandler
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId

class HabitsHandlers: BotHandler {
    override fun registerHandlers(dispatcher: Dispatcher) {
        dispatcher.command("habits") {
            val chatId = ChatId.fromId(update.message?.chat?.id ?: return@command)
            bot.sendMessage(chatId = chatId, text = "hello")
        }

        dispatcher.text("help"){
            val chatId = ChatId.fromId(update.message?.chat?.id ?: return@text)

            bot.sendMessage(chatId = chatId, text = "hello")
        }
    }
}