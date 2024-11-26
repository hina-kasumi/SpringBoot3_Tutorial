package com.hina.tutorial.controller;

import com.hina.tutorial.dto.request.ApiResponse;
import com.hina.tutorial.dto.request.AuthenticationRequest;
import com.hina.tutorial.dto.request.IntrospectRequest;
import com.hina.tutorial.dto.response.AuthenticationResponse;
import com.hina.tutorial.dto.response.IntrospectResponse;
import com.hina.tutorial.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse
                .<AuthenticationResponse>builder()
                    .result(result)
                .build();
    }
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspectResponse(request);
        return ApiResponse
                .<IntrospectResponse>builder()
                    .result(result)
                .build();
    }
}
