package com.springboot.rest.controller;

import com.springboot.rest.constants.CommonConstants;
import com.springboot.rest.model.StringResponseModel;
import com.springboot.rest.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = CommonConstants.EMAIL_SVC)
@RequiredArgsConstructor
@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(CommonConstants.API_PATH + CommonConstants.EMAIL_SVC)
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/send")
    public StringResponseModel sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
        return new StringResponseModel("Email sent successfully!");
    }
}