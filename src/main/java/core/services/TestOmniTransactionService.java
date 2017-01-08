package core.services;

import com.bugsnag.Bugsnag;
import core.entities.*;
import core.entities.enums.ShippingNotificationType;
import core.entities.enums.SourceEntity;
import core.entities.enums.TransactionState;
import core.security.PrintHttpReport;
import core.thirdParty.aftership.enums.StatusTag;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TestOmniTransactionService {
    private final TestOmniTransactionDAO testOmniTransactionDAO;
    private final TestOmniTransactionItemDAO testOmniTransactionItemDAO;
    private final TestOmniTransactionDetailDAO testOmniTransactionDetailDAO;
    private final TestOpenApiOrderDAO testOpenApiOrderDAO;
    private final TestOpenApiQuoteDAO testOpenApiQuoteDAO;
    private final CourierNameService courierNameService;
    private final CurrencyService currencyService;

    public TestOmniTransactionService(TestOmniTransactionDAO testOmniTransactionDAO,
                                      TestOmniTransactionItemDAO testOmniTransactionItemDAO,
                                      TestOmniTransactionDetailDAO testOmniTransactionDetailDAO,
                                      TestOpenApiOrderDAO testOpenApiOrderDAO,
                                      TestOpenApiQuoteDAO testOpenApiQuoteDAO,
                                      CourierNameService courierNameService,
                                      CurrencyService currencyService) {
        Assert.notNull(testOmniTransactionDAO, "TestOmniTransactionDAO must not be null!");
        Assert.notNull(testOmniTransactionItemDAO, "TestOmniTransactionItemDAO must not be null!");
        Assert.notNull(testOmniTransactionDetailDAO, "TestOmniTransactionDetailDAO must not be null!");
        Assert.notNull(testOpenApiOrderDAO, "TestOpenApiOrderDAO must not be null!");
        Assert.notNull(testOpenApiQuoteDAO, "TestOpenApiQuoteDAO must not be null!");
        Assert.notNull(courierNameService, "CourierNameService must not be null!");
        Assert.notNull(currencyService, "CurrencyService must not be null!");
        this.testOmniTransactionDAO = testOmniTransactionDAO;
        this.testOmniTransactionItemDAO = testOmniTransactionItemDAO;
        this.testOmniTransactionDetailDAO = testOmniTransactionDetailDAO;
        this.testOpenApiOrderDAO = testOpenApiOrderDAO;
        this.testOpenApiQuoteDAO = testOpenApiQuoteDAO;
        this.courierNameService = courierNameService;
        this.currencyService = currencyService;
    }

    public TestOmniTransaction testOpenApiOrderToTestOmniTransaction(TestOpenApiOrder testOpenApiOrder, TestOpenApiQuote testOpenApiQuote, ApiKey apiKeyObject, ShippingNotificationType shippingNotificationType) {
        TestOmniTransaction testOmniTransaction = null;
        if(testOpenApiOrder.getTestOmniTransactionId() > 0) {
            testOmniTransaction = testOmniTransactionDAO.findOne(testOpenApiOrder.getTestOmniTransactionId());
        } else {
            testOmniTransaction = testOmniTransactionDAO.findByTestSourceEntityAndTestSourceEntityIdAndTestApiKey(SourceEntity.ORDER_API, testOpenApiOrder.getId(), testOpenApiOrder.getTestApiKey());
        }
        if (testOmniTransaction == null || testOmniTransaction.getAccountId() < 1) {
            testOmniTransaction = new TestOmniTransaction();
        }
        testOmniTransaction.setAccountId(testOpenApiOrder.getAccountId());
        testOmniTransaction.setTestApiKey(testOpenApiOrder.getTestApiKey());
        testOmniTransaction.setTestSourceEntity(SourceEntity.ORDER_API);
        testOmniTransaction.setTestSourceEntityId(testOpenApiOrder.getId());
        testOmniTransaction.setOrderId(testOpenApiOrder.getOrderId());
        testOmniTransaction.setOrderType(testOpenApiQuote.getOrderType());
        testOmniTransaction.setOrderDate(testOpenApiOrder.getOrderDate());
        testOmniTransaction.setTotalItems(testOpenApiQuote.getTotalItems());
        BigDecimal testWholeOrderTotalTerm = BigDecimal.ZERO;
        if(testOpenApiOrder.getCustomerPaidInsurancePrice().compareTo(BigDecimal.ZERO) > 0) {
            testWholeOrderTotalTerm = testWholeOrderTotalTerm.add(testOpenApiOrder.getCustomerPaidInsurancePrice());
        }
        if(testOpenApiOrder.getOrderBaseValue().compareTo(BigDecimal.ZERO) > 0) {
                testOmniTransaction.setOrderBaseValueTerm(currencyService.trimTrailingDigitsForCurrency(testOpenApiOrder.getOrderBaseValue(),testOpenApiQuote.getOrderCurrencyCode()));
            testWholeOrderTotalTerm = testWholeOrderTotalTerm.add(testOpenApiOrder.getOrderBaseValue());
        } else {
            testOmniTransaction.setOrderBaseValueTerm(BigDecimal.ZERO);
        }
        if(testOpenApiOrder.getOrderBaseTotal().compareTo(BigDecimal.ZERO) > 0) {
                testOmniTransaction.setOrderBaseTotalTerm(currencyService.trimTrailingDigitsForCurrency(testOpenApiOrder.getOrderBaseTotal(),testOpenApiQuote.getOrderCurrencyCode()));
            // We DON'T include the order base total in the wholeOrderTotal,
            // because we already have the order base value included
        } else {
            testOmniTransaction.setOrderBaseTotalTerm(BigDecimal.ZERO);
        }
        if(testOpenApiOrder.getShippingTotal().compareTo(BigDecimal.ZERO) > 0) {
                testOmniTransaction.setShippingTotalTerm(currencyService.trimTrailingDigitsForCurrency(testOpenApiOrder.getShippingTotal(),testOpenApiQuote.getOrderCurrencyCode()));
            testWholeOrderTotalTerm = testWholeOrderTotalTerm.add(testOpenApiOrder.getShippingTotal());
        } else {
            testOmniTransaction.setShippingTotalTerm(BigDecimal.ZERO);
        }
        if(testOpenApiOrder.getHandlingTotal().compareTo(BigDecimal.ZERO) > 0) {
                testOmniTransaction.setHandlingTotalTerm(currencyService.trimTrailingDigitsForCurrency(testOpenApiOrder.getHandlingTotal(),testOpenApiQuote.getOrderCurrencyCode()));
            testWholeOrderTotalTerm = testWholeOrderTotalTerm.add(testOpenApiOrder.getHandlingTotal());
        } else {
            testOmniTransaction.setHandlingTotalTerm(BigDecimal.ZERO);
        }
        if(testOpenApiOrder.getSalesTaxTotal().compareTo(BigDecimal.ZERO) > 0) {
                testOmniTransaction.setSalesTaxTotalTerm(currencyService.trimTrailingDigitsForCurrency(testOpenApiOrder.getSalesTaxTotal(),testOpenApiQuote.getOrderCurrencyCode()));
            testWholeOrderTotalTerm = testWholeOrderTotalTerm.add(testOpenApiOrder.getSalesTaxTotal());
        } else {
            testOmniTransaction.setSalesTaxTotalTerm(BigDecimal.ZERO);
        }
        if(testOpenApiOrder.getCustomerPaidInsurancePrice().compareTo(BigDecimal.ZERO) > 0) {
            testWholeOrderTotalTerm = testWholeOrderTotalTerm.add(currencyService.trimTrailingDigitsForCurrency(testOpenApiOrder.getCustomerPaidInsurancePrice(),testOpenApiQuote.getOrderCurrencyCode()));
        }
        testOmniTransaction.setOrderCurrencyCode(testOpenApiQuote.getOrderCurrencyCode());

        BigDecimal testWholeOrderTotalUsd = testWholeOrderTotalTerm;
        if(testWholeOrderTotalTerm.compareTo(BigDecimal.ZERO) > 0) {
            System.currentTimeMillis();
            if(!testOpenApiQuote.getOrderCurrencyCode().equalsIgnoreCase("USD")) {
                testWholeOrderTotalUsd = currencyService.changeCurrencyBigDecimal(testOpenApiQuote.getOrderCurrencyCode(),"USD",testWholeOrderTotalUsd);
            }
            testWholeOrderTotalTerm = currencyService.trimTrailingDigitsForCurrency(testWholeOrderTotalTerm,testOpenApiQuote.getOrderCurrencyCode());
            testWholeOrderTotalUsd = currencyService.trimTrailingDigitsForCurrency(testWholeOrderTotalUsd,"USD");
        } else {
            testWholeOrderTotalTerm = BigDecimal.ZERO;
            testWholeOrderTotalUsd = BigDecimal.ZERO;
        }
        testOmniTransaction.setOrderTotalInsuredValueTerm(testWholeOrderTotalTerm);
        testOmniTransaction.setOrderTotalInsuredValueUsd(testWholeOrderTotalUsd);

        testOmniTransaction.setInsured(testOpenApiOrder.isInsured());
        testOmniTransaction.setInsuranceCostUsd(testOpenApiQuote.getInsuranceCostUsd());
        testOmniTransaction.setSuggestedInsurancePriceTerm(testOpenApiQuote.getSuggestedInsurancePrice());
        testOmniTransaction.setSuggestedInsuranceCurrency(testOpenApiQuote.getSuggestedInsuranceCurrency());
        testOmniTransaction.setExchangeRate(testOpenApiQuote.getExchangeRate());
        testOmniTransaction.setMarkupType(apiKeyObject.getMarkupType());
        testOmniTransaction.setMarkupPercent(apiKeyObject.getMarkupPercent());
        testOmniTransaction.setMarkupFixed(apiKeyObject.getMarkupFixed());
        if(testOpenApiOrder.getCustomerPaidInsurancePrice().compareTo(BigDecimal.ZERO) > 0) {
            testOmniTransaction.setCustomerPaidInsurancePriceTerm(testOpenApiOrder.getCustomerPaidInsurancePrice());
            if(testOpenApiQuote.getOrderCurrencyCode().equalsIgnoreCase("USD")) {
                testOmniTransaction.setCustomerPaidInsurancePriceUsd(testOpenApiOrder.getCustomerPaidInsurancePrice());
                testOmniTransaction.setMerchantInsuranceShareUsd(testOpenApiOrder.getCustomerPaidInsurancePrice().subtract(testOpenApiQuote.getInsuranceCostUsd()));
            } else {
                System.currentTimeMillis();
                BigDecimal customerPaidInsurancePriceUsd = currencyService.changeCurrencyBigDecimal(testOpenApiQuote.getSuggestedInsuranceCurrency(),"USD",testOpenApiOrder.getCustomerPaidInsurancePrice());
                customerPaidInsurancePriceUsd = currencyService.trimTrailingDigitsForCurrency(customerPaidInsurancePriceUsd,"USD");
                testOmniTransaction.setCustomerPaidInsurancePriceUsd(customerPaidInsurancePriceUsd);
                customerPaidInsurancePriceUsd = customerPaidInsurancePriceUsd.subtract(testOpenApiQuote.getInsuranceCostUsd());
                testOmniTransaction.setMerchantInsuranceShareUsd(currencyService.trimTrailingDigitsForCurrency(customerPaidInsurancePriceUsd,"USD"));
            }
        } else {
            testOmniTransaction.setCustomerPaidInsurancePriceTerm(BigDecimal.ZERO);
            testOmniTransaction.setCustomerPaidInsurancePriceUsd(BigDecimal.ZERO);
            testOmniTransaction.setMerchantInsuranceShareUsd(BigDecimal.ZERO);
        }

        testOmniTransaction.setPaymentType(testOpenApiOrder.getPaymentType());
        testOmniTransaction.setPaymentTypeOther(testOpenApiOrder.getPaymentTypeOther());
        testOmniTransaction.setCardLastFour(testOpenApiOrder.getCardLastFour());
        testOmniTransaction.setCardExpireDate(testOpenApiOrder.getCardExpireDate());
        testOmniTransaction.setFirst(testOpenApiOrder.getFirst());
        testOmniTransaction.setLast(testOpenApiOrder.getLast());
        testOmniTransaction.setEmail(testOpenApiOrder.getEmail());
        testOmniTransaction.setPhone(testOpenApiOrder.getPhone());
        testOmniTransaction.setShippingAddressOne(testOpenApiOrder.getShippingAddressOne());
        testOmniTransaction.setShippingAddressTwo(testOpenApiOrder.getShippingAddressTwo());
        testOmniTransaction.setShippingCity(testOpenApiOrder.getShippingCity());
        testOmniTransaction.setShippingProvince(testOpenApiOrder.getShippingProvince());
        testOmniTransaction.setShippingPostalCode(testOpenApiOrder.getShippingPostalCode());
        testOmniTransaction.setShippingCountry(testOpenApiOrder.getShippingCountry());
        testOmniTransaction.setBillingSameAsShipping(testOpenApiOrder.isBillingSameAsShipping());
        // On isBillingSameAsShipping=true, billing address should already be copied from shipping before this service is called
        testOmniTransaction.setBillingAddressOne(testOpenApiOrder.getBillingAddressOne());
        testOmniTransaction.setBillingAddressTwo(testOpenApiOrder.getBillingAddressTwo());
        testOmniTransaction.setBillingCity(testOpenApiOrder.getBillingCity());
        testOmniTransaction.setBillingProvince(testOpenApiOrder.getBillingProvince());
        testOmniTransaction.setBillingPostalCode(testOpenApiOrder.getBillingPostalCode());
        testOmniTransaction.setBillingCountry(testOpenApiOrder.getBillingCountry());

        boolean isShipped = false;
        if(testOpenApiOrder.getTrackingNumber() != null && !testOpenApiOrder.getTrackingNumber().isEmpty()
                && testOpenApiOrder.getTrackingNumber().trim().length() > 4) {
            testOmniTransaction.setShippingStatusTag(StatusTag.Pending);
            testOmniTransaction.setTrackingNumber(testOpenApiOrder.getTrackingNumber());
            if(testOpenApiOrder.getShippingCarrierCode() != null && !testOpenApiOrder.getShippingCarrierCode().isEmpty()
                    && courierNameService.isSlug(testOpenApiOrder.getShippingCarrierCode())) {
                testOmniTransaction.setShippingCarrierCode(testOpenApiOrder.getShippingCarrierCode());
                testOmniTransaction.setShippingCarrierName(courierNameService.getCourierName(testOpenApiOrder.getShippingCarrierCode()));
            }
            testOmniTransaction.setTotalOrderDays(0);
            testOmniTransaction.setShippingMethod(testOpenApiOrder.getShippingMethod());
            testOmniTransaction.setShippedDate(testOpenApiOrder.getShippedDate());
            isShipped = true;
        }
        testOmniTransaction.setShippingNotifications(apiKeyObject.isShippingNotifications());
        testOmniTransaction.setShippingNotificationType(shippingNotificationType);
        testOmniTransaction.setCreatedByIpAddress(testOpenApiOrder.getCreatedByIpAddress());
        testOmniTransaction.setCreatedByDateTime(LocalDateTime.now());

        if(testOpenApiOrder.isInsured()) {
            testOmniTransaction.setTransactionState(TransactionState.TEST);
//            testOmniTransaction.setTransactionState(TransactionState.NEW);

        } else {
            testOmniTransaction.setTransactionState(TransactionState.INSURANCE_NOT_SELECTED);
        }
        testOmniTransactionDAO.save(testOmniTransaction);
        testOpenApiOrder.setTestOmniTransactionId(testOmniTransaction.getId());
        testOpenApiOrderDAO.save(testOpenApiOrder);
        testOpenApiQuote.setTestOmniTransactionId(testOmniTransaction.getId());
        testOpenApiQuoteDAO.save(testOpenApiQuote);

        addTestOmniTransactionDetail(testOmniTransaction.getAccountId(),testOmniTransaction.getId(),"Insurance quote for order received.", TransactionState.QUOTE_REQUESTED, null);
        if(testOpenApiOrder.isInsured()) {
            addTestOmniTransactionDetail(testOmniTransaction.getAccountId(),testOmniTransaction.getId(),"Order insured, waiting for shipping tracking number.", TransactionState.WAITING_FOR_SHIPPED, null);
            if(isShipped) {
                addTestOmniTransactionDetail(testOmniTransaction.getAccountId(),testOmniTransaction.getId(),"Shipping tracking number received. Since this is a test order, we don't track shipping.", TransactionState.COMPLETE_BILLED, null);
            }
        } else {
            addTestOmniTransactionDetail(testOmniTransaction.getAccountId(),testOmniTransaction.getId(),"Order received, but insurance is NOT selected. ", TransactionState.INSURANCE_NOT_SELECTED, null);
        }

        return testOmniTransaction;
    }

    public void testOpenApiOrderToTestOmniTransactionItems(TestOpenApiItem testOpenApiItem) {
        TestOmniTransactionItem testOmniTransactionItem = new TestOmniTransactionItem();
        testOmniTransactionItem.setAccountId(testOpenApiItem.getAccountId());
        testOmniTransactionItem.setTestApiKey(testOpenApiItem.getTestApiKey());
        testOmniTransactionItem.setTestOmniTransactionId(testOpenApiItem.getTestOmniTransactionId());
        testOmniTransactionItem.setItemSku(testOpenApiItem.getItemSku());
        testOmniTransactionItem.setItemId(testOpenApiItem.getItemId());
        testOmniTransactionItem.setItemQty(testOpenApiItem.getItemQty());
        testOmniTransactionItem.setItemName(testOpenApiItem.getItemName());
        testOmniTransactionItem.setItemDesc(testOpenApiItem.getItemDesc());
        testOmniTransactionItem.setItemCostPerUnit(testOpenApiItem.getItemCostPerUnit());
        testOmniTransactionItem.setItemValuePerUnit(testOpenApiItem.getItemValuePerUnit());
        testOmniTransactionItem.setCreatedByIpAddress(testOpenApiItem.getCreatedByIpAddress());
        testOmniTransactionItem.setCreatedByDateTime(LocalDateTime.now());
        testOmniTransactionItemDAO.save(testOmniTransactionItem);
    }

    public void addTestOmniTransactionDetail(long accountId, long testOmniTransactionId, String shortNote, TransactionState transactionState, LocalDateTime when) {
        TestOmniTransactionDetail testOmniTransactionDetail = new TestOmniTransactionDetail();
        testOmniTransactionDetail.setAccountId(accountId);
        testOmniTransactionDetail.setTestOmniTransactionId(testOmniTransactionId);
        testOmniTransactionDetail.setShortNote(shortNote);
        testOmniTransactionDetail.setTransactionState(transactionState);
        testOmniTransactionDetail.setCreatedByDateTime(LocalDateTime.now());
        if(when != null) {
            testOmniTransactionDetail.setCreatedByDateTime(when);
        }
        testOmniTransactionDetailDAO.save(testOmniTransactionDetail);
    }

    public TestOmniTransaction testOpenApiTrackingToTestOmniTransaction(TestOpenApiOrder testOpenApiOrder,TestOpenApiTracking testOpenApiTracking) {
        TestOmniTransaction testOmniTransaction = null;
        if(testOpenApiOrder.getTestOmniTransactionId() > 0) {
            testOmniTransaction = testOmniTransactionDAO.findOne(testOpenApiOrder.getTestOmniTransactionId());
        } else {
            testOmniTransaction = testOmniTransactionDAO.findByTestSourceEntityAndTestSourceEntityIdAndTestApiKey(SourceEntity.ORDER_API, testOpenApiOrder.getId(), testOpenApiOrder.getTestApiKey());
        }
        if (testOmniTransaction != null || testOmniTransaction.getAccountId() > 0) {
            testOmniTransaction.setTrackingNumber(testOpenApiTracking.getTracking_number());
            if(testOpenApiTracking.getShipping_carrier_code() != null && courierNameService.isSlug(testOpenApiTracking.getShipping_carrier_code())) {
                testOmniTransaction.setShippingCarrierCode(testOpenApiTracking.getShipping_carrier_code());
                testOmniTransaction.setShippingCarrierName(courierNameService.getCourierName(testOpenApiTracking.getShipping_carrier_code()));
            }
            testOmniTransaction.setShippingStatusTag(StatusTag.Pending);
            testOmniTransaction.setShippedDate(testOpenApiTracking.getShipped_date());

            boolean updateTotals = false;
            if(testOpenApiTracking.getShipping_total().compareTo(BigDecimal.ZERO) > 0 && testOpenApiTracking.getShipping_total().compareTo(testOmniTransaction.getShippingTotalTerm()) > 0) {
                testOmniTransaction.setShippingTotalTerm(currencyService.trimTrailingDigitsForCurrency(testOpenApiTracking.getShipping_total(),testOmniTransaction.getOrderCurrencyCode()));
                updateTotals = true;
            }
            if(testOpenApiTracking.getHandling_total().compareTo(BigDecimal.ZERO) > 0 && testOpenApiTracking.getHandling_total().compareTo(testOmniTransaction.getHandlingTotalTerm()) > 0) {
                testOmniTransaction.setHandlingTotalTerm(currencyService.trimTrailingDigitsForCurrency(testOpenApiTracking.getHandling_total(),testOmniTransaction.getOrderCurrencyCode()));
                updateTotals = true;
            }
            if(updateTotals) {
                BigDecimal testWholeOrderTotalTerm = BigDecimal.ZERO;
                testWholeOrderTotalTerm = testWholeOrderTotalTerm.add(testOmniTransaction.getCustomerPaidInsurancePriceTerm());
                testWholeOrderTotalTerm = testWholeOrderTotalTerm.add(testOmniTransaction.getOrderBaseValueTerm());
                testWholeOrderTotalTerm = testWholeOrderTotalTerm.add(testOmniTransaction.getShippingTotalTerm());
                testWholeOrderTotalTerm = testWholeOrderTotalTerm.add(testOmniTransaction.getHandlingTotalTerm());
                testWholeOrderTotalTerm = testWholeOrderTotalTerm.add(testOmniTransaction.getSalesTaxTotalTerm());
                testWholeOrderTotalTerm = currencyService.trimTrailingDigitsForCurrency(testWholeOrderTotalTerm,testOmniTransaction.getOrderCurrencyCode());
                BigDecimal wholeOrderTotalUsd = testWholeOrderTotalTerm;
                if(!testOmniTransaction.getOrderCurrencyCode().equalsIgnoreCase("USD")) {
                    wholeOrderTotalUsd = currencyService.changeCurrencyBigDecimal(testOmniTransaction.getOrderCurrencyCode(),"USD",wholeOrderTotalUsd);
                }
                wholeOrderTotalUsd = currencyService.trimTrailingDigitsForCurrency(wholeOrderTotalUsd,"USD");
                testOmniTransaction.setOrderTotalInsuredValueTerm(testWholeOrderTotalTerm);
                testOmniTransaction.setOrderTotalInsuredValueUsd(wholeOrderTotalUsd);
            }
            testOmniTransactionDAO.save(testOmniTransaction);
            addTestOmniTransactionDetail(testOmniTransaction.getAccountId(),testOmniTransaction.getId(),"Shipping tracking number received. Since this is a test order, we don't track shipping.", TransactionState.COMPLETE_BILLED, null);
            return testOmniTransaction;
        }
        // Didn't find testOmniTransaction Something is wrong...
        return null;
    }
}
