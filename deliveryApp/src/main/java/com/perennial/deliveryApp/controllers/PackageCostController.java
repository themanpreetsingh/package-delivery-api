package com.perennial.deliveryApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perennial.deliveryApp.requests.PackageCostRequest;
import com.perennial.deliveryApp.responses.PackageCostResponse;
import com.perennial.deliveryApp.services.PackageCostService;

@RestController
@RequestMapping("/api")
public class PackageCostController {

	@Autowired
	private PackageCostService packageCostService;
	
	@GetMapping("/packageCost")
	public ResponseEntity<PackageCostResponse> calculateDeliveryCost(@RequestBody PackageCostRequest packageCostRequest) {
        // Validate the input
        if (!isValidParcelRequest(packageCostRequest)) {
            return ResponseEntity.badRequest().build();
        }

        // Calculate the delivery cost
        double cost = packageCostService.calculateDeliveryCost(packageCostRequest);

        // Create and return the response
        PackageCostResponse response = new PackageCostResponse();
        response.setCost(cost);
        return ResponseEntity.ok(response);
    }

	private boolean isValidParcelRequest(PackageCostRequest packageCostRequest) {
		return packageCostRequest != null && packageCostRequest.getWeight() > 0 &&
				packageCostRequest.getHeight() > 0 && packageCostRequest.getWidth() > 0 && packageCostRequest.getLength() > 0;
	}
}
