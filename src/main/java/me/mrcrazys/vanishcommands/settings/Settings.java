package me.mrcrazys.vanishcommands.settings;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.fo.settings.SimpleSettings;

import java.util.List;

/**
 * Stores settings for the plugin.
 */
@SuppressWarnings("unused")
public final class Settings extends SimpleSettings {

    /**
     * Returns the current {@code settings.yml} version.
     *
     * @return the current {@code settings.yml} version.
     */
    @Override
    protected int getConfigVersion() {
        return 1;
    }

    /**
     * Stores settings for vanish.
     */
    public static final class Vanish {

        /**
         * Should the vanish commands be performed?
         */
        @Nullable
        public static Boolean enabled;

        /**
         * The commands to perform when a player vanishes.
         */
        @Nullable
        public static List<@NotNull String> commands;

        /**
         * Initializes the vanish settings.
         */
        private static void init() {
            setPathPrefix("Vanish");

            enabled = getBoolean("Enabled");
            commands = getStringList("Commands");
        }
    }

    /**
     * Stores settings for unvanish.
     */
    public static final class Unvanish {

        /**
         * Should the unvanish commands be performed?
         */
        @Nullable
        public static Boolean enabled;

        /**
         * The commands to perform when a player unvanishes.
         */
        @Nullable
        public static List<@NotNull String> commands;

        /**
         * Initializes the unvanish settings.
         */
        private static void init() {
            setPathPrefix("Unvanish");

            enabled = getBoolean("Enabled");
            commands = getStringList("Commands");
        }
    }
}
