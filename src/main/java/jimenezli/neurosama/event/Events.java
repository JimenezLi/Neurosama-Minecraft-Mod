package jimenezli.neurosama.event;

import jimenezli.neurosama.handler.EntityHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Events {
    /**
     * Right click turtle or fox with iron block will change them into programming turtle or drawing fox.
     * I thought of computer chip item, but I have no idea where it should come from.
     */
    @SubscribeEvent
    public static void onTurtleFoxInteract(PlayerInteractEvent.EntityInteract event) {
        boolean actionSuccess = false;
        ItemStack itemStack = event.getItemStack();

        if (itemStack.getItem() == Items.IRON_BLOCK) {
            Entity entity = event.getTarget();
            if (!event.getWorld().isClientSide()) {
                if (entity.getClass() == TurtleEntity.class) {
                    ((TurtleEntity) entity).convertTo(EntityHandler.programmingTurtle, true);
                    actionSuccess = true;
                } else if (entity.getClass() == FoxEntity.class) {
                    ((FoxEntity) entity).convertTo(EntityHandler.drawingFox, true);
                    actionSuccess = true;
                }
            }
        }

        if (actionSuccess && !event.getPlayer().isCreative()) {
            itemStack.shrink(1);
        }
    }
}
