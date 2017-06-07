package com.practice;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

import com.practice.model.User;

public class ReflectionFieldPracticeTest {

  @Test
  public void testReflectionFieldPractice()
      throws ClassNotFoundException, InstantiationException,
      IllegalAccessException, NoSuchFieldException, SecurityException {
    @SuppressWarnings("unchecked")
    Class<User> userClass = (Class<User>) Class.forName("com.practice.model.User");


    // get all field which has access modifier as public
    Field[] fields = userClass.getFields();
    Assert.assertTrue(fields.length == 0);

    // get all private fields
    Field[] fields1 = userClass.getDeclaredFields();
    for (Field field : fields1) {
      Assert.assertTrue(Modifier.isPrivate(field.getModifiers()));
    }

    // how to access private fields getter and setters
    User user = userClass.newInstance();
    Assert.assertNotNull(user);

    for (Field field : fields1) {
      field.setAccessible(true);
      String fieldName = field.getName();
      if (fieldName.equals("name")) {
        field.set(user, "sai");
      } else if (fieldName.equals("id")) {
        field.set(user, new Integer(1));
      }
    }

    Assert.assertEquals("sai", user.getName());
    Assert.assertEquals(1, user.getId().intValue());

    // get field using the field Name if we know the field. Instead of getting all fields and loop over
    // everything
    Field type = userClass.getDeclaredField("type");
    type.setAccessible(true);
    Assert.assertEquals("USER", type.get(user));
    Assert.assertTrue(type.getType().equals(String.class));

    // get the type of the field
    Field name = userClass.getDeclaredField("name");
    Assert.assertTrue(name.getType().equals(String.class));

    // get the type of the field
    Field id = userClass.getDeclaredField("id");
    Assert.assertTrue(id.getType().equals(Integer.class));

  }

}
