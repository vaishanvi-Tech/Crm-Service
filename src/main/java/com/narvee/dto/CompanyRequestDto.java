package com.narvee.dto;

import lombok.Data;

@Data
public class CompanyRequestDto {
	private String name;
	private String email;
	private String address;
	private Long phoneNo;
	private String websiteUrl;
	private String country;
}
