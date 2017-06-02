package com.practice.model;

public class User {

  private final String type="USER";

  private String name;

  private Integer id;

  /**
   *
   */
  public User() {
    super();
  }

  /**
   * @param name
   * @param id
   */
  private User(String name, Integer id) {
    super();
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "User [type=" + type + ", name=" + name + ", id=" + id + "]";
  }

  private String type() {
    return type;
  }


}
