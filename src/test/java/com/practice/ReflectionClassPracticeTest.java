package com.practice;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

import com.practice.model.User;

public class ReflectionClassPracticeTest {

  @Test
  public void testReflectionClassPractice() throws ClassNotFoundException, InstantiationException,
  IllegalAccessException,
  IllegalArgumentException, InvocationTargetException {
    // One way to get class Object
    String className = "com.practice.model.User";
    Class<?> classObject = Class.forName(className);

    // Another way to get class object
    Class<User> anotherObject = User.class;

    Assert.assertTrue(classObject.equals(anotherObject));

    // get the class name from class object
    String name = anotherObject.getName();
    System.out.println("Name: " + name);
    Assert.assertNotNull(name);
    Assert.assertTrue(className.equals(name));

    // get the simple class name from class object
    String simpleName = anotherObject.getSimpleName();
    System.out.println("simpleName: " + simpleName);
    Assert.assertNotNull(simpleName);

    // get the package name from class object
    String packageName = anotherObject.getPackage().getName();
    System.out.println("package name: " + packageName);
    Assert.assertNotNull(packageName);

    // get the canonical name from the class object
    String canonicalName = anotherObject.getCanonicalName();
    System.out.println("Canonical Name: " + canonicalName);
    Assert.assertNotNull(canonicalName);

    // get the superClasses of this class
    Class<?> superClass = anotherObject.getSuperclass();
    System.out.println(superClass.toString());
    Assert.assertNotNull(superClass);

    // A class can implement many interfaces. Therefore an array of Class is returned. Interfaces are also
    // represented by Class objects in Java Reflection.
    //
    // NOTE: Only the interfaces specifically declared implemented by a given class is returned. If a
    // superclass of the class implements an interface, but the class doesn't specifically state that it also
    // implements that interface, that interface will not be returned in the array. Even if the class in
    // practice implements that interface, because the superclass does.
    //
    // To get a complete list of the interfaces implemented by a given class you will have to consult both the
    // class and its superclasses recursively.
    Class<?>[] classes = anotherObject.getInterfaces();
    Assert.assertNotNull(classes);

    // get the fields in the class. will only return the public fields
    Field[] fields = anotherObject.getFields();
    Assert.assertNotNull(fields);
    Assert.assertTrue(fields.length == 0);

    // get the fields in the class. will only return all fields including the private
    Field[] fields1 = anotherObject.getDeclaredFields();
    Assert.assertNotNull(fields1);
    Assert.assertTrue(fields1.length != 0);


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
        user = constructor.newInstance("test", 1);
      }
    }

    // Assert to check that user object is created using the private constructor
    Assert.assertNotNull(user);
    Assert.assertEquals("test", user.getName());
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

    // Assert that we accessed the private method
    Assert.assertTrue(type != null && !type.isEmpty());
    Assert.assertTrue(type.equalsIgnoreCase("user"));


  }

}
