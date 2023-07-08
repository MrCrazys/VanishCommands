package me.mrcrazys.vanishcommands.listener;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.Snd;
import com.Zrips.CMI.events.CMIPlayerUnVanishEvent;
import me.mrcrazys.vanishcommands.settings.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.fo.event.SimpleListener;
import org.mineacademy.fo.model.Variables;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A listener for handling a player unvanish event.
 */
public final class UnvanishListener extends SimpleListener<@NotNull CMIPlayerUnVanishEvent> {

    /**
     * The instance of this listener.
     */
    @NotNull
    private static final UnvanishListener INSTANCE = new UnvanishListener();

    /**
     * Creates a new unvanish listener.
     */
    private UnvanishListener() {
        super(CMIPlayerUnVanishEvent.class, EventPriority.NORMAL, true);
    }

    /**
     * Called when a player unvanishes in CMI.
     *
     * @param event the {@link CMIPlayerUnVanishEvent} that was fired.
     */
    @Override
    protected void execute(@NotNull final CMIPlayerUnVanishEvent event) {
        final Player player = event.getPlayer();

        final Snd snd = new Snd()
                .setSender(player)
                .setTarget(player);

        final List<String> commands = Optional.ofNullable(Settings.Unvanish.commands)
                // Return an empty list if the unvanish commands don't exist.
                .orElse(Collections.emptyList())

                // Turn the commands into a stream.
                .stream()

                // Replace variables such as [playerName] in the command.
                .map(command -> CMI.getInstance().getLM().updateSnd(snd, command))

                // Replace hardcoded variables like {location} in the command.
                .map(command -> Variables.replace(command, player))

                // Collect the updated values into a list.
                .collect(Collectors.toList());

        // Process the unvanish commands.
        if (!commands.isEmpty())
            CMI.getInstance().getSpecializedCommandManager().processCmds("VanishCommands-Unvanish", commands, player);
    }

    /**
     * Returns the instance of this listener.
     *
     * @return the instance of this listener.
     */
    @NotNull
    public static UnvanishListener getInstance() {
        return INSTANCE;
    }
}
