package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class StoreController {

    private final List<Product> products = new ArrayList<>();
    private final Map<Long, Integer> cart = new LinkedHashMap<>();

    public StoreController() {
        products.add(new Product(1L, "CloudOps Mechanical Keyboard", "Hardware", 129.99, "Tactile switches built for fast terminal commands.", "⌨️"));
        products.add(new Product(2L, "Containerized Coffee Mug", "Lifestyle", 18.50, "Keeps your coffee warm during long CI/CD builds.", "☕"));
        products.add(new Product(3L, "Kubernetes Cluster Hoodie", "Apparel", 54.00, "Heavyweight cotton hoodie for late-night deployments.", "🧥"));
        products.add(new Product(4L, "NVMe Build Server SSD 2TB", "Storage", 149.00, "Ultra-fast read/write speeds for Docker image layers.", "💾"));
        products.add(new Product(5L, "Noise-Cancelling Dev Headset", "Audio", 199.99, "Block out alerts until PagerDuty calls.", "🎧"));
        products.add(new Product(6L, "Linux Penguin Desk Mat", "Accessories", 24.99, "Extended stitched desk mat with Bash cheat sheet.", "🐧"));
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return products;
    }

    @GetMapping("/cart")
    public Map<String, Object> getCart() {
        List<Map<String, Object>> items = new ArrayList<>();
        double total = 0.0;
        int totalQty = 0;

        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Product p = findProduct(entry.getKey());
            if (p != null) {
                int qty = entry.getValue();
                double subtotal = p.getPrice() * qty;
                total += subtotal;
                totalQty += qty;

                Map<String, Object> item = new HashMap<>();
                item.put("product", p);
                item.put("quantity", qty);
                item.put("subtotal", Math.round(subtotal * 100.0) / 100.0);
                items.add(item);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("total", Math.round(total * 100.0) / 100.0);
        response.put("count", totalQty);
        return response;
    }

    @PostMapping("/cart/add/{id}")
    public Map<String, Object> addToCart(@PathVariable Long id) {
        if (findProduct(id) != null) {
            cart.put(id, cart.getOrDefault(id, 0) + 1);
        }
        return getCart();
    }

    @DeleteMapping("/cart/remove/{id}")
    public Map<String, Object> removeFromCart(@PathVariable Long id) {
        cart.remove(id);
        return getCart();
    }

    @PostMapping("/checkout")
    public Map<String, String> checkout() {
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        cart.clear();
        Map<String, String> res = new HashMap<>();
        res.put("status", "SUCCESS");
        res.put("orderId", orderId);
        res.put("message", "Order placed successfully! Ready for deployment.");
        return res;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "devops-ecommerce");
    }

    private Product findProduct(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
