package com.hms.springbackendhms.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    Medicine medicine;
    String description;
    Date useUntil;
}
