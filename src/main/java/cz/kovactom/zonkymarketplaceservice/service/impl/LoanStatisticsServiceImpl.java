package cz.kovactom.zonkymarketplaceservice.service.impl;

import cz.kovactom.zonkymarketplaceservice.client.ZonkyMarketplaceClient;
import cz.kovactom.zonkymarketplaceservice.controller.model.LoanInterestRateSummary;
import cz.kovactom.zonkymarketplaceservice.service.LoanStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;

@RequiredArgsConstructor
@Service
public class LoanStatisticsServiceImpl implements LoanStatisticsService {

    private final ZonkyMarketplaceClient zonkyMarketplaceClient;

    @Override
    public LoanInterestRateSummary getInterestRateSummary(int loanFetchSize) {
        final DoubleSummaryStatistics interestRateStats = zonkyMarketplaceClient.getLoansInterestRates(loanFetchSize)
                .stream()
                .mapToDouble(e -> e)
                .summaryStatistics();

        return LoanInterestRateSummary.builder()
                .minInterestRate(interestRateStats.getMin())
                .averageInterestRate(interestRateStats.getAverage())
                .maxInterestRate(interestRateStats.getMax())
                .build();
    }
}
