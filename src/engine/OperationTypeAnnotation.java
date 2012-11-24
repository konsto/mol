package engine;

import java.lang.annotation.*; 

@Retention(RetentionPolicy.RUNTIME)
public @interface OperationTypeAnnotation 
{
    String operation();
}