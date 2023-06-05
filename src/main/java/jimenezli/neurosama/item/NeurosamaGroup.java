package jimenezli.neurosama.item;

import jimenezli.neurosama.handler.ItemHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class NeurosamaGroup extends ItemGroup {
    public NeurosamaGroup() {
        super("neurosana_group");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemHandler.HEART.get());
    }
}
