package com.algaworks.algadelivery.delivery.tracking.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void shouldCreateItemWithBrandNew() {
        String name = "Produto";
        Integer quantity = 2;

        Item item = Item.brandNew(name, quantity, new Delivery());

        assertNotNull(item.getId());
        assertEquals(name, item.getName());
        assertEquals(quantity, item.getQuantity());
    }
}