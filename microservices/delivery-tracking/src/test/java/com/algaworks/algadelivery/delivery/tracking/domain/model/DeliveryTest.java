package com.algaworks.algadelivery.delivery.tracking.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    void shouldCreateDeliveryDraftWithValuesPattern() {
        Delivery delivery = Delivery.draft();

        assertNotNull(delivery.getId());
        assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        assertEquals(0, delivery.getTotalItems());
        assertEquals(BigDecimal.ZERO, delivery.getTotalCost());
        assertEquals(BigDecimal.ZERO, delivery.getCourierPayout());
        assertEquals(BigDecimal.ZERO, delivery.getDistanceFee());
        assertTrue(delivery.getItems().isEmpty());
    }

    @Test
    void shouldAddItemAndReturnItemId() {
        Delivery delivery = Delivery.draft();
        String itemName = "Product";
        int quantity = 2;

        UUID itemId = delivery.addItem(itemName, quantity);

        List<Item> items = delivery.getItems();
        assertEquals(1, items.size());
        assertEquals(itemId, items.get(0).getId());
        assertEquals(itemName, items.get(0).getName());
        assertEquals(quantity, items.get(0).getQuantity());
    }

    @Test
    void shouldRemoveItemAndUpdateTotalItems() {
        Delivery delivery = Delivery.draft();
        UUID itemId1 = delivery.addItem("Product1", 2);
        UUID itemId2 = delivery.addItem("Product2", 3);

        delivery.removeItems(itemId1);

        List<Item> items = delivery.getItems();
        assertEquals(1, items.size());
        assertEquals(itemId2, items.get(0).getId());
        assertEquals(3, delivery.getTotalItems());
    }

    @Test
    void shouldChangeItemQuantityAndUpdateTotalItems() {
        Delivery delivery = Delivery.draft();
        UUID itemId = delivery.addItem("Product", 2);

        delivery.changeItemQuantity(itemId, 5);

        List<Item> items = delivery.getItems();
        assertEquals(5, items.get(0).getQuantity());
        assertEquals(5, delivery.getTotalItems());
    }

    @Test
    void shouldRemoveAllItemsAndUpdateTotalItems() {
        Delivery delivery = Delivery.draft();
        delivery.addItem("Product1", 2);
        delivery.addItem("Product2", 3);

        delivery.removeItems();

        assertTrue(delivery.getItems().isEmpty());
        assertEquals(0, delivery.getTotalItems());
    }

    @Test
    void shouldReturnUnmodifiableItemsList() {
        Delivery delivery = Delivery.draft();
        delivery.addItem("Product", 1);

        List<Item> items = delivery.getItems();

        assertThrows(UnsupportedOperationException.class, () -> items.add(Item.brandNew("Another", 2)));
    }

    @Test
    void shouldMarkDeliveryAsDeliveredAndSetStatusAndFullfilledAt() {
        Delivery delivery = Delivery.draft();

        delivery.markAsDelivered();

        assertEquals(DeliveryStatus.DELIVERY, delivery.getStatus());
        assertNotNull(delivery.getFullfilledAt());
    }
}