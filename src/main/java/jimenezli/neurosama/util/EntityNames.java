package jimenezli.neurosama.util;

import jimenezli.neurosama.NeurosamaMod;
import net.minecraft.util.ResourceLocation;

import java.util.Locale;

public class EntityNames {
    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(NeurosamaMod.ID, name.toLowerCase(Locale.ROOT));
    }

    public static final ResourceLocation NEUROSAMA = prefix("neurosama");
}
