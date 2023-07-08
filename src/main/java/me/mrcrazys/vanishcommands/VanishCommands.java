package me.mrcrazys.vanishcommands;

import me.mrcrazys.vanishcommands.listener.UnvanishListener;
import me.mrcrazys.vanishcommands.listener.VanishListener;
import me.mrcrazys.vanishcommands.settings.Settings;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.model.HookManager;
import org.mineacademy.fo.plugin.SimplePlugin;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * The main class for VanishCommands.
 */
public final class VanishCommands extends SimplePlugin {

    /**
     * Called when the plugin is started.
     */
    @Override
    protected void onPluginStart() {
    }

    /**
     * Called when the plugin is started or reloaded.
     */
    @Override
    protected void onReloadablesStart() {
        // Disable the plugin if CMI isn't loaded.
        Valid.checkBoolean(HookManager.isCMILoaded(), "VanishCommands requires CMI to run. Please purchase and install it from: https://www.spigotmc.org/resources/3742.");

        // Only register events if the plugin is enabled.
        Optional.of(this.isEnabled())
                .filter(Boolean::booleanValue)

                // Create a stream with entries that have the state
                // and corresponding listener instance.
                .ifPresent(enabled -> Stream.of(
                                new SimpleEntry<>(Settings.Unvanish.enabled, UnvanishListener.getInstance()),
                                new SimpleEntry<>(Settings.Vanish.enabled, VanishListener.getInstance())
                        )

                        // Filter on the entries where the state is
                        // not null and true. Defaults to false for
                        // null values.
                        .filter(entry -> Optional.ofNullable(entry.getKey()).orElse(false))

                        // Map each entry to its value.
                        .map(Entry::getValue)

                        // Filter out null values.
                        .filter(Objects::nonNull)

                        // Register the events.
                        .forEach(this::registerEvents));
    }

    /* ------------------------------------------------------------------------------- */
    /* Static */
    /* ------------------------------------------------------------------------------- */

    /**
     * Returns the instance of {@code VanishCommands}.
     *
     * @return the instance of {@code VanishCommands}.
     */
    @NotNull
    public static VanishCommands getInstance() {
        return (VanishCommands) SimplePlugin.getInstance();
    }
}
