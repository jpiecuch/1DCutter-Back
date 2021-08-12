﻿package pl.printo3d.onedcutter.cutter1d.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class UserModel implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  Long id;

  String username;
  String password;
  String role;
  String email;
  String phone;
  String website;

  public UserModel(){}

  public UserModel(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }


  public UserModel(String username, String password) {
    this.username = username;
    this.password = password;
  }

  

  public UserModel(String username, String password, String email, String website) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.website = website;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    //return Collections.singleton(new SimpleGrantedAuthority(role));
    return null;
  }

  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    return password;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }
  
  
}