package me.mrcrazys.vanishcommands.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.DebugCommand;
import org.mineacademy.fo.command.ReloadCommand;
import org.mineacademy.fo.command.SimpleCommandGroup;

/**
 * The main command for VanishCommands.
 */
@AutoRegister
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class VanishCommandsCommandGroup extends SimpleCommandGroup {

    /**
     * The instance of this command group.
     */
    @NotNull
    private static final VanishCommandsCommandGroup INSTANCE = new VanishCommandsCommandGroup();

    @Override
    protected void registerSubcommands() {
        this.registerSubcommand(new DebugCommand("vanishcommands.command.debug"));
        this.registerSubcommand(new ReloadCommand("vanishcommands.command.reload"));
    }

    /**
     * Returns the credits.
     *
     * @return the credits.
     */
    @Nullable
    @Override
    protected String getCredits() {
        return null;
    }

    /**
     * Returns the instance of this command group.
     *
     * @return the instance of this command group.
     */
    @NotNull
    private static VanishCommandsCommandGroup getInstance() {
        return INSTANCE;
    }
}