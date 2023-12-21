package com.studyscale.dto;

//dto: Data Transfer Object
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginData {
	int id;
	String password;
}
