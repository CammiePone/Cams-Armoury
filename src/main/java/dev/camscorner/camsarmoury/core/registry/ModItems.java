package dev.camscorner.camsarmoury.core.registry;

import dev.camscorner.camsarmoury.CamsArmoury;
import dev.camscorner.camsarmoury.common.items.ArquebusItem;
import dev.camscorner.camsarmoury.common.items.armour.OceanCrownItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;

public class ModItems
{
	//----Item Map----//
	public static final LinkedHashMap<Item, Identifier> ITEMS = new LinkedHashMap<>();

	//-----Items-----//
	public static final Item OCEAN_CROWN = create("ocean_crown", new OceanCrownItem(ArmorMaterials.TURTLE, EquipmentSlot.HEAD, new Item.Settings().group(CamsArmoury.ITEM_GROUP)));
//	public static final Item EVOKER_BRACELET = create("evoker_bracelet", new EvokerBraceletItem(new Item.Settings().group(CamsArmoury.ITEM_GROUP).maxCount(1)));
	public static final Item ARQUEBUS = create("arquebus", new ArquebusItem(new Item.Settings().group(CamsArmoury.ITEM_GROUP).maxCount(1)));

	//-----Registry-----//
	public static void register()
	{
		ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
	}

	private static <T extends Item> T create(String name, T item)
	{
		ITEMS.put(item, new Identifier(CamsArmoury.MOD_ID, name));
		return item;
	}
}
