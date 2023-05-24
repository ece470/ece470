package com.hms.springbackendhms.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {
    public static final int SUCCESS = 1;
    public static final int FAIL = -1;
    int status;
}
