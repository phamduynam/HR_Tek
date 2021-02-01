package com.toprate.hr_tek_demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SearchUserDto {

    private String role;

    private String status;
}
