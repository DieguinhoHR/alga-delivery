package com.algaworks.algadelivery.delivery.tracking.domain.model;

import com.algaworks.algadelivery.delivery.tracking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
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
    void shouldRemoveTotalItems() {
        Delivery delivery = Delivery.draft();

        delivery.removeItems();

        List<Item> items = delivery.getItems();
        assertEquals(0, items.size());
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

        assertThrows(UnsupportedOperationException.class,
                () -> items.add(Item.brandNew("Another", 2, new Delivery())));
    }


    @Test
    public void shouldChangeToPlaced() {
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createdValidPreparationDetails());

        delivery.place();

        assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
        assertNotNull(delivery.getPlacedAt());
    }

    @Test
    public void shouldNotPlace() {
        Delivery delivery = Delivery.draft();
        assertThrows(DomainException.class, () -> {
            delivery.place();
        });

        assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        assertNull(delivery.getPlacedAt());
    }



    private Delivery.PreparationDetails createdValidPreparationDetails() {
        ContactPoint sender = ContactPoint.builder()
                .zipCode("00000-000")
                .street("Rua São Paulo")
                .number("100")
                .complement("Sala 401")
                .name("João Silva")
                .phone("(11) 90000-1234")
                .build();


        ContactPoint recipient = ContactPoint.builder()
                .zipCode("12331-342")
                .street("Rua Brasil")
                .number("500")
                .complement("")
                .name("Maria Silva")
                .phone("(11) 91345-1332")
                .build();



        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("15.00"))
                .courierPayout(new BigDecimal("5.00"))
                .expectedDeliveryTime(Duration.ofHours(5))
                .build();
    }
}