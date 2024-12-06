# MobZ (Reborn)
A port of [Globox1997's MobZ](https://www.curseforge.com/minecraft/mc-mods/mobz), for Minecraft versions (Supports both Forge and Fabric):
* 1.21.2, 1.21.3
* 1.21, 1.21.1
* 1.20.5, 1.20.6
* 1.20.2, 1.20.3, 1.20.4
* 1.20, 1.20.1
* 1.19.2
* 1.18.2
* 1.17.1
* 1.16.5

__This branch is for Minecraft [1.21.2, 1.21.3).__

1.16.5 only has Forge version, for 1.16.5 Fabric version, check out [the original MobZ](https://github.com/Globox1997/MobZ).

This mod adds vanilla style mobs, blocks and items.

## Dependencies
### Forge Version
Since 3.2.0 for Minecraft 1.17.1 (including the patched 3.0.2 for Minecraft 1.16.5), the Forge version also depends on [Cloth Config API](https://www.curseforge.com/minecraft/mc-mods/cloth-config-forge).

### Neoforge Version
Neoforge support is introduced since Minecraft 1.20.4 and requires [Cloth Config API](https://www.curseforge.com/minecraft/mc-mods/cloth-config).

### Fabric Version
Since 3.3.0 for Minecraft 1.18.1, the Fabric version starts to depend on [Fabric Shield Lib](https://www.curseforge.com/minecraft/mc-mods/fabric-shield-lib). Using this library alleviates the compatibility issue with other mods that add shields.

You need to install Fabric Loader, [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api),
[Cloth Config API](https://www.curseforge.com/minecraft/mc-mods/cloth-config) and
[Fabric Shield Lib](https://www.curseforge.com/minecraft/mc-mods/fabric-shield-lib) together with this mod.
[Mod menu](https://www.curseforge.com/minecraft/mc-mods/modmenu) is highly recommended.
For version requirements, check out the release page of this repo.

## For developers
Since 1.17.1, MobZ (Reborn) for Fabric and Forge share the common code base as much as possible (in the `src` folder). The entire code base uses Minecraft official mapping. The platform-specific code and resources are in `forge` and `fabric` folder, they provide a thin layer of abstraction and hide the difference between Forge and Fabric.

To modify and debug the code, first import either "forge" or "fabric" folder as a Gradle project into the Eclipse IDE, and then run the gradle task `genEclipseRuns`. Intellij IDEA is also supported.

To debug the Fabric project in Eclipse, you need to copy `fabric\fabric_loader_dependencies.json` into `fabric\run\config` folder, otherwise Fabric loader will complain about the dependencies.

### Build artifact
Windows users need to replace `./` and `../` with `.\` and `..\` respectively.
```
git clone https://github.com/rikka0w0/MobZReborn.git
cd MobZReborn/$LOADER_NAME
./gradlew clean prepareInfo runData
./gradlew build
```
where $LOADER_NAME must be one of:
1. neofoge
2. fabric
3. forge

### Specify JRE path:
1. Minecraft 1.16.5 and below work best with Java 8.
2. Minecraft 1.17 and 1.17.1 requires Java 16.
3. Minecraft 1.18 to 1.20.4 (inclusive) requires Java 17.
4. Starting from 1.20.5, Minecraft requires Java 21.
```
./gradlew -Dorg.gradle.java.home=/path_to_jdk_directory <commands>
```

__Special thanks to Globox1997 who originally made this fantastic mod__
__感谢mcmod.cn的天涯提供的中文本地化__
