package net.mobz.forge;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.mobz.MobZ;
import net.mobz.client.EntityRenderers;
import net.mobz.client.VanillaClientRegistry;
import net.mobz.config.MobZComposedGuiRegistryAccess;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MobZ.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrationHandler {
	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		EntityRenderers.registerAll(net.minecraft.client.renderer.entity.EntityRenderers::register);
		VanillaClientRegistry.registerItemModelProperties(ItemProperties::register);
	}

	@SubscribeEvent
	public static void onLayerDefinitionRegistration(final RegisterLayerDefinitions event) {
		EntityRenderers.registerLayerDefinitions(event::registerLayerDefinition);
	}

	public static void registerConfigGui() {
		ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class,
				() -> new ConfigScreenFactory((client, parent) -> MobZComposedGuiRegistryAccess.buildScreen(parent)));
	}
}
