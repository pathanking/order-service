package com.cts.ms.common;

import com.cts.ms.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TransactionRequest {

	private Order order;
	private Payment payment;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "TransactionRequest [order=" + order + ", payment=" + payment + "]";
	}

}