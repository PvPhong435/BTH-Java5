package com.lab4.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ShippingService {

    private static final String API_URL = "https://api.grab.com/v1/..."; // Thay đổi endpoint theo API cụ thể
    private final String API_KEY = "YOUR_API_KEY"; // Thay đổi với API key của bạn

    public ShippingCostResponse calculateShippingCost(ShippingRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        
        // Thêm header cho yêu cầu
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        HttpEntity<ShippingRequest> entity = new HttpEntity(request, headers);
        
        // Gửi yêu cầu và nhận phản hồi
        ResponseEntity<ShippingCostResponse> response = restTemplate.exchange(
            API_URL,
            HttpMethod.POST,
            entity,
            ShippingCostResponse.class
        );

        return response.getBody();
    }
}
