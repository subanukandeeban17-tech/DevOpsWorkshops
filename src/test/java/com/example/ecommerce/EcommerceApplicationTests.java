package com.example.ecommerce;

import com.example.ecommerce.controller.StoreController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EcommerceApplicationTests {

    @Autowired
    private StoreController storeController;

    @Test
    void contextLoadsAndProductsExist() {
        assertThat(storeController.getProducts()).isNotEmpty();
        assertThat(storeController.health().get("status")).isEqualTo("UP");
    }
}
