package com.apimicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
	
	@Autowired
    private RestTemplate restTemplate;
	@Autowired
    private JwtUtil jwt;
	@Value("${HOST_NAME}")	
	private String hostName;

    public AuthResponse register(AuthRequest authRequest) {
    	
        User user = restTemplate.postForObject(hostName, authRequest, User.class);

        String accessToken = jwt.generate(user, "ACCESS");
        String refreshToken = jwt.generate(user, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);

    }
}