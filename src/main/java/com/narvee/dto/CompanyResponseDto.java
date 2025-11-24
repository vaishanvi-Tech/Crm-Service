package com.narvee.dto;

import lombok.Data;

@Data
public class CompanyResponseDto {
	private Long id;
	private String name;
	private String email;
	private String address;
	private Long phoneNo;
	private String websiteUrl;
	private String country;
}
