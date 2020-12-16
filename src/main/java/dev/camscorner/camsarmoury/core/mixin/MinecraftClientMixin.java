package dev.camscorner.camsarmoury.core.mixin;

import dev.camscorner.camsarmoury.common.items.ArquebusItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin
{
	@Shadow
	public ClientPlayerEntity player;

	@Inject(method = "doAttack", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/util/hit/HitResult;getType()Lnet/minecraft/util/hit/HitResult$Type;", ordinal = 0), cancellable = true)
	public void doAttack(CallbackInfo info)
	{
		ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);

		if(stack.getItem() instanceof ArquebusItem)
		{
			stack.damage(1, player, ((p) -> p.sendToolBreakStatus(Hand.MAIN_HAND)));
		}
	}

	@Inject(method = "doAttack", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/client/network/ClientPlayerEntity;swingHand(Lnet/minecraft/util/Hand;)V", ordinal = 0), cancellable = true)
	public void cancelSwingAnim(CallbackInfo info)
	{
		if(player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof ArquebusItem)
			info.cancel();
	}
}
