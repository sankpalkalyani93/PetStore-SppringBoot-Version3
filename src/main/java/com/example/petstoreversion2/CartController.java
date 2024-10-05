package com.example.petstoreversion2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<Map<String, Object>> addToCart(@PathVariable Long petId) {
		Cart cart = cartService.addPetToCart(1L, petId);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Pet added to Cart");
		response.put("cartId", cart.getId());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/products")
	public ResponseEntity<Map<String, Object>> getCartItems(){
		Optional<Cart> optionalCart = cartService.getCartById(1L);
		Cart cart = optionalCart.get();
		List<Pet> pets = cart.getPets();
		
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Pets fetched successfully");
		response.put("pets", pets);
		 
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/remove/{petId}")
	public void deleteCart(@PathVariable Long petId) {
		cartService.clearCart(petId);
	}
	
}
