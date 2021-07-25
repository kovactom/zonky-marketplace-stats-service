package cz.kovactom.zonkymarketplaceservice.service;

import cz.kovactom.zonkymarketplaceservice.controller.model.LoanInterestRateSummary;

public interface LoanStatisticsService {
    LoanInterestRateSummary getInterestRateSummary(int loanFetchSize);
}
