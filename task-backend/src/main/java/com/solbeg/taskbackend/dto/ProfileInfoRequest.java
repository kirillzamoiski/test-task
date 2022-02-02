package com.solbeg.taskbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProfileInfoRequest {

    private String name;
    private String sector;
    private Boolean agreeToTerms;
}
