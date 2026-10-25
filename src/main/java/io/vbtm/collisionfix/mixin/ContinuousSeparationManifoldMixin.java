package io.vbtm.collisionfix.mixin;

import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Create 6.0.10's {@code ContinuousSeparationManifold} nulls {@code axis} and {@code normalAxis} in {@code reset()}
 * and never sets them in the constructor; {@code ContraptionCollider} then reads {@code mf.axis.x} for a manifold that
 * found no separating axis and the server crashes. 6.0.9 kept both at {@code Vec3.ZERO}. Restore that.
 */
@Mixin(targets = "com.simibubi.create.foundation.collision.ContinuousOBBCollider$ContinuousSeparationManifold", remap = false)
public abstract class ContinuousSeparationManifoldMixin {
    @Shadow Vec3 axis;
    @Shadow Vec3 normalAxis;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void vbtm_collisionfix$initAxes(CallbackInfo ci) {
        if (axis == null) axis = Vec3.ZERO;
        if (normalAxis == null) normalAxis = Vec3.ZERO;
    }

    @Inject(method = "reset", at = @At("RETURN"))
    private void vbtm_collisionfix$resetAxes(CallbackInfo ci) {
        if (axis == null) axis = Vec3.ZERO;
        if (normalAxis == null) normalAxis = Vec3.ZERO;
    }
}
