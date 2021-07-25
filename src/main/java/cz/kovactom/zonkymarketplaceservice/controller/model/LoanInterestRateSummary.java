package cz.kovactom.zonkymarketplaceservice.controller.model;

import lombok.Value;

@Value
public class LoanInterestRateSummary {
    private double minInterestRate;
    private double averageInterestRate;
    private double maxInterestRate;
}
