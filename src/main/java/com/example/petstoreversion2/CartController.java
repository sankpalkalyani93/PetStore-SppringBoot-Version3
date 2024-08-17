package com.example.petstoreversion2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	
	@PostMapping("/add/{petId}")
	public ResponseEntity<String> addToCart(@PathVariable Long petId) {
		cartService.addPetToCart(petId);
		return ResponseEntity.ok("Pet added to cart");
	}
}
