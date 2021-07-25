package cz.kovactom.zonkymarketplaceservice.controller;

import cz.kovactom.zonkymarketplaceservice.controller.model.LoanInterestRateSummary;
import cz.kovactom.zonkymarketplaceservice.service.LoanStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/loans")
public class LoanStatisticsController {

    private final LoanStatisticsService loanStatisticsService;

    @GetMapping("/interest-rate/summary")
    public LoanInterestRateSummary getLoanInterestRateSummary(@RequestParam(required = false, defaultValue = "100") int loanFetchSize) {
        return loanStatisticsService.getInterestRateSummary(loanFetchSize);
    }
}
