package com.hina.tutorial.controller;

import com.hina.tutorial.dto.request.ApiResponse;
import com.hina.tutorial.dto.request.AuthenticationRequest;
import com.hina.tutorial.dto.response.AuthenticationResponse;
import com.hina.tutorial.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        boolean result = authenticationService.authenticate(request);
        return ApiResponse
                .<AuthenticationResponse>builder()
                    .result(AuthenticationResponse
                            .builder()
                            .authenticated(result).build())
                .build();
    }
}
