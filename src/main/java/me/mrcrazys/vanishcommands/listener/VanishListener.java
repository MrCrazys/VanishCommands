package me.mrcrazys.vanishcommands.listener;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.Snd;
import com.Zrips.CMI.events.CMIPlayerVanishEvent;
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
 * A listener for handling a player vanish event.
 */
public final class VanishListener extends SimpleListener<@NotNull CMIPlayerVanishEvent> {

    /**
     * The instance of this listener.
     */
    @NotNull
    private static final VanishListener INSTANCE = new VanishListener();

    /**
     * Creates a new vanish listener.
     */
    private VanishListener() {
        super(CMIPlayerVanishEvent.class, EventPriority.NORMAL, true);
    }

    /**
     * Called when a player vanishes in CMI.
     *
     * @param event the {@link CMIPlayerVanishEvent} that was fired.
     */
    @Override
    protected void execute(@NotNull final CMIPlayerVanishEvent event) {
        final Player player = event.getPlayer();

        final Snd snd = new Snd()
                .setSender(player)
                .setTarget(player);

        final List<String> commands = Optional.ofNullable(Settings.Vanish.commands)
                // Return an empty list if the vanish commands don't exist.
                .orElse(Collections.emptyList())

                // Turn the commands into a stream.
                .stream()

                // Replace variables such as [playerName] in the command.
                .map(command -> CMI.getInstance().getLM().updateSnd(snd, command))

                // Replace hardcoded variables such as {location} in the command.
                .map(command -> Variables.replace(command, player))

                // Collect the updated values into a list.
                .collect(Collectors.toList());

        // Process the vanish commands.
        if (!commands.isEmpty())
            CMI.getInstance().getSpecializedCommandManager().processCmds("VanishCommands-Vanish", commands, player);
    }

    /**
     * Returns the instance of this listener.
     *
     * @return the instance of this listener.
     */
    @NotNull
    public static VanishListener getInstance() {
        return INSTANCE;
    }
}
