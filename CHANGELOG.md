1. Fix the mineable problem of amat, boss, and the hardened metal blocks.
2. Fix the disappearing problem when inserting medieval music disc.
3. Avoid hard-coded JSON and use data generator where possible. Simple block models, block states, loot tables, advancements, and recipes are now completely using data generator. This will simplify the future porting of this mod and prevent possible bugs like the two above.
4. Use loot table for advancement rewards, no longer use mcfunction