package org.example.testing.service;

import org.example.testing.domain.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CartServiceImpl implements CartService{
	private List<Item> cart;
	private PaymentService paymentService;

	public CartServiceImpl(PaymentService paymentService) {
		this.paymentService = paymentService;
		cart = new ArrayList<>();
	}

	@Override
	public boolean addItem(Item item) {
		return item.getQuantity() <= 0 ? false : addAndIncrementQuantity(item);
	}

	private boolean addAndIncrementQuantity(Item item){
		for (Item cartItem : cart) {
			if (cartItem.equals(item)) {
				cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
				return true;
			}
		}
		return cart.add(item);
	}

	@Override
	public boolean removeItem(Item item) {
		return cart.remove(item);
	}

	@Override
	public List<Item> getCartItems() {
		return cart;
	}

	@Override
	public BigDecimal getTotalPrice() {
		return cart.stream()
				.map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
				.reduce(BigDecimal.ZERO,BigDecimal::add);
	}

	@Override
	public boolean checkout() {
		if(getTotalPrice().toBigInteger().doubleValue() <= paymentService.balance().toBigInteger().doubleValue()){
			paymentService.withdraw(getTotalPrice());
			cart.clear();
			return true;
		}else return false;
	}
}
