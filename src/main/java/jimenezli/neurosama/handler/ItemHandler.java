package jimenezli.neurosama.handler;

import jimenezli.neurosama.NeurosamaMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemHandler {
    public static ItemGroup creativeTab = ItemGroup.TAB_MISC;
    public static Item.Properties defaultBuilder() {
        return new Item.Properties().tab(creativeTab);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NeurosamaMod.ID);

    public static final RegistryObject<Item> heart = ITEMS.register("heart", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ironmilk = ITEMS.register("ironmilk", () -> new Item(defaultBuilder()));
}
