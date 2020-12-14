package dev.camscorner.camsarmoury.core.registry;

import dev.camscorner.camsarmoury.CamsArmoury;
import dev.camscorner.camsarmoury.common.items.SpearItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;

public class ModItems
{
	//----Item Map----//
	public static final LinkedHashMap<Item, Identifier> ITEMS = new LinkedHashMap<>();

	//-----Items-----//
	public static final Item SPEAR = create("spear", new SpearItem(new Item.Settings().group(CamsArmoury.ITEM_GROUP).maxCount(1)));

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
