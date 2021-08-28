# MobZ (Reborn)
A port of Globox1997's MobZ, for Minecraft 1.16.5 and 1.7.1, Forge and Fabric.

1.16.5 only has the Forge version, for 1.16.5 Fabric version, check out [the original MobZ](https://github.com/Globox1997/MobZ).

This mod adds vanilla style mobs, blocks and items.

## Dependencies
### Forge Version
You only need to install Forge and then install this mod. [Configured](https://www.curseforge.com/minecraft/mc-mods/configured)
is highly recommended, it enables graphically configuration.

### Fabric Version
You need to install Fabric Loader, [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api),
[Cloth Config API](https://www.curseforge.com/minecraft/mc-mods/cloth-config) and then install this mod.
[Mod menu](https://www.curseforge.com/minecraft/mc-mods/modmenu) is highly recommended.
For version requirements, check out the release page of this repo.

## For developers
To modify and debug the code, first import the repo as a Gradle project in Eclipse IDE, and then run the gradle task `genEclipseRuns`.
Most design work should happen in Fabric using yarn mapping. However, after remapping (see the following section), the forge folder can be imported to IDE as well.
If the forge part cannot be imported into Eclipse, please copy `gradlew`, `gradlew.bat` and `gradle` folder into `forge` folder and retry.

Windows users need to replace `./` and `../` with `.\` and `..\` respectively.

Since 1.17.1, MobZ (Reborn) for Fabric and Forge share common code as much as possible. The entire code base uses Minecraft official mapping.

Intellij IDEA is also supported.

### Compile Fabric artifact
```
git clone https://github.com/rikka0w0/MobZReborn.git
cd MobZReborn/fabric
./gradlew build
```

### Compile Forge artifact
```
git clone https://github.com/rikka0w0/MobZReborn.git
cd MobZReborn/forge
./gradlew build
```

### To specify JRE path (Since 1.17.1, Minecraft requires Java 16 or above):
```
./gradlew -Dorg.gradle.java.home=/path_to_jdk_directory <commands>
```
