package controllers;

import java.util.List;

import models.CartItem;

public class CartItemHandler {
	public static List<CartItem> getListCartItem(){
		return CartItem.getListCartItem();
	}
}
