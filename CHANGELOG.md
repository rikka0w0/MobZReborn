1. Port to 1.21.2 and 1.21.3
2. Armor textures have been moved to `assets/mobz/textures/entity/equipment/humanoid` and `assets/mobz/textures/entity/equipment/humanoid_leggings`
3. Add MobZRarity to simplify item tooltip addition and reduces the number of localization key-value pairs required. Common localization keys start with "text.mobz.tier."
4. Localization key `item.mobz.spawnegg` is now `item.mobz.spawn_egg_of`
5. Make Withender summon more intuitive
6. Metal golem can now be repaired using hardened metal ingots, this was broken for a while
7. Food and gift of golden chicken can now be customized with item tag "golden_chicken_food" and loot table "gameplay/golden_chicken_lay"
8. Add BlazeLike and EvokerLike base class to simplify implementation
9. Improve the looking of Frost Blaze and Wither Blaze
10. Frost balls shot by a Frost Blaze no longer look on fire
11. Toad tongue now uses ModelPart
12. Fix a Wasp bug which causes vanilla bee fail to spawn
13. Give wasp a new model, texture not ready yet