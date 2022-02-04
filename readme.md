# MobZ (Reborn)
Forge port of [Globox1997's MobZ](https://www.curseforge.com/minecraft/mc-mods/mobz), for Minecraft 1.16.5.

For 1.16.5 Fabric version, check out [the original MobZ](https://github.com/Globox1997/MobZ).

This mod adds vanilla style mobs, blocks and items.

__The 1.16.5 branch will no longer be maintained.__ The last commit fixes the incompatibility with other mods that uses Cloth Config API and corrects the shield animation.

## Dependencies
Minecraft 1.16.2 to 1.16.5 on Java 8

Minecraft Forge 33

[Cloth Config API 4.13.49](https://www.curseforge.com/minecraft/mc-mods/cloth-config-forge)

## For developers
This branch is for 1.16.5 Forge only, the Fabric folder is not used.

To modify and debug the code, first import the git repo as a Gradle project into the Eclipse IDE, and then run the gradle task `genEclipseRuns`. Intellij IDEA is also supported.

### Compile Forge artifact
Windows users need to replace `./` and `../` with `.\` and `..\` respectively.
```
git clone https://github.com/rikka0w0/MobZReborn.git
./gradlew build
```

__Special thanks to Globox1997 who originally made this fantastic mod__
