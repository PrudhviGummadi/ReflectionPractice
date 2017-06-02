package com.practice;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Assert;

import com.practice.model.User;

public class ReflectionClassPractice {

  public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
  IllegalArgumentException, InvocationTargetException {
    // One way to get class Object
    String className = "com.practice.model.User";
    Class<?> classObject = Class.forName(className);

    // Another way to get class object
    Class<User> anotherObject = User.class;

    Assert.assertTrue(classObject.equals(anotherObject));

    // get the class name from class object
    String name = anotherObject.getName();
    Assert.assertNotNull(name);
    Assert.assertTrue(className.equals(name));

    // Inspect the Class Object
    // getting the constructor info for that class
    // getDeclaredConstructors() method will return all declared constructors in the class including the
    // private constructors excluding where as getConstructors() will return only the public constructors
    @SuppressWarnings("unchecked")
    Constructor<User>[] constructors = (Constructor<User>[]) anotherObject.getDeclaredConstructors();

    User user = null;

    for (Constructor<User> constructor : constructors) {
      if (constructor.getParameterTypes().length == 2) {
        constructor.setAccessible(true);
        user = constructor.newInstance("sai", 1);
      }
    }

    // Assert to check that user object is created using the private constructor
    Assert.assertNotNull(user);
    Assert.assertEquals("sai", user.getName());
    Assert.assertTrue(1 == user.getId().intValue());

    // Check whether the class modifier for given class is public or not
    Assert.assertTrue(Modifier.isPublic(classObject.getModifiers()));

    // how to find method in a class. getMethods() will only return the public methods
    Method[] methods = anotherObject.getMethods();
    System.out.println("List of methods: " + methods.length);

    String type = "";

    // how to find method in a class. getDeclaredMethods() will return all methods including private excluding
    // inherited Methods
    Method[] methods1 = anotherObject.getDeclaredMethods();
    System.out.println("List of methods: " + methods1.length);
    for (Method method : methods1) {
      if (Modifier.isPrivate(method.getModifiers())) {
        method.setAccessible(true);
        type = (String) method.invoke(anotherObject.newInstance());
      }
    }

    Assert.assertTrue(type != null && !type.isEmpty());
    Assert.assertTrue(type.equalsIgnoreCase("user"));


  }

}
