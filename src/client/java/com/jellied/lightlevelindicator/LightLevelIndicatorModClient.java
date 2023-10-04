package com.jellied.lightlevelindicator;

import com.fox2code.foxloader.loader.ClientMod;
import com.fox2code.foxloader.registry.CommandCompat;
import net.minecraft.src.client.KeyBinding;
import com.fox2code.foxloader.client.KeyBindingAPI;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class LightLevelIndicatorModClient extends LightLevelIndicatorMod implements ClientMod {
    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final KeyBinding toggleLightLevelIndicatorKeyBinding = new KeyBinding("Toggle Light Indicators", Keyboard.KEY_L);
    private static boolean firstPress = true;

    public void onTick() {
        handleKeybind();
        LightLevelIndicatorHandler.update();
    }

    public void onInit() {
        KeyBindingAPI.registerKeyBinding(toggleLightLevelIndicatorKeyBinding);
        CommandCompat.registerClientCommand(new ToggleCommand("toggleLightIndicators"));
    }

    private void handleKeybind() {
        if (minecraft.currentScreen != null) {
            firstPress = true;
            return;
        }

        if (Keyboard.isKeyDown(toggleLightLevelIndicatorKeyBinding.keyCode) && firstPress) {
            LightLevelIndicatorHandler.toggleIsEnabled();
            Minecraft.getInstance().thePlayer.displayChatMessage("Light Level Indicators: " + (LightLevelIndicatorHandler.getIsEnabled() ? "Enabled" : "Disabled"));
            firstPress = false;
        } else if (!Keyboard.isKeyDown(toggleLightLevelIndicatorKeyBinding.keyCode)) {
            firstPress = true;
        }
    }
}
