package dev.deltric.catjam.command

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

/**
 * Command for playing music
 */
class PlayCommand : Command {

    override fun getName(): String {
        return "play"
    }

    override fun getDescription(): String {
        return "Play a song on the bot."
    }

    override fun getVersion(): Int {
        return 1
    }

    override fun build(root: SlashCommandData): SlashCommandData {
        return root.addOption(
            OptionType.STRING,
            "url",
            "URL to the song you want to play."
        )
    }

    override fun onExecute(event: SlashCommandInteractionEvent) {

    }

}