package apps.habits
import apps.habits.bot.HabitsHandlers
import bot.BotHandler
import org.kodein.di.DI
import org.kodein.di.bindSingleton

object HabitsModule {
    val module = DI.Module("habits") {
        bindSingleton<BotHandler>() { HabitsHandlers() }
    }
}