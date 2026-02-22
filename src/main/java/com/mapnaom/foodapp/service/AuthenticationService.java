package com.mapnaom.foodapp.service;

import com.mapnaom.foodapp.payload.request.AuthenticationRequest;
import com.mapnaom.foodapp.payload.request.RegisterRequest;
import com.mapnaom.foodapp.payload.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
