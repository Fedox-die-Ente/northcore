package net.fedustria.northcore.api.core;

import lombok.Getter;
import lombok.Setter;
import net.fedustria.northcore.api.player.impl.APIPlayer;
import net.fedustria.northcore.common.Constants;
import net.fedustria.northcore.config.NCConfig;
import net.fedustria.northcore.config.data.DatabaseConfig;
import net.fedustria.northcore.database.DBClient;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Getter
public abstract class BaseCore {

    protected static BaseCore instance;

    protected final DBClient dbClient;


    @Setter
    private boolean enabled = false;

    public BaseCore() {
        instance = this;
        try {
            final DatabaseConfig dbConfig = NCConfig.loadConfig(new File(System.getenv(Constants.WORKING_DIRECTORY), Constants.DB_CONFIG_FILE), DatabaseConfig.class);
            dbClient = new DBClient(dbConfig);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static BaseCore getCore() {
        return instance;
    }

    public BukkitCore getBukkit() {
        return (BukkitCore) instance;
    }

    public APIPlayer getAPIPlayer(UUID uniqueId) {
        return APIPlayer.get(uniqueId);
    }

    public abstract void sendMessage(APIPlayer apiPlayer, String message);

    public abstract boolean isOnline(UUID uniqueId);

}
