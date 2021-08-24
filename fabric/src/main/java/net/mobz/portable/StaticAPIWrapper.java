package net.mobz.portable;

import net.mobz.IRegistryWrapper;
import net.mobz.fabric.FabricRegistryWrapper;

public class StaticAPIWrapper {
	public static IRegistryWrapper instance = new FabricRegistryWrapper();
}
