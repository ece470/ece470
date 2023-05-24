package com.hms.springbackendhms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddPrescriptionRequest {
    String amka;
    String medicine;
    String description;
    String useUntil;
}
