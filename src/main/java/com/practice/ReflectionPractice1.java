package com.practice;

import java.lang.reflect.Method;

import com.practice.model.User;

public class ReflectionPractice1 {

  /**
   * Just getting the method names in class
   *
   * @param args
   */
  public static void main(String[] args) {
    Method[] methods = User.class.getMethods();

    for (Method method : methods) {
      System.out.println("Method : " + method.getName());
    }
  }

}
