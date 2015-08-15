## What is MOL? ##

MOL stands for My Own Language. The name is a bit misleading since it is not a language!
It is an interpreter for scripts written in any programming language designed by the user.
An important feature of MOL is its plugin-based architecture.
Users can easily write MOL modules in Java and treat MOL as a glue layer
while keeping all performance critical calculations in Java. They can also use existing Java
modules (like Math) and import them without any problem.
At the moment MOL contains a test parser for a simple language based on XML.

## Why another interpreter? ##

MOL is just a toy project. It was created for fun and to learn how Java reflection can be used to implement plugin-based architectures.

MOL is not finished yet. Below is a TODO list:
  * Demo parser for new language similar to Python
  * Exceptions handling
  * Documentation
  * Comments
  * Clean up
  * More unit tests


## Example XML based script: ##
```
<program>
  <import class="test_plugins.Math2D" alias="math" />
  <assigment identifier="vector1">
    <call method="getVector">
      <var identifier="math" />
      <int value="2" />
      <int value="2" />
    </call>
  </assigment>
  <assigment identifier="vector2">
    <call method="getVector">
      <var identifier="math" />
      <int value="10" />
      <int value="10" />
    </call>
  </assigment>
  <while>
    <condition>
      <lessThan>
        <var identifier="vector1" />
        <var identifier="vector2" />
      </lessThan>
    </condition>
    <action>
      <call method="add">
        <var identifier="vector1" />
        <int value="1" />
        <int value="1" />
      </call>
    </action>
  </while>
</program>
```

## Example Plugin Math2D: ##
```
package test_plugins;

import unitTests.Vector2D;

public class Math2D {
  
    public Vector2D getVector(int x, int y) {
        return new Vector2D(x, y);
    }
}
```

## Example Plugin Vector2D: ##
```
package unitTests;

import annotations.*;

public class Vector2D {
    private int x;
    private int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Addition
    public void add(int offset) {
        x += offset;
        y += offset;
    }

    @Addition
    public void add(int xoffset, int yoffset) {
        x += xoffset;
        y += yoffset;
    }

    @EqualTo
    public boolean equalTo(Vector2D vector) {
        if (x == vector.x && y == vector.y) {
            return true;
        } else {
            return false;
        }
    }

    @LessThan
    public boolean lessThan(Vector2D vector) {
        if (x < vector.x && y < vector.y) {
            return true;
        } else {
            return false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "Vector2D: x:" + x + " ==> y: " + y;
    }
}
```