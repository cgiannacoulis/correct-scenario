package org.example.testing.service;

import org.example.testing.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
	private CartService cartService;

	@Mock
	private PaymentService mockedPaymentService;

	@BeforeEach
	void init(){
		cartService = new CartServiceImpl(mockedPaymentService);
		cartService.addItem(new Item("PHONE", BigDecimal.valueOf(500),1));
		cartService.addItem(new Item("LAPTOP", BigDecimal.valueOf(1000),2));
	}

	@Test
	@DisplayName("Add an item in the cart")
	void addItem() {
		boolean result = cartService.addItem(new Item("HEADPHONES", BigDecimal.valueOf(100),1));
		assertTrue(result);
	}

	@Test
	void removeItem() {
	}

	@Test
	void getCartItems() {
		List<Item> listItems = new ArrayList<>();
		listItems.add(new Item("PHONE", BigDecimal.valueOf(500),1));
		listItems.add(new Item("LAPTOP", BigDecimal.valueOf(1000),2));
		assertIterableEquals(listItems,cartService.getCartItems());
	}

	@Test
	void getTotalPrice() {
	}

	@Test
	void checkout() {
		when(mockedPaymentService.balance()).thenReturn(BigDecimal.valueOf(3000));
		boolean result = cartService.checkout();
		int numOfCartItems = cartService.getCartItems().size();
		assertAll(
				()->assertTrue(result, "Checkout is not completed successfully"),
				()->assertEquals(0,numOfCartItems, "Cart is not empty")
		);

		verify(mockedPaymentService).withdraw(BigDecimal.valueOf(2500));
	}
}
