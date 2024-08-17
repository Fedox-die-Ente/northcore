package net.fedustria.northcore;

import lombok.Getter;
import net.fedustria.northcore.api.player.IAPIPlayer;
import net.fedustria.northcore.config.NCConfig;
import net.fedustria.northcore.config.data.DatabaseConfig;
import net.fedustria.northcore.database.DBClient;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static net.fedustria.northcore.common.Constants.DB_CONFIG_FILE;
import static net.fedustria.northcore.common.Constants.WORKING_DIRECTORY;

@Getter
public abstract class BaseCore {
    protected final DBClient dbClient;


    public BaseCore() {
        try {
            final DatabaseConfig dbConfig = NCConfig.loadConfig(new File(System.getenv(WORKING_DIRECTORY), DB_CONFIG_FILE), DatabaseConfig.class);
            dbClient = new DBClient(dbConfig);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract IAPIPlayer getPlayer();

    public abstract List<IAPIPlayer> getPlayers();
}
