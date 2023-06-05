package jimenezli.neurosama.handler;

import jimenezli.neurosama.NeurosamaMod;
import jimenezli.neurosama.item.HeartItem;
import jimenezli.neurosama.item.NeurosamaGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemHandler {
    public static final ItemGroup creativeTab = new NeurosamaGroup();
    public static Item.Properties defaultBuilder() {
        return new Item.Properties().tab(creativeTab);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NeurosamaMod.ID);

    public static final RegistryObject<Item> HEART = ITEMS.register("heart", HeartItem::new);
    public static final RegistryObject<Item> IRONMILK = ITEMS.register("ironmilk", () -> new Item(defaultBuilder()));
}
