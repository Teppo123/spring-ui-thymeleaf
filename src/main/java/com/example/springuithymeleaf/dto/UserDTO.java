package com.example.springuithymeleaf.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@NoArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 176712563459609446L;
	
	private long id;

//	@NotBlank(message = "First name can't be blank")
	private String firstName;

//	@NotBlank(message = "Last name can't be blank")
	private String lastName;

//	@NotNull(message = "Birth date can't be null")

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationTime;

}
