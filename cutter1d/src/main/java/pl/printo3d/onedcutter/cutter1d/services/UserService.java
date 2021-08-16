﻿package pl.printo3d.onedcutter.cutter1d.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.printo3d.onedcutter.cutter1d.models.UserModel;
import pl.printo3d.onedcutter.cutter1d.repo.UserRepo;

@Service
public class UserService implements UserDetailsService
{
  private UserRepo uRepo;

  @Bean
  public PasswordEncoder passwordEncoder() 
  {
      return new BCryptPasswordEncoder();
  }

  @Autowired
  private PasswordEncoder pEncoder;
  

  public UserService(UserRepo uRepo) {
    this.uRepo = uRepo;
  }

  public boolean addUser(UserModel userModel)
  {
    if( userModel.getUsername() != ""  &&  userModel.getPassword() != ""  &&  userModel.getEmail() != "" &&
        userModel.getUsername() != null  &&  userModel.getPassword() != null  &&  userModel.getEmail() != null )
    {
      if( !uRepo.existsByUsername(userModel.getUsername()))
      {
        userModel.setRole("VIP"); // role dynamicznie pasuje ustawiac.
        userModel.setPassword(pEncoder.encode(userModel.getPassword()));
        uRepo.save(userModel);
        System.out.println("uService Dodajemy Usera..");
  
        return true;
      }
      else
      {
        System.out.println("uService: User exists!");
        return false;
      }
    }
    else
    {
      System.out.println("uService: Bad kredenszals!");
      return false;
    }


  }

  public boolean isLoggedIn()
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    System.out.println("getPrincipal: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    System.out.println("auth.getName: " + auth.getName());

    // na wypadek nulla
    if(auth == null)
    {
      return false;
    }

    return auth.isAuthenticated();
  }

  @Override
  public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
    return uRepo.findByUsername(arg0);
  }
}
