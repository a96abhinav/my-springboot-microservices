package com.springboot.rest.config.security;

import com.springboot.rest.entity.myspringappdb.Role;
import com.springboot.rest.model.RoleModel;
import com.springboot.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        Map<String, Object> attributes = oAuth2User.getAttributes();

        Map<String, Object> commonAttributes = new HashMap<>();
        Set<SimpleGrantedAuthority> authorities = (Set<SimpleGrantedAuthority>) new HashSet<>(oAuth2User.getAuthorities());

        if(registrationId.equals("google") || registrationId.equals("github")) {
            if (registrationId.equals("google")) {
                commonAttributes.put("username", attributes.get("email").toString().split("@")[0]);
            } else if (registrationId.equals("github")) {
                commonAttributes.put("username", attributes.get("login"));
            }

            String username = (String) commonAttributes.get("username");
            if(null != userService.getUserByUsername(username) && null != userService.getUserByUsername(username).getRoles()) {
                Set<RoleModel> userRoles = userService.getUserByUsername(username).getRoles();

                List<SimpleGrantedAuthority> grantedAuthorities = userRoles.stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRole()))
                        .collect(Collectors.toList());

                authorities.addAll(grantedAuthorities);
            }
        }

        return new DefaultOAuth2User(authorities, commonAttributes, "username");
    }
}
