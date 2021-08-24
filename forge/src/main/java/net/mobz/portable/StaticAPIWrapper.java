package net.mobz.portable;

import net.mobz.IRegistryWrapper;
import net.mobz.forge.ForgeRegistryWrapper;

public class StaticAPIWrapper {
	public static IRegistryWrapper instance = new ForgeRegistryWrapper();
}
