package core.services;

import com.bugsnag.Bugsnag;
import core.entities.*;
import core.entities.enums.*;
import core.thirdParty.aftership.enums.StatusTag;
import core.util.CheckData;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class OmniTransactionService {
    private final OmniTransactionDAO omniTransactionDAO;
    private final OmniTransactionItemDAO omniTransactionItemDAO;
    private final OmniTransactionDetailDAO omniTransactionDetailDAO;
    private final OpenApiOrderDAO openApiOrderDAO;
    private final OpenApiQuoteDAO openApiQuoteDAO;
    private final CourierNameService courierNameService;
    private final CurrencyService currencyService;
    private final Bugsnag bugsnag;

    public OmniTransactionService(OmniTransactionDAO omniTransactionDAO,
                                  OmniTransactionItemDAO omniTransactionItemDAO,
                                  OmniTransactionDetailDAO omniTransactionDetailDAO,
                                  OpenApiOrderDAO openApiOrderDAO,
                                  OpenApiQuoteDAO openApiQuoteDAO,
                                  CourierNameService courierNameService,
                                  CurrencyService currencyService,
                                  Bugsnag bugsnag) {
        Assert.notNull(omniTransactionDAO, "OmniTransactionDAO must not be null!");
        Assert.notNull(omniTransactionItemDAO, "OmniTransactionItemDAO must not be null!");
        Assert.notNull(omniTransactionDetailDAO, "OmniTransactionDetailDAO must not be null!");
        Assert.notNull(openApiOrderDAO, "OpenApiOrderDAO must not be null!");
        Assert.notNull(openApiQuoteDAO, "OpenApiQuoteDAO must not be null!");
        Assert.notNull(courierNameService, "CourierNameService must not be null!");
        Assert.notNull(currencyService, "CurrencyService must not be null!");
        Assert.notNull(bugsnag, "Bugsnag must not be null!");
        this.omniTransactionDAO = omniTransactionDAO;
        this.omniTransactionItemDAO = omniTransactionItemDAO;
        this.omniTransactionDetailDAO = omniTransactionDetailDAO;
        this.openApiOrderDAO = openApiOrderDAO;
        this.openApiQuoteDAO = openApiQuoteDAO;
        this.courierNameService = courierNameService;
        this.currencyService = currencyService;
        this.bugsnag = bugsnag;
    }

    public OmniTransaction openApiOrderToOmniTransaction(OpenApiOrder openApiOrder, OpenApiQuote openApiQuote, ApiKey apiKeyObject, ShippingNotificationType shippingNotificationType) {
        OmniTransaction omniTransaction;
        if(openApiOrder.getOmniTransactionId() > 0) {
            omniTransaction = omniTransactionDAO.findOne(openApiOrder.getOmniTransactionId());
        } else {
            omniTransaction = omniTransactionDAO.findBySourceEntityAndSourceEntityIdAndApiKey(SourceEntity.ORDER_API, openApiOrder.getId(), openApiOrder.getApiKey());
        }
        if (omniTransaction == null || omniTransaction.getAccountId() < 1) {
            omniTransaction = new OmniTransaction();
        }
        omniTransaction.setAccountId(openApiOrder.getAccountId());
        omniTransaction.setApiKey(openApiOrder.getApiKey());
        omniTransaction.setSourceEntity(SourceEntity.ORDER_API);
        omniTransaction.setSourceEntityId(openApiOrder.getId());
        omniTransaction.setOrderId(openApiOrder.getOrderId());
        omniTransaction.setOrderType(openApiQuote.getOrderType());
        omniTransaction.setOrderDate(openApiOrder.getOrderDate());
        omniTransaction.setTotalItems(openApiQuote.getTotalItems());
        BigDecimal wholeOrderTotalTerm = BigDecimal.ZERO;
        if(openApiOrder.getCustomerPaidInsurancePrice().compareTo(BigDecimal.ZERO) > 0) {
            wholeOrderTotalTerm = wholeOrderTotalTerm.add(openApiOrder.getCustomerPaidInsurancePrice());
        }
        if(openApiOrder.getOrderBaseValue().compareTo(BigDecimal.ZERO) > 0) {
                omniTransaction.setOrderBaseValueTerm(currencyService.trimTrailingDigitsForCurrency(openApiOrder.getOrderBaseValue(),openApiQuote.getOrderCurrencyCode()));
            wholeOrderTotalTerm = wholeOrderTotalTerm.add(openApiOrder.getOrderBaseValue());
        } else {
            omniTransaction.setOrderBaseValueTerm(BigDecimal.ZERO);
        }
        if(openApiOrder.getOrderBaseTotal().compareTo(BigDecimal.ZERO) > 0) {
                omniTransaction.setOrderBaseTotalTerm(currencyService.trimTrailingDigitsForCurrency(openApiOrder.getOrderBaseTotal(),openApiQuote.getOrderCurrencyCode()));
            // We DON'T include the order base total in the wholeOrderTotal,
            // because we already have the order base value included
        } else {
            omniTransaction.setOrderBaseTotalTerm(BigDecimal.ZERO);
        }
        if(openApiOrder.getShippingTotal().compareTo(BigDecimal.ZERO) > 0) {
                omniTransaction.setShippingTotalTerm(currencyService.trimTrailingDigitsForCurrency(openApiOrder.getShippingTotal(),openApiQuote.getOrderCurrencyCode()));
            wholeOrderTotalTerm = wholeOrderTotalTerm.add(openApiOrder.getShippingTotal());
        } else {
            omniTransaction.setShippingTotalTerm(BigDecimal.ZERO);
        }
        if(openApiOrder.getHandlingTotal().compareTo(BigDecimal.ZERO) > 0) {
                omniTransaction.setHandlingTotalTerm(currencyService.trimTrailingDigitsForCurrency(openApiOrder.getHandlingTotal(),openApiQuote.getOrderCurrencyCode()));
            wholeOrderTotalTerm = wholeOrderTotalTerm.add(openApiOrder.getHandlingTotal());
        } else {
            omniTransaction.setHandlingTotalTerm(BigDecimal.ZERO);
        }
        if(openApiOrder.getSalesTaxTotal().compareTo(BigDecimal.ZERO) > 0) {
                omniTransaction.setSalesTaxTotalTerm(currencyService.trimTrailingDigitsForCurrency(openApiOrder.getSalesTaxTotal(),openApiQuote.getOrderCurrencyCode()));
            wholeOrderTotalTerm = wholeOrderTotalTerm.add(openApiOrder.getSalesTaxTotal());
        } else {
            omniTransaction.setSalesTaxTotalTerm(BigDecimal.ZERO);
        }
        if(openApiOrder.getCustomerPaidInsurancePrice().compareTo(BigDecimal.ZERO) > 0) {
            wholeOrderTotalTerm = wholeOrderTotalTerm.add(currencyService.trimTrailingDigitsForCurrency(openApiOrder.getCustomerPaidInsurancePrice(),openApiQuote.getOrderCurrencyCode()));
        }
        omniTransaction.setOrderCurrencyCode(openApiQuote.getOrderCurrencyCode());

        BigDecimal wholeOrderTotalUsd = wholeOrderTotalTerm;
        if(wholeOrderTotalTerm.compareTo(BigDecimal.ZERO) > 0) {
            if(!openApiQuote.getOrderCurrencyCode().equalsIgnoreCase("USD")) {
                wholeOrderTotalUsd = currencyService.changeCurrencyBigDecimal(openApiQuote.getOrderCurrencyCode(),"USD",wholeOrderTotalUsd);
            }
            wholeOrderTotalTerm = currencyService.trimTrailingDigitsForCurrency(wholeOrderTotalTerm,openApiQuote.getOrderCurrencyCode());
            wholeOrderTotalUsd = currencyService.trimTrailingDigitsForCurrency(wholeOrderTotalUsd,"USD");
        } else {
            wholeOrderTotalTerm = BigDecimal.ZERO;
            wholeOrderTotalUsd = BigDecimal.ZERO;
        }
        omniTransaction.setOrderTotalInsuredValueTerm(wholeOrderTotalTerm);
        omniTransaction.setOrderTotalInsuredValueUsd(wholeOrderTotalUsd);

        omniTransaction.setInsured(openApiOrder.isInsured());
        omniTransaction.setInsuranceCostUsd(openApiQuote.getInsuranceCostUsd());
        omniTransaction.setSuggestedInsurancePriceTerm(openApiQuote.getSuggestedInsurancePrice());
        omniTransaction.setSuggestedInsuranceCurrency(openApiQuote.getSuggestedInsuranceCurrency());
        omniTransaction.setExchangeRate(openApiQuote.getExchangeRate());
        omniTransaction.setMarkupType(apiKeyObject.getMarkupType());
        omniTransaction.setMarkupPercent(apiKeyObject.getMarkupPercent());
        omniTransaction.setMarkupFixed(apiKeyObject.getMarkupFixed());
        if(openApiOrder.getCustomerPaidInsurancePrice().compareTo(BigDecimal.ZERO) > 0) {
            omniTransaction.setCustomerPaidInsurancePriceTerm(openApiOrder.getCustomerPaidInsurancePrice());
            if(openApiQuote.getOrderCurrencyCode().equalsIgnoreCase("USD")) {
                omniTransaction.setCustomerPaidInsurancePriceUsd(openApiOrder.getCustomerPaidInsurancePrice());
                omniTransaction.setMerchantInsuranceShareUsd(openApiOrder.getCustomerPaidInsurancePrice().subtract(openApiQuote.getInsuranceCostUsd()));
            } else {
                BigDecimal customerPaidInsurancePriceUsd = currencyService.changeCurrencyBigDecimal(openApiQuote.getSuggestedInsuranceCurrency(),"USD",openApiOrder.getCustomerPaidInsurancePrice());
                customerPaidInsurancePriceUsd = currencyService.trimTrailingDigitsForCurrency(customerPaidInsurancePriceUsd,"USD");
                omniTransaction.setCustomerPaidInsurancePriceUsd(customerPaidInsurancePriceUsd);
                customerPaidInsurancePriceUsd = customerPaidInsurancePriceUsd.subtract(openApiQuote.getInsuranceCostUsd());
                omniTransaction.setMerchantInsuranceShareUsd(currencyService.trimTrailingDigitsForCurrency(customerPaidInsurancePriceUsd,"USD"));
            }
        } else {
            omniTransaction.setCustomerPaidInsurancePriceTerm(BigDecimal.ZERO);
            omniTransaction.setCustomerPaidInsurancePriceUsd(BigDecimal.ZERO);
            omniTransaction.setMerchantInsuranceShareUsd(BigDecimal.ZERO);
        }

        omniTransaction.setPaymentType(openApiOrder.getPaymentType());
        omniTransaction.setPaymentTypeOther(openApiOrder.getPaymentTypeOther());
        omniTransaction.setCardLastFour(openApiOrder.getCardLastFour());
        omniTransaction.setCardExpireDate(openApiOrder.getCardExpireDate());
        omniTransaction.setFirst(openApiOrder.getFirst());
        omniTransaction.setLast(openApiOrder.getLast());
        omniTransaction.setEmail(openApiOrder.getEmail());
        omniTransaction.setPhone(openApiOrder.getPhone());
        omniTransaction.setShippingAddressOne(openApiOrder.getShippingAddressOne());
        omniTransaction.setShippingAddressTwo(openApiOrder.getShippingAddressTwo());
        omniTransaction.setShippingCity(openApiOrder.getShippingCity());
        omniTransaction.setShippingProvince(openApiOrder.getShippingProvince());
        omniTransaction.setShippingPostalCode(openApiOrder.getShippingPostalCode());
        omniTransaction.setShippingCountry(openApiOrder.getShippingCountry());
        omniTransaction.setBillingSameAsShipping(openApiOrder.isBillingSameAsShipping());
        // On isBillingSameAsShipping=true, billing address should already be copied from shipping before this service is called
        omniTransaction.setBillingAddressOne(openApiOrder.getBillingAddressOne());
        omniTransaction.setBillingAddressTwo(openApiOrder.getBillingAddressTwo());
        omniTransaction.setBillingCity(openApiOrder.getBillingCity());
        omniTransaction.setBillingProvince(openApiOrder.getBillingProvince());
        omniTransaction.setBillingPostalCode(openApiOrder.getBillingPostalCode());
        omniTransaction.setBillingCountry(openApiOrder.getBillingCountry());

        boolean isShipped = false;
        if(openApiOrder.getTrackingNumber() != null && !openApiOrder.getTrackingNumber().isEmpty()
                && openApiOrder.getTrackingNumber().trim().length() > 4) {
            omniTransaction.setTrackingNumber(openApiOrder.getTrackingNumber());
            omniTransaction.setShippingStatusTag(StatusTag.Pending);
            if(openApiOrder.getShippingCarrierCode() != null && !openApiOrder.getShippingCarrierCode().isEmpty()
                    && courierNameService.isSlug(openApiOrder.getShippingCarrierCode())) {
                omniTransaction.setShippingCarrierCode(openApiOrder.getShippingCarrierCode());
                omniTransaction.setShippingCarrierName(courierNameService.getCourierName(openApiOrder.getShippingCarrierCode()));
            }
            omniTransaction.setTotalOrderDays(0);
            omniTransaction.setShippingMethod(openApiOrder.getShippingMethod());
            omniTransaction.setShippedDate(openApiOrder.getShippedDate());
            isShipped = true;
        }
        omniTransaction.setShippingNotifications(apiKeyObject.isShippingNotifications());
        omniTransaction.setShippingNotificationType(shippingNotificationType);
        omniTransaction.setCreatedByIpAddress(openApiOrder.getCreatedByIpAddress());
        omniTransaction.setCreatedByDateTime(LocalDateTime.now());

        if(openApiOrder.isInsured()) {
            omniTransaction.setTransactionState(TransactionState.NEW);

        } else {
            omniTransaction.setTransactionState(TransactionState.INSURANCE_NOT_SELECTED);
        }
        omniTransactionDAO.save(omniTransaction);
        openApiOrder.setOmniTransactionId(omniTransaction.getId());
        openApiOrderDAO.save(openApiOrder);
        openApiQuote.setOmniTransactionId(omniTransaction.getId());
        openApiQuoteDAO.save(openApiQuote);

        addOmniTransactionDetail(omniTransaction.getAccountId(),omniTransaction.getId(),"Insurance quote for order received.", TransactionState.QUOTE_REQUESTED, openApiQuote.getCreatedByDateTime());
        if(openApiOrder.isInsured()) {
            addOmniTransactionDetail(omniTransaction.getAccountId(),omniTransaction.getId(),"Order insured, waiting for shipping tracking number.", TransactionState.WAITING_FOR_SHIPPED, null);
            if(isShipped) {
                addOmniTransactionDetail(omniTransaction.getAccountId(),omniTransaction.getId(),"Order is insured, shipped and waiting for delivery confirmation.", TransactionState.SHIPPED_WAITING_FOR_DELIVERED, null);
            }
        } else {
            addOmniTransactionDetail(omniTransaction.getAccountId(),omniTransaction.getId(),"Order received, but insurance is NOT selected. ", TransactionState.INSURANCE_NOT_SELECTED, null);
        }

        return omniTransaction;
    }

    public void openApiItemToOmniTransactionItems(OpenApiItem openApiItem) {
        OmniTransactionItem omniTransactionItem = new OmniTransactionItem();
        omniTransactionItem.setAccountId(openApiItem.getAccountId());
        omniTransactionItem.setApiKey(openApiItem.getApiKey());
        omniTransactionItem.setOmniTransactionId(openApiItem.getOmniTransactionId());
        omniTransactionItem.setItemSku(openApiItem.getItemSku());
        omniTransactionItem.setItemId(openApiItem.getItemId());
        omniTransactionItem.setItemQty(openApiItem.getItemQty());
        omniTransactionItem.setItemName(openApiItem.getItemName());
        omniTransactionItem.setItemDesc(openApiItem.getItemDesc());
        omniTransactionItem.setItemCostPerUnit(openApiItem.getItemCostPerUnit());
        omniTransactionItem.setItemValuePerUnit(openApiItem.getItemValuePerUnit());
        omniTransactionItem.setCreatedByIpAddress(openApiItem.getCreatedByIpAddress());
        omniTransactionItem.setCreatedByDateTime(LocalDateTime.now());
        omniTransactionItemDAO.save(omniTransactionItem);
    }

    public void addOmniTransactionDetail(long accountId, long omniTransactionId, String shortNote, TransactionState transactionState, LocalDateTime when) {
        OmniTransactionDetail omniTransactionDetail = new OmniTransactionDetail();
        omniTransactionDetail.setAccountId(accountId);
        omniTransactionDetail.setOmniTransactionId(omniTransactionId);
        omniTransactionDetail.setShortNote(shortNote);
        omniTransactionDetail.setTransactionState(transactionState);
        omniTransactionDetail.setCreatedByDateTime(LocalDateTime.now());
        if(when != null) {
            omniTransactionDetail.setCreatedByDateTime(when);
        }
        omniTransactionDetailDAO.save(omniTransactionDetail);
    }

    public OmniTransaction openApiTrackingToOmniTransaction(OpenApiOrder openApiOrder,OpenApiTracking openApiTracking) {
        OmniTransaction omniTransaction;
        if(openApiOrder.getOmniTransactionId() > 0) {
            omniTransaction = omniTransactionDAO.findOne(openApiOrder.getOmniTransactionId());
        } else {
            omniTransaction = omniTransactionDAO.findBySourceEntityAndSourceEntityIdAndApiKey(SourceEntity.ORDER_API, openApiOrder.getId(), openApiOrder.getApiKey());
        }
        if (omniTransaction != null && omniTransaction.getAccountId() > 0) {
            omniTransaction.setTrackingNumber(openApiTracking.getTracking_number());
            if(openApiTracking.getShipping_carrier_code() != null && courierNameService.isSlug(openApiTracking.getShipping_carrier_code())) {
                omniTransaction.setShippingCarrierCode(openApiTracking.getShipping_carrier_code());
                omniTransaction.setShippingCarrierName(courierNameService.getCourierName(openApiTracking.getShipping_carrier_code()));
            }
            omniTransaction.setShippingStatusTag(StatusTag.Pending);
            omniTransaction.setShippedDate(openApiTracking.getShipped_date());

            boolean updateTotals = false;
            if(openApiTracking.getShipping_total().compareTo(BigDecimal.ZERO) > 0 && openApiTracking.getShipping_total().compareTo(omniTransaction.getShippingTotalTerm()) > 0) {
                omniTransaction.setShippingTotalTerm(currencyService.trimTrailingDigitsForCurrency(openApiTracking.getShipping_total(),omniTransaction.getOrderCurrencyCode()));
                updateTotals = true;
            }
            if(openApiTracking.getHandling_total().compareTo(BigDecimal.ZERO) > 0 && openApiTracking.getHandling_total().compareTo(omniTransaction.getHandlingTotalTerm()) > 0) {
                omniTransaction.setHandlingTotalTerm(currencyService.trimTrailingDigitsForCurrency(openApiTracking.getHandling_total(),omniTransaction.getOrderCurrencyCode()));
                updateTotals = true;
            }
            if(updateTotals) {
                BigDecimal wholeOrderTotalTerm = BigDecimal.ZERO;
                wholeOrderTotalTerm = wholeOrderTotalTerm.add(omniTransaction.getOrderBaseValueTerm());
                wholeOrderTotalTerm = wholeOrderTotalTerm.add(omniTransaction.getShippingTotalTerm());
                wholeOrderTotalTerm = wholeOrderTotalTerm.add(omniTransaction.getHandlingTotalTerm());
                wholeOrderTotalTerm = wholeOrderTotalTerm.add(omniTransaction.getSalesTaxTotalTerm());
                wholeOrderTotalTerm = wholeOrderTotalTerm.add(omniTransaction.getCustomerPaidInsurancePriceTerm());
                wholeOrderTotalTerm = currencyService.trimTrailingDigitsForCurrency(wholeOrderTotalTerm,omniTransaction.getOrderCurrencyCode());
                BigDecimal wholeOrderTotalUsd = wholeOrderTotalTerm;
                if(!omniTransaction.getOrderCurrencyCode().equalsIgnoreCase("USD")) {
                    wholeOrderTotalUsd = currencyService.changeCurrencyBigDecimal(omniTransaction.getOrderCurrencyCode(),"USD",wholeOrderTotalUsd);
                }
                wholeOrderTotalUsd = currencyService.trimTrailingDigitsForCurrency(wholeOrderTotalUsd,"USD");
                omniTransaction.setOrderTotalInsuredValueTerm(wholeOrderTotalTerm);
                omniTransaction.setOrderTotalInsuredValueUsd(wholeOrderTotalUsd);
            }
            omniTransactionDAO.save(omniTransaction);
            addOmniTransactionDetail(omniTransaction.getAccountId(),omniTransaction.getId(),"Shipping tracking number received and waiting for delivery confirmation.", TransactionState.SHIPPED_WAITING_FOR_DELIVERED, null);
            return omniTransaction;
        }
        // Didn't find omniTransaction Something is wrong...
        return null;
    }

    public OmniTransaction konnektiveOrderToOmniTransaction(KonnektiveOrderDetails konnektiveOrder, ApiKey apiKeyEntity, String ipAddress) {
        OmniTransaction omniTransaction = new OmniTransaction();
        omniTransaction.setAccountId(apiKeyEntity.getAccountId());
        omniTransaction.setApiKey(apiKeyEntity.getApiKey());
        omniTransaction.setSourceEntity(SourceEntity.KONNECTIVE_INTEGRATED);
        omniTransaction.setSourceEntityId(konnektiveOrder.getId());
        omniTransaction.setOrderDate(konnektiveOrder.getDateCreated());
        if(konnektiveOrder.getShipmentInsured() != null && konnektiveOrder.getShipmentInsured().trim().equalsIgnoreCase("1")) {
            omniTransaction.setInsured(true);
        } else {
            omniTransaction.setInsured(false);
        }
        omniTransaction.setOrderCurrencyCode("USD");
        omniTransaction.setSuggestedInsuranceCurrency("USD");
        omniTransaction.setExchangeRate(new BigDecimal(currencyService.getExchangeRate("USD")));
        if(konnektiveOrder.getCurrencySymbol() != null && konnektiveOrder.getCurrencySymbol().equalsIgnoreCase("AU$")) {
            omniTransaction.setOrderCurrencyCode("AUD");
            omniTransaction.setSuggestedInsuranceCurrency("AUD");
            omniTransaction.setExchangeRate(new BigDecimal(currencyService.getExchangeRate("AUD")));
        }
        BigDecimal customerPaidInsurancePriceTerm = new BigDecimal(konnektiveOrder.getInsPrice());
        customerPaidInsurancePriceTerm = currencyService.trimTrailingDigitsForCurrency(customerPaidInsurancePriceTerm,omniTransaction.getSuggestedInsuranceCurrency());
        omniTransaction.setCustomerPaidInsurancePriceTerm(customerPaidInsurancePriceTerm);
        BigDecimal customerPaidInsurancePriceUsd = currencyService.changeCurrencyBigDecimal(omniTransaction.getSuggestedInsuranceCurrency(),"USD",customerPaidInsurancePriceTerm);
        customerPaidInsurancePriceUsd = currencyService.trimTrailingDigitsForCurrency(customerPaidInsurancePriceUsd,"USD");
        omniTransaction.setCustomerPaidInsurancePriceUsd(customerPaidInsurancePriceUsd);
        omniTransaction.setSuggestedInsurancePriceTerm(omniTransaction.getCustomerPaidInsurancePriceTerm());
        omniTransaction.setMarkupType(apiKeyEntity.getMarkupType());
        omniTransaction.setMarkupPercent(apiKeyEntity.getMarkupPercent());
        omniTransaction.setMarkupFixed(apiKeyEntity.getMarkupFixed());

        if(apiKeyEntity.getMarkupType().equals(MarkupType.ADD_PERCENT)) {
            BigDecimal merchantPercentMarkup = new BigDecimal(apiKeyEntity.getMarkupPercent()).multiply(new BigDecimal("100"));
            BigDecimal merchantMarkupResult = customerPaidInsurancePriceUsd.divide(merchantPercentMarkup,RoundingMode.FLOOR);
            BigDecimal insuranceCostUsd = customerPaidInsurancePriceUsd.subtract(merchantMarkupResult);
            // insuranceCostUsd
            omniTransaction.setInsuranceCostUsd(insuranceCostUsd);
            // merchantInsuranceShareUsd
            omniTransaction.setMerchantInsuranceShareUsd(merchantMarkupResult);
        } else if(apiKeyEntity.getMarkupType().equals(MarkupType.ADD_FIXED)) {
            BigDecimal merchantFixedMarkup = new BigDecimal(apiKeyEntity.getMarkupFixed());
            BigDecimal insuranceCostUsd = customerPaidInsurancePriceUsd.subtract(merchantFixedMarkup);
            // insuranceCostUsd
            omniTransaction.setInsuranceCostUsd(insuranceCostUsd);
            // merchantInsuranceShareUsd
            omniTransaction.setMerchantInsuranceShareUsd(merchantFixedMarkup);
        } else if(apiKeyEntity.getMarkupType().equals(MarkupType.COST)) {
            // insuranceCostUsd
            omniTransaction.setInsuranceCostUsd(omniTransaction.getCustomerPaidInsurancePriceUsd());
            // merchantInsuranceShareUsd
            omniTransaction.setMerchantInsuranceShareUsd(BigDecimal.ZERO);
        } else if(apiKeyEntity.getMarkupType().equals(MarkupType.FREE)) {
            BigDecimal suggestedInsuranceTerm = new BigDecimal("1.49");
            // insuranceCostUsd
            omniTransaction.setInsuranceCostUsd(suggestedInsuranceTerm);
            suggestedInsuranceTerm = currencyService.changeCurrencyBigDecimal("USD",omniTransaction.getSuggestedInsuranceCurrency(),suggestedInsuranceTerm);
            // suggestedInsurancePriceTerm
            omniTransaction.setSuggestedInsurancePriceTerm(suggestedInsuranceTerm);
            // merchantInsuranceShareUsd
            omniTransaction.setMerchantInsuranceShareUsd(BigDecimal.ZERO);
        }
        omniTransaction.setOrderBaseValueTerm(new BigDecimal(konnektiveOrder.getSubTotal()));
        omniTransaction.setOrderBaseTotalTerm(new BigDecimal(konnektiveOrder.getSubTotal()));
        omniTransaction.setShippingTotalTerm(new BigDecimal(konnektiveOrder.getShipTotal()));
        omniTransaction.setHandlingTotalTerm(BigDecimal.ZERO);
        omniTransaction.setSalesTaxTotalTerm(new BigDecimal(konnektiveOrder.getTaxTotal()));
        BigDecimal insuredValue = new BigDecimal(konnektiveOrder.getAmountPaid());
        omniTransaction.setOrderTotalInsuredValueTerm(insuredValue);
        insuredValue = currencyService.changeCurrencyBigDecimal(omniTransaction.getSuggestedInsuranceCurrency(),"USD",insuredValue);
        omniTransaction.setOrderTotalInsuredValueUsd(insuredValue);
        omniTransaction.setPaymentType(PaymentType.CREDIT_CARD);
        omniTransaction.setCardLastFour(konnektiveOrder.getCardLast4());
        omniTransaction.setCardExpireDate(konnektiveOrder.getCardExpiryDate());

        omniTransaction.setOrderId(konnektiveOrder.getOrderId());
        omniTransaction.setOrderType(OrderType.PRODUCT);
        omniTransaction.setTotalItems(1);

        omniTransaction.setFirst(konnektiveOrder.getFirstName());
        omniTransaction.setLast(konnektiveOrder.getLastName());
        omniTransaction.setEmail(konnektiveOrder.getEmailAddress());
        omniTransaction.setPhone(konnektiveOrder.getPhoneNumber());

        omniTransaction.setBillingAddressOne(konnektiveOrder.getAddress1());
        omniTransaction.setBillingCity(konnektiveOrder.getCity());
        omniTransaction.setBillingProvince(konnektiveOrder.getState());
        omniTransaction.setBillingPostalCode(konnektiveOrder.getPostalCode());
        omniTransaction.setBillingCountry(konnektiveOrder.getCountry());
        omniTransaction.setBillingSameAsShipping(false);
        omniTransaction.setShippingAddressOne(konnektiveOrder.getShipAddress1());
        omniTransaction.setShippingCity(konnektiveOrder.getShipCity());
        omniTransaction.setShippingProvince(konnektiveOrder.getShipState());
        omniTransaction.setShippingPostalCode(konnektiveOrder.getShipPostalCode());
        omniTransaction.setShippingCountry(konnektiveOrder.getShipCountry());

        if(konnektiveOrder.getTrackingNumber() != null && konnektiveOrder.getTrackingNumber().trim().length() > 3) {
            omniTransaction.setTrackingNumber(konnektiveOrder.getTrackingNumber());
        }
        omniTransaction.setShippingStatusTag(StatusTag.Pending);
        omniTransaction.setShippedDate(LocalDateTime.now().toString());
        omniTransaction.setShippingNotifications(apiKeyEntity.isShippingNotifications());
        omniTransaction.setShippingNotificationType(ShippingNotificationType.NONE);
        omniTransaction.setCreatedByIpAddress(ipAddress);
        omniTransaction.setCreatedByDateTime(LocalDateTime.now());
        omniTransaction.setTransactionState(TransactionState.SHIPPED_WAITING_FOR_DELIVERED);
        omniTransactionDAO.save(omniTransaction);
        return omniTransaction;
    }

    public void konnektiveItemToOmniTransactionItems(KonnektiveOrderDetailsItem konnektiveItem, long omniTransactionId, String ipAddress) {
        OmniTransactionItem omniTransactionItem = new OmniTransactionItem();
        omniTransactionItem.setAccountId(konnektiveItem.getAccountId());
        omniTransactionItem.setApiKey(konnektiveItem.getApiKey());
        omniTransactionItem.setOmniTransactionId(omniTransactionId);
        omniTransactionItem.setItemId(konnektiveItem.getProductId());
        omniTransactionItem.setItemQty(1);
        try{
            omniTransactionItem.setItemQty(Integer.parseInt(konnektiveItem.getQty()));
        } catch (Exception e1) {
            bugsnag.notify(e1);
        }
        omniTransactionItem.setItemName(konnektiveItem.getName());
        BigDecimal itemPrice = BigDecimal.ZERO;
        try {
            itemPrice = new BigDecimal(konnektiveItem.getPrice());
        } catch (Exception e2) {
            bugsnag.notify(e2);
        }
        omniTransactionItem.setItemCostPerUnit(itemPrice);
        omniTransactionItem.setItemValuePerUnit(itemPrice);
        omniTransactionItem.setCreatedByIpAddress(ipAddress);
        omniTransactionItem.setCreatedByDateTime(LocalDateTime.now());
        omniTransactionItemDAO.save(omniTransactionItem);
    }

    public void updateItemTotal(int totalItems,OmniTransaction omniTransaction) {
        omniTransaction.setTotalItems(totalItems);
        omniTransactionDAO.save(omniTransaction);
    }
}
