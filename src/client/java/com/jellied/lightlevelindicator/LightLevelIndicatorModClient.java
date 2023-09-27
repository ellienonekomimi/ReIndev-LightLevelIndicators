package com.jellied.lightlevelindicator;

import com.fox2code.foxloader.loader.ClientMod;
import com.fox2code.foxloader.registry.CommandCompat;

public class LightLevelIndicatorModClient extends LightLevelIndicatorMod implements ClientMod {
    public void onTick() {
        LightLevelIndicatorHandler.update();
    }

    public void onInit() {
        CommandCompat.registerClientCommand(new ToggleCommand("toggleLightIndicators"));
    }
}
