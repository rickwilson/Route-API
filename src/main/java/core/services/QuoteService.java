package core.services;

import core.entities.ApiKey;
import core.entities.enums.MarkupType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service
public class QuoteService {
    private final CurrencyService currencyService;

    @Autowired
    public QuoteService(CurrencyService currencyService) {
        Assert.notNull(currencyService, "CurrencyService must not be null!");
        this.currencyService = currencyService;
    }

    public BigDecimal getInsuranceCostUSD(BigDecimal totalValue, String currencyCode, ApiKey apiKeyObject) {
        // convert total to USD
        BigDecimal usdValue = totalValue;
        if(!currencyCode.equalsIgnoreCase("USD")) {
            usdValue = currencyService.changeCurrencyBigDecimal(currencyCode,"USD",totalValue);
        }
        return calculateInsurance(usdValue,apiKeyObject);
    }

    public BigDecimal getSuggestedInsurancePrice(BigDecimal insuranceCostUSD, String currencyCode, ApiKey apiKeyObject) {

        BigDecimal suggestedInsurancePrice = insuranceCostUSD;
        if(apiKeyObject.getMarkupType() == MarkupType.FREE) {
            suggestedInsurancePrice = BigDecimal.ZERO;
        } else if(apiKeyObject.getMarkupType() == MarkupType.ADD_FIXED) {
            suggestedInsurancePrice = suggestedInsurancePrice.add(new BigDecimal(apiKeyObject.getMarkupFixed()));
        } else if(apiKeyObject.getMarkupType() == MarkupType.ADD_PERCENT) {
            suggestedInsurancePrice = suggestedInsurancePrice.add(suggestedInsurancePrice.multiply(new BigDecimal(apiKeyObject.getMarkupPercent())).divide(new BigDecimal("100")));
        } else if(apiKeyObject.getMarkupType() == MarkupType.MACHINE_LEARNING) {
            suggestedInsurancePrice = suggestedInsurancePrice.add(suggestedInsurancePrice.multiply(new BigDecimal("30")).divide(new BigDecimal("100")));
        } else if(apiKeyObject.getMarkupType() == MarkupType.SUBTRACT_FIXED) {
            suggestedInsurancePrice = suggestedInsurancePrice.subtract(new BigDecimal(apiKeyObject.getMarkupFixed()));
        } else if(apiKeyObject.getMarkupType() == MarkupType.SUBTRACT_PERCENT) {
            suggestedInsurancePrice = suggestedInsurancePrice.subtract(suggestedInsurancePrice.multiply(new BigDecimal(apiKeyObject.getMarkupPercent())).divide(new BigDecimal("100")));
        }
        if(!currencyCode.equalsIgnoreCase("USD")) {
            suggestedInsurancePrice = currencyService.changeCurrencyBigDecimal("USD",currencyCode,suggestedInsurancePrice);
        }
        suggestedInsurancePrice = currencyService.trimTrailingDigitsForCurrency(suggestedInsurancePrice,currencyCode);
        return suggestedInsurancePrice;
    }

    private BigDecimal calculateInsurance(BigDecimal usdValue, ApiKey apiKeyObject) {
        BigDecimal insuranceCost = new BigDecimal("2.95");
        if(usdValue.compareTo(BigDecimal.ZERO) <= 0) {
            insuranceCost = BigDecimal.ZERO;
        } else if(usdValue.compareTo(new BigDecimal("100")) > 0 && usdValue.compareTo(new BigDecimal("200")) <= 0) {
            insuranceCost = new BigDecimal("3.70");
        } else if(usdValue.compareTo(new BigDecimal("200")) > 0 && usdValue.compareTo(new BigDecimal("300")) <= 0) {
            insuranceCost = new BigDecimal("4.45");
        } else if(usdValue.compareTo(new BigDecimal("300")) > 0 && usdValue.compareTo(new BigDecimal("400")) <= 0) {
            insuranceCost = new BigDecimal("5.20");
        } else if(usdValue.compareTo(new BigDecimal("400")) > 0 && usdValue.compareTo(new BigDecimal("500")) <= 0) {
            insuranceCost = new BigDecimal("5.95");
        } else if(usdValue.compareTo(new BigDecimal("500")) > 0 && usdValue.compareTo(new BigDecimal("600")) <= 0) {
            insuranceCost = new BigDecimal("6.70");
        } else if(usdValue.compareTo(new BigDecimal("600")) > 0 && usdValue.compareTo(new BigDecimal("700")) <= 0) {
            insuranceCost = new BigDecimal("7.45");
        } else if(usdValue.compareTo(new BigDecimal("700")) > 0 && usdValue.compareTo(new BigDecimal("800")) <= 0) {
            insuranceCost = new BigDecimal("8.20");
        } else if(usdValue.compareTo(new BigDecimal("800")) > 0 && usdValue.compareTo(new BigDecimal("900")) <= 0) {
            insuranceCost = new BigDecimal("8.95");
        } else if(usdValue.compareTo(new BigDecimal("100")) > 0 && usdValue.compareTo(new BigDecimal("1000")) <= 0) {
            insuranceCost = new BigDecimal("9.70");
        } else if(usdValue.compareTo(new BigDecimal("1000")) > 0 && usdValue.compareTo(new BigDecimal("2000")) <= 0) {
            insuranceCost = new BigDecimal("19.45");
        } else if(usdValue.compareTo(new BigDecimal("2000")) > 0 && usdValue.compareTo(new BigDecimal("3000")) <= 0) {
            insuranceCost = new BigDecimal("29.20");
        } else if(usdValue.compareTo(new BigDecimal("3000")) > 0 && usdValue.compareTo(new BigDecimal("4000")) <= 0) {
            insuranceCost = new BigDecimal("38.95");
        } else if(usdValue.compareTo(new BigDecimal("4000")) > 0 && usdValue.compareTo(new BigDecimal("5000")) <= 0) {
            insuranceCost = new BigDecimal("48.70");
        } else if(usdValue.compareTo(new BigDecimal("5000")) > 0 && usdValue.compareTo(new BigDecimal("6000")) <= 0) {
            insuranceCost = new BigDecimal("58.45");
        } else if(usdValue.compareTo(new BigDecimal("6000")) > 0 && usdValue.compareTo(new BigDecimal("7000")) <= 0) {
            insuranceCost = new BigDecimal("68.20");
        } else if(usdValue.compareTo(new BigDecimal("7000")) > 0 && usdValue.compareTo(new BigDecimal("8000")) <= 0) {
            insuranceCost = new BigDecimal("77.95");
        } else if(usdValue.compareTo(new BigDecimal("8000")) > 0 && usdValue.compareTo(new BigDecimal("9000")) <= 0) {
            insuranceCost = new BigDecimal("87.70");
        } else if(usdValue.compareTo(new BigDecimal("9000")) > 0 && usdValue.compareTo(new BigDecimal("10000")) <= 0) {
            insuranceCost = new BigDecimal("97.45");
        } else if(usdValue.compareTo(new BigDecimal("10000")) > 0 ) {
            insuranceCost = new BigDecimal("1000");
        }
        return insuranceCost;
    }
}
