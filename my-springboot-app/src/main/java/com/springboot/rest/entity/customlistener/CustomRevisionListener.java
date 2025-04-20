package com.springboot.rest.entity.customlistener;

import com.springboot.rest.entity.myspringappdb.CustomRevisionInformationEntity;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionInformationEntity customRevisionEntity = (CustomRevisionInformationEntity) revisionEntity;
        customRevisionEntity.setCreatedBy(getLoggedInUser());
    }

    private String getLoggedInUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
