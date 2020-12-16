package dev.camscorner.camsarmoury.core.mixin;

import dev.camscorner.camsarmoury.common.items.armour.OceanCrownItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin
{
	@Inject(method = "isTouchingWaterOrRain", at = @At("HEAD"), cancellable = true)
	public void isTouchingWaterOrRain(CallbackInfoReturnable info)
	{
		if(((Object) this) instanceof LivingEntity)
		{
			LivingEntity entity = (LivingEntity) (Object) this;

			if(entity.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof OceanCrownItem)
				info.setReturnValue(true);
		}
	}

	@Inject(method = "isWet", at = @At("HEAD"), cancellable = true)
	public void isWet(CallbackInfoReturnable info)
	{
		if(((Object) this) instanceof LivingEntity)
		{
			LivingEntity entity = (LivingEntity) (Object) this;

			if(entity.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof OceanCrownItem)
				info.setReturnValue(true);
		}
	}
}
