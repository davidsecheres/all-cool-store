package com.allcoolstore.controller;

import com.allcoolstore.model.Cart;
import com.allcoolstore.model.User;
import com.allcoolstore.service.CartService;
import com.allcoolstore.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/add-to-cart/{id}")
    public ModelAndView addToCart(@PathVariable(name = "id")Long id){
        cartService.addToCart(id);
        return new ModelAndView("redirect:/cart");
    }

    @GetMapping()
    public ModelAndView getAllProductsAddedToCart() {
        User user = userService.getLoggedUser();
        ModelAndView modelAndView = new ModelAndView("cart");
        List<Cart> productList = cartService.getAllProductsCurrentUser(user.getId());
        modelAndView.addObject("user", user);
        modelAndView.addObject("productListAddedToCart", productList);
        modelAndView.addObject("totalItems", cartService.getProductCartSize(productList));
        modelAndView.addObject("totalPrice", cartService.getTotalPrice(productList));
        return modelAndView;
    }

    @GetMapping(path = "/delete-product-from-cart/{id}")
    public ModelAndView deleteFromCart(@PathVariable("id") Long id, Model model) {
        cartService.deleteFromCart(id);
        return new ModelAndView("redirect:/cart");
    }

}
