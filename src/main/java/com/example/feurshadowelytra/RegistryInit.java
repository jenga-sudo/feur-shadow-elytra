package com.example.feurshadowelytra;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryInit {
  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, FeurShadowElytra.MODID);

  public static final RegistryObject<Item> FEUR_WINGS_ELYTRA = ITEMS.register(
      "feur_wings_elytra",
      () -> new ShadowElytraItem(new Item.Properties().stacksTo(1).durability(432))
  );
}
