package io.github.maple8192.economics;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class Economics implements EconCore {
    private static class Holder {
        private static final Economics INSTANCE = new Economics();
        private Holder() {}
    }

    private Economics() {}

    private EconDatabase database = null;
    private final List<EconExtension> extensions = new LinkedList<>();

    public static void setDatabase(EconDatabase database) {
        if (Holder.INSTANCE.database != null) {
            throw new IllegalStateException("Database already set");
        }
        Holder.INSTANCE.database = database;
    }

    public static EconCore addExtension(EconExtension extension) {
        Holder.INSTANCE.extensions.add(extension);
        return Holder.INSTANCE;
    }

    static Optional<String> getDatabaseName() {
        return Optional.ofNullable(Holder.INSTANCE.database).map(EconExtension::getName);
    }

    static List<String> getExtensionNames() {
        return Holder.INSTANCE.extensions.stream().map(EconExtension::getName).toList();
    }

    @Override
    public @NotNull Optional<List<UUID>> getKeys() {
        if (database == null) {
            return Optional.empty();
        }
        return Optional.of(database.getKeys());
    }

    @Override
    public boolean set(UUID uuid, BigDecimal value) {
        if (database == null) {
            return false;
        }
        database.set(uuid, value);
        return true;
    }

    @Override
    public @NotNull Optional<BigDecimal> get(UUID uuid) {
        if (database == null) {
            return Optional.empty();
        }
        return database.get(uuid);
    }
}
