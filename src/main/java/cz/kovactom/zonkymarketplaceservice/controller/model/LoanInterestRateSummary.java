package cz.kovactom.zonkymarketplaceservice.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class LoanInterestRateSummary {
    private double minInterestRate;
    private double averageInterestRate;
    private double maxInterestRate;
}
