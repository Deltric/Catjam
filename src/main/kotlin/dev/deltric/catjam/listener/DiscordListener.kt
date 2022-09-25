package dev.deltric.catjam.listener

import dev.deltric.catjam.Catjam
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

/**
 * Listens for Discord related events
 */
class DiscordListener(
    private val catjam: Catjam
) : ListenerAdapter() {

    /**
     * On slash command run
     */
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val command = this.catjam.getCommand(event.commandIdLong)
        command?.onExecute(event)
    }

}