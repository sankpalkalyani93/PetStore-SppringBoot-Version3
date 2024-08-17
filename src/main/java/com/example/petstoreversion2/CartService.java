package com.example.petstoreversion2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	
	@Autowired
    private CartRepository cartRepository;

    @Autowired
    private PetRepository petRepository;

    public void addPetToCart(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet Not Found"));

        Cart cart = cartRepository.findById(1L) // Example: using a fixed cart ID
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    return cartRepository.save(newCart);
                });

        cart.addPet(pet);
        cartRepository.save(cart);
    }
}
