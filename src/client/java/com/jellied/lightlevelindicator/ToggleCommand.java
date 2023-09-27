package com.jellied.lightlevelindicator;

import com.fox2code.foxloader.network.NetworkPlayer;
import com.fox2code.foxloader.registry.CommandCompat;

public class ToggleCommand extends CommandCompat {
    public ToggleCommand(String name) {
        super(name);
    }

    public void onExecute(String[] args, NetworkPlayer user) {
        LightLevelIndicatorHandler.toggleIsEnabled();
    }
}
