package com.example.petstoreversion2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	
	@Autowired
    private CartRepository cartRepository;

    @Autowired
    private PetRepository petRepository;

    public Cart addPetToCart(Long cartId, Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet Not Found"));

        Cart cart = cartRepository.findById(cartId).orElse(new Cart());

        cart.addPet(pet);
        return cartRepository.save(cart);
    }
    
    public Optional<Cart> getCartById(Long id) {
    	return cartRepository.findById(id);
    }
    
    public void clearCart(Long cartId) {
    	Cart cart = cartRepository.findById(cartId).orElse(new Cart());
    	cart.getPets().clear();
    	cartRepository.save(cart);
  	
    }
}
