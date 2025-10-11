package com.algaworks.algadelivery.delivery.tracking.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactPointTest {

    @Test
    void shouldCreateContactPointWithBuilder() {
        String zipCode = "12345-678";
        String street = "Main St";
        String number = "100";
        String complement = "Apt 1";
        String name = "John Doe";
        String phone = "555-1234";

        ContactPoint contactPoint = ContactPoint.builder()
                .zipCode(zipCode)
                .street(street)
                .number(number)
                .complement(complement)
                .name(name)
                .phone(phone)
                .build();

        assertEquals(zipCode, contactPoint.getZipCode());
        assertEquals(street, contactPoint.getStreet());
        assertEquals(number, contactPoint.getNumber());
        assertEquals(complement, contactPoint.getComplement());
        assertEquals(name, contactPoint.getName());
        assertEquals(phone, contactPoint.getPhone());
    }
}