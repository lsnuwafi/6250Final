//package com.me.util;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Userutil {
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//    List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
//updatedAuthorities.add(...); //add your role here [e.g., new SimpleGrantedAuthority("ROLE_NEW_ROLE")]
//
//    Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
//
//    SecurityContextHolder.getContext().setAuthentication(newAuth);
//}
