package dev.deltric.catjam.command

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

/**
 * Template for a Catjam Discord slash command
 */
interface Command {

    /**
     * Gets the command name
     * @return command name
     */
    fun getName(): String

    /**
     * Gets the command description
     * @return command description
     */
    fun getDescription(): String

    /**
     * Gets the command version
     * @return command version
     */
    fun getVersion(): Int

    /**
     * Builds the command from the root
     * @param root - root command
     * @return command with options added or root
     */
    fun build(root: SlashCommandData): SlashCommandData {
        return root
    }

    /**
     * On command executed
     * @param event - slash command event
     */
    fun onExecute(event: SlashCommandInteractionEvent)

}