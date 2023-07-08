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
                .orElse(Collections.emptyList())
                .stream()
                .map(command -> CMI.getInstance().getLM().updateSnd(snd, command))
                .map(command -> Variables.replace(command, player))
                .collect(Collectors.toList());

        // Process vanish commands from settings.yml.
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
