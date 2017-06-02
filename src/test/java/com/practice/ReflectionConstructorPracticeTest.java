package com.practice;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.practice.model.User;

import junit.framework.Assert;

public class ReflectionConstructorPracticeTest {

  @Test
  public void testReflectionConctructorPractice() throws NoSuchMethodException, SecurityException,
  InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

    Class<User> userClass = User.class;

    // get the public constructors
    Constructor<User>[] constructors = (Constructor<User>[]) userClass.getConstructors();
    Assert.assertNotNull(constructors);
    for (Constructor<User> constructor : constructors) {
      Assert.assertTrue(Modifier.isPublic(constructor.getModifiers()));
    }

    // get the private constructors
    Constructor<User>[] constructors1 = (Constructor<User>[]) userClass.getDeclaredConstructors();
    Assert.assertNotNull(constructors1);
    for (Constructor<User> constructor : constructors1) {
      if (Modifier.isPrivate(constructor.getModifiers())) {
        System.out
        .println("There is a private constructor in this class with arguments: " + constructor.getParameterCount());
      }
    }

    // get public Constructor with one argument
    Constructor<User> singleParameterConstructor = userClass.getConstructor(new Class[] { String.class });
    Assert.assertNotNull(singleParameterConstructor);
    User user = singleParameterConstructor.newInstance("sai");
    Assert.assertNotNull(user);
    Assert.assertEquals("sai", user.getName());
    Assert.assertNull(user.getId());

    // get PrivateConstructor with one argument
    Constructor<User> singlePrivateConstructor = userClass.getDeclaredConstructor(new Class[] { Integer.class });
    Assert.assertNotNull(singlePrivateConstructor);
    Assert.assertTrue(Modifier.isPrivate(singlePrivateConstructor.getModifiers()));
    Assert.assertTrue(singlePrivateConstructor.getParameterCount() == 1);
    singlePrivateConstructor.setAccessible(true);
    User user1 = singlePrivateConstructor.newInstance(1);
    Assert.assertNotNull(user1);
    Assert.assertTrue(user1.getId().intValue() == 1);

    // get PrivateConstructor with two arguments
    Constructor<User> doublePrivateConstructor = userClass
        .getDeclaredConstructor(new Class[] { String.class, Integer.class });
    Assert.assertNotNull(doublePrivateConstructor);
    Assert.assertTrue(Modifier.isPrivate(doublePrivateConstructor.getModifiers()));
    Assert.assertTrue(doublePrivateConstructor.getParameterCount() == 2);
    doublePrivateConstructor.setAccessible(true);
    User user2 = doublePrivateConstructor.newInstance("sai", 1);
    Assert.assertNotNull(user2);
    Assert.assertEquals("sai", user2.getName());
    Assert.assertTrue(user2.getId().intValue() == 1);

  }

}
