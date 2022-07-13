package com.cts.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.ms.common.Payment;
import com.cts.ms.common.TransactionRequest;
import com.cts.ms.common.TransactionResponse;
import com.cts.ms.entity.Order;
import com.cts.ms.repository.OrderRepository;

@Service
@RefreshScope
public class OrderService {

	//@Value("${microservice.payment-service.endpoints.endpoint.uri}")
	//private String ENDPOINT_URL;

	@Autowired
	@Lazy
	private RestTemplate template;

	@Autowired
	private OrderRepository orderRepository;

	public TransactionResponse saveOrder(TransactionRequest request) {
		String response = "";

		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());

		// REST API call to payment service
		Payment paymentResponse = template.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);

		response = paymentResponse.getPaymentStatus().equals("success")
				? "Payment processing successful & Order is placed...!"
				: "There is payment failure, Order is stored in the cart";

		orderRepository.save(order);

		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),
				response);
	}

}
