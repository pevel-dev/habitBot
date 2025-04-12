import apps.habits.HabitsModule
import bot.TelegramBot
import di.AppModule
import org.kodein.di.instance

fun main() {
    println("Запуск приложения...")

    val handlerModules = listOf(
        HabitsModule.module
    )

    val di = AppModule.setupDependencyInjection(handlerModules)

    val bot by di.instance<TelegramBot>()
    bot.start()

    // Ожидание для поддержания бота запущенным
    println("Для завершения работы нажмите Enter...")
    readLine()

    // Останавливаем бота при завершении программы
    bot.stop()
    println("Приложение завершено")
}