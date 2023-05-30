package jimenezli.neurosama.client.renderer;

import jimenezli.neurosama.NeurosamaMod;
import jimenezli.neurosama.handler.EntityHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = NeurosamaMod.ID)
public class ClientSetup {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityHandler.registerEntityRenderer();
    }
}
