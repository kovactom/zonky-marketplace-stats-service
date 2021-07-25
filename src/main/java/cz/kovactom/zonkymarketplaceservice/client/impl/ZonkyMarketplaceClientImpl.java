package cz.kovactom.zonkymarketplaceservice.client.impl;

import cz.kovactom.zonkymarketplaceservice.client.ZonkyMarketplaceClient;
import cz.kovactom.zonkymarketplaceservice.client.model.Loan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ZonkyMarketplaceClientImpl implements ZonkyMarketplaceClient {

    public static final String ROOT_URL = "https://api.zonky.cz/loans/marketplace";

    private static final String X_PAGE_HEADER = "X-Page";
    private static final String X_SIZE_HEADER = "X-Size";

    private final RestTemplate restTemplate;

    public ZonkyMarketplaceClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .build();
    }

    @Override
    public List<Double> getLoansInterestRates(int fetchSize) {
        final URI targetUri = UriComponentsBuilder.fromHttpUrl(ROOT_URL)
                .queryParam("fields", "interestRate")
                .build()
                .toUri();

        final HttpHeaders headers = new HttpHeaders();
        headers.add(X_PAGE_HEADER, "0");
        headers.add(X_SIZE_HEADER, String.valueOf(fetchSize));

        final ResponseEntity<List<Loan>> responseEntity = restTemplate.exchange(
                targetUri,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        final List<Loan> result = responseEntity.getBody();
        if (result == null) return Collections.emptyList();

        return result.stream()
                .map(Loan::getInterestRate)
                .collect(Collectors.toList());
    }
}
