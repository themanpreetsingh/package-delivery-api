package com.perennial.deliveryApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.perennial.deliveryApp.customExceptions.RejectedPackageException;
import com.perennial.deliveryApp.requests.PackageCostRequest;
import com.perennial.deliveryApp.services.PackageCostService;

@SpringBootTest
class PackageServiceTests {

	@Autowired
	private PackageCostService packageCostService;
	
	@Test
    public void testCalculateDeliveryCost_HighWeightRejected() {
        PackageCostRequest parcelRequest = new PackageCostRequest();
        parcelRequest.setWeight(60);
        parcelRequest.setHeight(20);
        parcelRequest.setLength(20);
        parcelRequest.setWidth(20);

        try {
        	packageCostService.calculateDeliveryCost(parcelRequest);
            // If no exception is thrown, the test fails
            fail("Expected RejectedPackageException");
        } catch (RejectedPackageException e) {
            // The test passes if RejectedParcelException is thrown
            assertEquals("Parcel weight exceeds 50kg. Rejected.", e.getMessage());
        }
    }

    @Test
    public void testCalculateDeliveryCost_HeavyParcel() {
    	PackageCostRequest parcelRequest = new PackageCostRequest();
    	parcelRequest.setWeight(15);
        parcelRequest.setHeight(20);
        parcelRequest.setLength(20);
        parcelRequest.setWidth(20);
        
        double expectedCost = 20 * parcelRequest.getWeight();
        double actualCost = packageCostService.calculateDeliveryCost(parcelRequest);

        assertEquals(expectedCost, actualCost, 0.001);
    }

    @Test
    public void testCalculateDeliveryCost_SmallParcel() {
        PackageCostRequest parcelRequest = new PackageCostRequest();
        parcelRequest.setWeight(5);
        parcelRequest.setHeight(10);
        parcelRequest.setLength(10);
        parcelRequest.setWidth(10);
        
        double expectedCost = 0.03 * parcelRequest.getLength() * parcelRequest.getHeight() * parcelRequest.getWidth();
        double actualCost = packageCostService.calculateDeliveryCost(parcelRequest);

        assertEquals(expectedCost, actualCost, 0.001);
    }

    @Test
    public void testCalculateDeliveryCost_MediumParcel() {
        PackageCostRequest parcelRequest = new PackageCostRequest();
        parcelRequest.setWeight(5);
        parcelRequest.setHeight(20);
        parcelRequest.setLength(10);
        parcelRequest.setWidth(10);
        
        double expectedCost = 0.04 * parcelRequest.getLength() * parcelRequest.getHeight() * parcelRequest.getWidth();
        double actualCost = packageCostService.calculateDeliveryCost(parcelRequest);

        assertEquals(expectedCost, actualCost, 0.001);
    }

    @Test
    public void testCalculateDeliveryCost_LargeParcel() {
        PackageCostRequest parcelRequest = new PackageCostRequest();
        parcelRequest.setWeight(5);
        parcelRequest.setHeight(30);
        parcelRequest.setLength(30);
        parcelRequest.setWidth(30);
        
        double expectedCost = 0.05 * parcelRequest.getLength() * parcelRequest.getHeight() * parcelRequest.getWidth();
        double actualCost = packageCostService.calculateDeliveryCost(parcelRequest);

        assertEquals(expectedCost, actualCost, 0.001);
    }

}
