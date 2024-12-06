## Fabric Requires:
Minecraft [${minecraft_version_min}, ${minecraft_version_max})
Fabric Loader ${loader_version_min} or above
Fabric API ${fabric_version_min} or above
Cloth Config ${cloth_config_version_min} or above
Mod menu ${mod_menu_version} (optional)

Fabric Shield Lib is not available for 1.21.2 and 1.21.3, so MobZ has to use MixinExtra's less invasive ModifyExpressionValue to implement the shield. ModifyExpressionValue supports chaining, other mods can still targets the same injection point, the order doesn't matter.