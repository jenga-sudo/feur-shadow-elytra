
package com.example.feurshadowelytra;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
  static { RegistryInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus()); }
}
