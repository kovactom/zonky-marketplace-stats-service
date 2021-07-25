package cz.kovactom.zonkymarketplaceservice.service.impl;

import cz.kovactom.zonkymarketplaceservice.client.ZonkyMarketplaceClient;
import cz.kovactom.zonkymarketplaceservice.controller.model.LoanInterestRateSummary;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class LoanStatisticsServiceImplTest {

    @Mock
    private ZonkyMarketplaceClient zonkyMarketplaceClient;

    @InjectMocks
    private LoanStatisticsServiceImpl loanStatisticsService;

    public static Stream<Arguments> getInterestRateSummaryTestArgs() {
        return Stream.of(
                Arguments.of(List.of(1.0, 2.0, 3.0), 1.0, 2.0, 3.0),
                Arguments.of(List.of(-1.0, -2.0, -3.0), -3.0, -2.0, -1.0),
                Arguments.of(List.of(), Double.POSITIVE_INFINITY, 0.0, Double.NEGATIVE_INFINITY)
        );
    }

    @ParameterizedTest
    @MethodSource("getInterestRateSummaryTestArgs")
    void getInterestRateSummaryTest(List<Double> loansInterestRates, double expectedMin, double expectedAvg, double expectedMax) {
        Mockito.when(zonkyMarketplaceClient.getLoansInterestRates(Mockito.anyInt()))
                .thenReturn(loansInterestRates);

        final LoanInterestRateSummary interestRateSummary = loanStatisticsService.getInterestRateSummary(100);
        assertThat(interestRateSummary.getMinInterestRate()).isEqualTo(expectedMin);
        assertThat(interestRateSummary.getAverageInterestRate()).isEqualTo(expectedAvg);
        assertThat(interestRateSummary.getMaxInterestRate()).isEqualTo(expectedMax);
    }
}
