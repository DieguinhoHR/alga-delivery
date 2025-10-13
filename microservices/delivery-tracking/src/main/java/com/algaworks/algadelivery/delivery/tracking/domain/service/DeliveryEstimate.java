package com.algaworks.algadelivery.delivery.tracking.domain.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@AllArgsConstructor
@Getter
public class DeliveryEstimate {

    private Duration estimatedTime;
    private Double distanceInKm;
}
