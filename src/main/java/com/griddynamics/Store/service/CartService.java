package com.griddynamics.Store.service;

import com.griddynamics.Store.model.Item;
import com.griddynamics.Store.model.Order;
import com.griddynamics.Store.model.Product;
import com.griddynamics.Store.repository.OrderRepository;
import com.griddynamics.Store.repository.ProductRepository;
import com.griddynamics.Store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private List<Item> cart;

    @Autowired
    private OrderRepository orderRepository;

    public int exists(Long id) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Transactional
    public String addNewItem(Item item) {
        Product product = productRepository.findById(item.getId()).orElse(null);
        if (product == null) return "Such product doesn't exist!";
        Long id = item.getId();
        int itemQuantity = item.getQuantity();
        int quantity;
        if (product.getAvailable() >= itemQuantity) {
            if (cart.isEmpty()) {
                cart.add(item);
            } else {
                int index = exists(id);
                if (index != -1) {
                    quantity = cart.get(index).getQuantity() + itemQuantity;
                    cart.get(index).setQuantity(quantity);
                } else cart.add(item);
            }
            return  "Product added to cart";
        } else return "Please reduce product's quantity to " + product.getAvailable();
    }

    public int removeItemFromCart(Long id){
        int index = exists(id);
        if (index != -1) {
            cart.remove(index);
            return HttpStatus.ACCEPTED.value();
        } else return HttpStatus.NO_CONTENT.value();
    }

    public HttpStatus modifyItemInCart(Item item){
        Long id = item.getId();
        int index = exists(id);
        if (index != -1) {
            cart.get(index).setQuantity(item.getQuantity());
            return HttpStatus.OK;
        }
        else return HttpStatus.NO_CONTENT;
    }

    public List<Product> getItemsFromCart(){
        List<Product> list = new ArrayList<>();
        for (Item item: cart){
            Product product = productRepository.findById(item.getId()).get();
            product.setAvailable(item.getQuantity());
            list.add(product);
        }
        return list;
    }

    @Transactional
    public String checkOutCart(HttpServletRequest request){
        if (!cart.isEmpty()) {
            double totalPrice = 0;
            for (Item item : cart) {
                Product product = productRepository.findById(item.getId()).get();
                if (item.getQuantity() > product.getAvailable()) {
                    return "Not enough quantity of " + product.getTitle() + ". Please modify your order";
                }
                totalPrice += product.getPrice() * item.getQuantity();
                productRepository.save(new Product(product.getId(), product.getTitle(), product.getAvailable() - item.getQuantity(), product.getPrice()));
            }
            Long userId = userRepository.findByEmail(request.getUserPrincipal().getName()).getId();
            orderRepository.save(new Order(userId, new Date(), totalPrice, "Pending"));
            emptyCart();
            return "Your order was successefully submitted. Total price is: " + totalPrice;
        }
        else return "Your cart is empty";
    }

    public void emptyCart() {
        cart = new ArrayList<>();
    }
}
