package io.github.maple8192.economics;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EconDatabase extends EconExtension {
    void set(UUID uuid, BigDecimal value);
    @NotNull Optional<BigDecimal> get(UUID uuid);
    @NotNull List<UUID> getKeys();
}
