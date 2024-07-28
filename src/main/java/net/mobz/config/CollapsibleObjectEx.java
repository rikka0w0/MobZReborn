package net.mobz.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CollapsibleObjectEx {
	boolean startExpanded() default false;
	boolean includeSuperClass() default true;
}
