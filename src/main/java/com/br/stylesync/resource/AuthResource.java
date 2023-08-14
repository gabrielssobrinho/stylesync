package com.br.stylesync.resource;

import com.br.stylesync.dto.response.JwtAuthResponse;
import com.br.stylesync.service.auth.AuthenticationService;
import com.br.stylesync.dto.request.SignInRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthResource {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signin")
    public DeferredResult<ResponseEntity<JwtAuthResponse>> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        final DeferredResult<ResponseEntity<JwtAuthResponse>> deferredResult = new DeferredResult<>();
        deferredResult.setResult(ResponseEntity.ok().body(authenticationService.signin(signInRequest)));
        return deferredResult;
    }

}
