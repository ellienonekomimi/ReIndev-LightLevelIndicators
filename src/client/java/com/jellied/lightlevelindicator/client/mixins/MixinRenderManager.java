package com.jellied.lightlevelindicator.client.mixins;

import com.jellied.lightlevelindicator.entity.EntityLightLevelIndicator;
import com.jellied.lightlevelindicator.renderer.entity.RenderLightLevelIndicator;
import net.minecraft.src.client.renderer.entity.Render;
import net.minecraft.src.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RenderManager.class)
public class MixinRenderManager {
    @Shadow private Map<Class<?>, Render> entityRenderMap;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void onInit(CallbackInfo ci) {
        Render indicatorRenderer = new RenderLightLevelIndicator();
        this.entityRenderMap.put(EntityLightLevelIndicator.class, indicatorRenderer);
        indicatorRenderer.setRenderManager((RenderManager) (Object) this);
    }
}
