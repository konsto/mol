package plugin;

import java.lang.annotation.*; 

@Retention(RetentionPolicy.RUNTIME)
public @interface APluginType 
{
	String type() default "static";
}