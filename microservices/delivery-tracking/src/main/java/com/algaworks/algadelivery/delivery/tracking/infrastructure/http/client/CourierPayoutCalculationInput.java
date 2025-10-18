package com.algaworks.algadelivery.delivery.tracking.infrastructure.http.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CourierPayoutCalculationInput {

    private Double distanceInKm;
}
