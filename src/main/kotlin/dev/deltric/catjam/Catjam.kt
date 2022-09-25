package dev.deltric.catjam

import com.google.gson.Gson
import dev.deltric.catjam.command.Command
import dev.deltric.catjam.command.PlayCommand
import dev.deltric.catjam.command.RegisteredCommand
import dev.deltric.catjam.config.CatjamConfig
import dev.deltric.catjam.listener.DiscordListener
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.slf4j.LoggerFactory

/**
 * Main class for the Catjam bot
 */
class Catjam(
    private val jda: JDA,
    private val config: CatjamConfig,
    private val gson: Gson
) {
    private val logger = LoggerFactory.getLogger("Catjam")
    private val commandMap = mutableMapOf<Long, Command>()

    init {
        this.registerCommands()

        this.jda.addEventListener(DiscordListener(this))
    }

    /**
     * Registers all bot commands
     */
    private fun registerCommands() {
        this.registerCommand(PlayCommand())
    }

    /**
     * Registers a new bot command
     * @param command - command to register
     */
    private fun registerCommand(command: Command) {
        val entry = this.config.commands.toList()
            .find { entry -> entry.second.name == command.getName() }

        // If the command is locally cached, check if the version is already up-to-date.
        if (entry != null && entry.second.version == command.getVersion()) {
            this.commandMap[entry.first] = command
            this.logger.info("Loaded cached command: ${command.getName()}")
            return
        }

        val commandRoot = Commands.slash(command.getName(), command.getDescription())
        val registeredCommand = this.jda.upsertCommand(command.build(commandRoot)).complete()

        // After command is registered, update the local cache.
        if (registeredCommand != null) {
            this.commandMap[registeredCommand.idLong] = command
            this.config.commands[registeredCommand.idLong] = RegisteredCommand(command.getName(), command.getVersion())
            this.config.save(this.gson)
            this.logger.info("Updated/Registered command: ${command.getName()}")
        }
    }

    /**
     * Looks up a catjam command by its id
     */
    fun getCommand(commandId: Long): Command? {
        return this.commandMap[commandId]
    }
}