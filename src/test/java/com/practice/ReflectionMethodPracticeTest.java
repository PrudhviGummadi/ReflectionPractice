package com.practice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.practice.model.User;

import junit.framework.Assert;

public class ReflectionMethodPracticeTest {

  @Test
  public void testReflectionMethodPractice()
      throws NoSuchMethodException, SecurityException, InstantiationException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {

    Class<User> clazz = User.class;

    // get the public methods including the superClass Methods.
    Method[] methods = clazz.getMethods();

    for (Method method : methods) {
      Assert.assertTrue(Modifier.isPublic(method.getModifiers()));
    }

    // get the private methods
    Method[] methods1 = clazz.getDeclaredMethods();
    Assert.assertTrue(methods1.length != 0);

    User user=clazz.newInstance();

    // get the Method parameter types and invoke the setter to set the value
    Method method3 = clazz.getDeclaredMethod("setName", String.class);
    method3.setAccessible(true);
    Assert.assertTrue(method3.getParameterCount() == 1);
    Class[] clazzes = method3.getParameterTypes();
    Assert.assertTrue(clazzes[0].equals(String.class));
    method3.invoke(user, "Test User");

    // get the Method parameter types and invoking the getter method
    Method method2 = clazz.getDeclaredMethod("getName");
    Assert.assertTrue(method2.getParameterTypes().length == 0);
    Assert.assertTrue(method2.getParameterCount() == 0);
    Assert.assertTrue(method2.getReturnType().equals(String.class));
    method2.setAccessible(true);
    String name = (String) method2.invoke(user);


    Assert.assertEquals(name, user.getName());


  }

}
