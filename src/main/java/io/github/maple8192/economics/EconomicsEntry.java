package io.github.maple8192.economics;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class EconomicsEntry extends JavaPlugin {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return switch (command.getName()) {
            case "db" -> {
                Economics.getDatabaseName().ifPresentOrElse(
                        name -> sender.sendMessage("Database Name : " + name),
                        () -> sender.sendMessage("Database is not set")
                );
                yield true;
            }
            case "ex" -> {
                sender.sendMessage("Extensions:");
                Economics.getExtensionNames().forEach(name -> sender.sendMessage("- " + name));
                yield true;
            }
            default -> false;
        };
    }
}
