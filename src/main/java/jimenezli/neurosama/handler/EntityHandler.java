package jimenezli.neurosama.handler;

import jimenezli.neurosama.NeurosamaMod;
import jimenezli.neurosama.client.renderer.entity.NeurosamaRenderer;
import jimenezli.neurosama.entity.NeurosamaEntity;
import jimenezli.neurosama.util.EntityNames;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = NeurosamaMod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityHandler {
    private static final List<EntityType<?>> ALL = new ArrayList<>();
    public static final EntityType<NeurosamaEntity> neurosama = build(EntityNames.NEUROSAMA, makeCastedBuilder(NeurosamaEntity.class, NeurosamaEntity::new, EntityClassification.CREATURE));

    @SuppressWarnings("unchecked")
    private static <E extends Entity> EntityType<E> build(ResourceLocation id, EntityType.Builder<E> builder) {
        EntityType<E> ret = (EntityType<E>) builder.build(id.toString()).setRegistryName(id);
        ALL.add(ret);
        return ret;
    }

    private static <E extends Entity> EntityType.Builder<E> makeCastedBuilder(@SuppressWarnings("unused") Class<E> cast, EntityType.IFactory<E> factory, EntityClassification classification) {
        return makeBuilder(factory, classification);
    }

    private static <E extends Entity> EntityType.Builder<E> makeBuilder(EntityType.IFactory<E> factory, EntityClassification classification) {
        return EntityType.Builder.of(factory, classification).
                sized(0.6F, 1.8F).
                setUpdateInterval(3).
                setShouldReceiveVelocityUpdates(true);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> evt) {
        evt.getRegistry().registerAll(ALL.toArray(new EntityType<?>[0]));
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(neurosama, NeurosamaEntity.createMobAttributes().build());
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerEntityRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(neurosama, NeurosamaRenderer::new);
    }
}
