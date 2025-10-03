
package com.example.feurshadowelytra.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class TexturePicker {
  public static ResourceLocation pick(ItemStack stack) {
    String name = stack.hasCustomHoverName() ? stack.getHoverName().getString() : null;
    if (name != null) {
      String lower = name.toLowerCase();
      if (lower.contains("[wing:")) {
        // direct filename within this mod's assets (elytra/wing14.png)
        int s = lower.indexOf("[wing:")+6; int e = lower.indexOf(']', s);
        if (e > s) {
          String key = lower.substring(s, e).trim();
          if (!key.endsWith(".png")) key += ".png";
          return new ResourceLocation("feur_shadow_elytra", "textures/elytra/"+key);
        }
      }
      // [Wing001]..
      int s = lower.indexOf("[wing"); int e = lower.indexOf(']', s);
      if (s >= 0 && e > s) {
        String digits = lower.substring(s+5, e).replaceAll("[^0-9]", "");
        try {
          int idx = Integer.parseInt(digits);
          return new ResourceLocation("feur_shadow_elytra", String.format("textures/elytra/wing_%03d.png", idx));
        } catch (Exception ignored) {}
      }
      if (lower.contains("angel")) return new ResourceLocation("feur_shadow_elytra", "textures/elytra/wing_014.png");
      if (lower.contains("devil") || lower.contains("demon")) return new ResourceLocation("feur_shadow_elytra", "textures/elytra/wing_177.png");
    }
    return new ResourceLocation("feur_shadow_elytra", "textures/elytra/wing_001.png");
  }
}
