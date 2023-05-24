package com.hms.springbackendhms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteMedicalActionRequest {
    String amka;
    String title;
    String details;
}
