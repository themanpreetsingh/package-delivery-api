package com.perennial.deliveryApp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.perennial.deliveryApp.customExceptions.RejectedPackageException;
import com.perennial.deliveryApp.requests.PackageCostRequest;

@Service
public class PackageCostService {
	
	@Value("${delivery.heavyParcelCostMultiplier}")
    private double heavyParcelCostMultiplier;

    @Value("${delivery.smallParcelCostMultiplier}")
    private double smallParcelCostMultiplier;

    @Value("${delivery.mediumParcelCostMultiplier}")
    private double mediumParcelCostMultiplier;

    @Value("${delivery.largeParcelCostMultiplier}")
    private double largeParcelCostMultiplier;

	public double calculateDeliveryCost(PackageCostRequest packageCostRequest) {
		double packageCost = 0;
		
		// Check for rejected parcel based on priority rule 1
        if (packageCostRequest.getWeight() > 50) {
            throw new RejectedPackageException("Parcel weight exceeds 50kg. Rejected.");
        }
		
		// Calculate cost based on priority rules 2 to 5
        if (packageCostRequest.getWeight() > 10) {
        	packageCost = heavyParcelCostMultiplier * packageCostRequest.getWeight();
        } else {
            double volume = packageCostRequest.getHeight() * packageCostRequest.getWidth() * packageCostRequest.getLength();

            if (volume < 1500) {
            	packageCost = smallParcelCostMultiplier * volume;
            } else if (volume < 2500) {
            	packageCost = mediumParcelCostMultiplier * volume;
            } else {
            	packageCost = largeParcelCostMultiplier * volume;
            }
        }
        
		return packageCost;
	}

}
