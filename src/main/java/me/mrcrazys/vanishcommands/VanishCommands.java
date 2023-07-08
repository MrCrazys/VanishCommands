package me.mrcrazys.vanishcommands;

import me.mrcrazys.vanishcommands.listener.UnvanishListener;
import me.mrcrazys.vanishcommands.listener.VanishListener;
import me.mrcrazys.vanishcommands.settings.Settings;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.model.HookManager;
import org.mineacademy.fo.plugin.SimplePlugin;

import java.util.Optional;

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
        if (this.isEnabled()) {
            // Use Optional to minimise the chance of NullPointerException.
            final Boolean vanishEnabled = Optional.ofNullable(Settings.Vanish.enabled).orElse(true);
            final Boolean unvanishEnabled = Optional.ofNullable(Settings.Unvanish.enabled).orElse(true);

            if (vanishEnabled)
                this.registerEvents(VanishListener.getInstance());

            if (unvanishEnabled)
                this.registerEvents(UnvanishListener.getInstance());
        }
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
