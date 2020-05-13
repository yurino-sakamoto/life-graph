package com.lifegraph.team20.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "users")
public class Users {
//  @Id
  private String id;

  @NotBlank
  @Size
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
  @NotBlank
  @Size(max = 120)
  private String password;

//  @DBRef
  private Set<Role> roles = new HashSet<>();

  public Users() {
  }

  public Users(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
}