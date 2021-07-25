package cz.kovactom.zonkymarketplaceservice.client;

import java.util.List;

public interface ZonkyMarketplaceClient {
    List<Double> getLoansInterestRates(int fetchSize);
}
