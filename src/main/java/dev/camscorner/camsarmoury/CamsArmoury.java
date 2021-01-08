package dev.camscorner.camsarmoury;

import dev.camscorner.camsarmoury.core.network.client.SquaredVelocityMessage;
import dev.camscorner.camsarmoury.core.registry.ModEnchants;
import dev.camscorner.camsarmoury.core.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class CamsArmoury implements ModInitializer
{
	public static final String MOD_ID = "camsarmoury";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "general"), () -> new ItemStack(ModItems.OCEAN_CROWN));

	@Override
	public void onInitialize()
	{
		ServerSidePacketRegistry.INSTANCE.register(SquaredVelocityMessage.ID, SquaredVelocityMessage::handle);
		ModItems.register();
		ModEnchants.register();
	}
}
