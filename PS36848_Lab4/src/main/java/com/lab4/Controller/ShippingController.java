package com.lab4.Controller;

import org.springframework.web.bind.annotation.*;

import com.lab4.service.ShippingCostResponse;
import com.lab4.service.ShippingRequest;
import com.lab4.service.ShippingService;


@RestController
@RequestMapping("/shipping")
public class ShippingController {
	 private final ShippingService shippingService;

	    public ShippingController(ShippingService shippingService) {
	        this.shippingService = shippingService;
	    }

	    @PostMapping("/calculate")
	    public ShippingCostResponse calculateShipping(@RequestBody ShippingRequest request) {
	        return shippingService.calculateShippingCost(request);
	    }
}
