package net.fedustria.northcore;

import lombok.Getter;
import net.fedustria.northcore.api.player.IAPIPlayer;

import java.util.List;

@Getter
public abstract class BaseCore {


	public BaseCore() {

	}

	public abstract IAPIPlayer getPlayer();

	public abstract List<IAPIPlayer> getPlayers();
}
