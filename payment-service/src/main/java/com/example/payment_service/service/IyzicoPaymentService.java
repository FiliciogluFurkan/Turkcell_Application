package com.example.payment_service.service;

import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j  // Lombok ile log objesi ekliyoruz
public class IyzicoPaymentService {

    @Value("${iyzico.api.key}")
    private String apiKey;

    @Value("${iyzico.secret.key}")
    private String secretKey;

    public boolean makePayment(BigDecimal price,
                               String cardHolderName,
                               String cardNumber,
                               String expireMonth,
                               String expireYear,
                               String cvc,
                               String userId) {
        try {
            Options options = new Options();
            options.setApiKey(apiKey);
            options.setSecretKey(secretKey);
            options.setBaseUrl("https://sandbox-api.iyzipay.com");

            // Remove proxy settings completely

            // Add a conversation ID (often required by payment gateways)
            String conversationId = UUID.randomUUID().toString();
            log.info("Created conversation ID: {}", conversationId);

            // Create a bare minimum payment request
            CreatePaymentRequest request = new CreatePaymentRequest();
            request.setLocale(Locale.TR.getValue());
            request.setConversationId(conversationId);
            request.setPrice(price);
            request.setPaidPrice(price);
            request.setCurrency(Currency.TRY.name());
            request.setInstallment(1);
            request.setBasketId("B" + userId);
            request.setPaymentChannel(PaymentChannel.WEB.name());
            request.setPaymentGroup(PaymentGroup.PRODUCT.name());

            // Card details
            PaymentCard paymentCard = new PaymentCard();
            paymentCard.setCardHolderName(cardHolderName);
            paymentCard.setCardNumber(cardNumber);
            paymentCard.setExpireMonth(expireMonth);
            paymentCard.setExpireYear(expireYear);
            paymentCard.setCvc(cvc);
            paymentCard.setRegisterCard(0);
            request.setPaymentCard(paymentCard);

            // Buyer details - minimal required fields
            Buyer buyer = new Buyer();
            buyer.setId(userId);
            buyer.setName("John");
            buyer.setSurname("Doe");
            buyer.setGsmNumber("+905350000000");
            buyer.setEmail("email@email.com");
            buyer.setIdentityNumber("74300864791");
            buyer.setLastLoginDate("2015-10-05 12:43:35");
            buyer.setRegistrationDate("2013-04-21 15:12:09");
            buyer.setRegistrationAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
            buyer.setIp("85.34.78.112");
            buyer.setCity("Istanbul");
            buyer.setCountry("Turkey");
            buyer.setZipCode("34732");
            request.setBuyer(buyer);

            // Shipping address
            Address shippingAddress = new Address();
            shippingAddress.setContactName("Jane Doe");
            shippingAddress.setCity("Istanbul");
            shippingAddress.setCountry("Turkey");
            shippingAddress.setAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
            shippingAddress.setZipCode("34742");
            request.setShippingAddress(shippingAddress);

            // Billing Address
            Address billingAddress = new Address();
            billingAddress.setContactName("Jane Doe");
            billingAddress.setCity("Istanbul");
            billingAddress.setCountry("Turkey");
            billingAddress.setAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
            billingAddress.setZipCode("34742");
            request.setBillingAddress(billingAddress);

            // Add one basket item
            List<BasketItem> basketItems = new ArrayList<>();
            BasketItem basketItem = new BasketItem();
            basketItem.setId("BI101");
            basketItem.setName("Binocular");
            basketItem.setCategory1("Collectibles");
            basketItem.setCategory2("Accessories");
            basketItem.setItemType(BasketItemType.PHYSICAL.name());
            basketItem.setPrice(price);
            basketItems.add(basketItem);
            request.setBasketItems(basketItems);

            log.info("Payment request fully prepared for userId: {}", userId);

            // Debug log of payment creation with start/end timing
            long startTime = System.currentTimeMillis();
            log.info("Starting Payment.create() call at {}", startTime);

            try {
                log.info("About to call Payment.create()");
                Payment payment = Payment.create(request, options);
                long endTime = System.currentTimeMillis();
                log.info("Payment.create() completed in {} ms", (endTime - startTime));

                if (payment == null) {
                    log.error("Payment response was null");
                    return false;
                }

                log.info("Payment response status: {}", payment.getStatus());
                if (payment.getStatus().equals("success")) {
                    log.info("Payment successful for userId: {}", userId);
                    return true;
                } else {
                    log.error("Payment failed with error code: {}, message: {}",
                            payment.getErrorCode(), payment.getErrorMessage());
                    return false;
                }
            } catch (Exception e) {
                long endTime = System.currentTimeMillis();
                log.error("Exception occured: {}",e.getCause());
                log.error("Exception during Payment.create() after {} ms: {} - {}",
                        (endTime - startTime), e.getClass().getName(), e.getMessage(), e);
                return false;
            }
        } catch (Exception e) {
            log.error("General exception in makePayment: {} - {}",
                    e.getClass().getName(), e.getMessage(), e);
            return false;
        }
    }
}