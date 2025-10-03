
package com.example.feurshadowelytra.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.example.feurshadowelytra.RegistryInit;
import com.example.feurshadowelytra.FeurShadowElytra;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientLayer {
  @SubscribeEvent
  public static void addLayers(EntityRenderersEvent.AddLayers e) {
    EntityRendererProvider.Context ctx = e.getContext();
    try { e.getSkin("default").addLayer(new WingLayer(e.getSkin("default"), ctx.getModelSet())); } catch (Throwable ignored) {}
    try { e.getSkin("slim").addLayer(new WingLayer(e.getSkin("slim"), ctx.getModelSet())); } catch (Throwable ignored) {}
  }

  static class WingLayer extends RenderLayer<AbstractClientPlayer, net.minecraft.client.model.PlayerModel<AbstractClientPlayer>> {
    private final ElytraModel<AbstractClientPlayer> model;
    public WingLayer(PlayerRenderer parent, EntityModelSet set) {
      super(parent);
      this.model = new ElytraModel<>(set.bakeLayer(net.minecraft.client.model.geom.ModelLayers.ELYTRA));
    }
    @Override
    public void render(PoseStack pose, MultiBufferSource buf, int light, AbstractClientPlayer p,
                       float a, float b, float c, float d, float e, float f) {
      ItemStack chest = p.getItemBySlot(EquipmentSlot.CHEST);
      if (chest.isEmpty() || chest.getItem() != RegistryInit.FEUR_WINGS_ELYTRA.get()) return;
      ResourceLocation tex = TexturePicker.pick(chest);
      var v = buf.getBuffer(net.minecraft.client.renderer.RenderType.entityCutoutNoCull(tex));
      this.getParentModel().copyPropertiesTo(this.model);
      this.model.setupAnim(p, a, b, d, e, f);
      pose.pushPose();
      this.model.renderToBuffer(pose, v, light, net.minecraft.client.renderer.LightTexture.FULL_BRIGHT, 1,1,1,1);
      pose.popPose();
    }
  }
}
