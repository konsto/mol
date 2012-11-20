package plugin;

import java.lang.annotation.*; 

@Retention(RetentionPolicy.RUNTIME)
public @interface PluginTypeAnnotation 
{
	String type() default "static";
}