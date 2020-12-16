package dev.camscorner.camsarmoury.core.mixin;

import dev.camscorner.camsarmoury.common.items.armour.OceanCrownItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TridentItem.class)
public abstract class TridentItemMixin extends Item
{
	public TridentItemMixin(Settings settings) { super(settings); }

	@Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isTouchingWaterOrRain()Z"))
	public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo info)
	{
		if(user.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof OceanCrownItem)
			user.getEquippedStack(EquipmentSlot.HEAD).damage(1, user, ((p) -> p.sendEquipmentBreakStatus(EquipmentSlot.HEAD)));
	}
}
