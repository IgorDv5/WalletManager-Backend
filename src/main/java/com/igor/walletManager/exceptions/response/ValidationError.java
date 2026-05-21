package com.igor.walletManager.exceptions.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	private List<FieldMessage> errors = new ArrayList<>();


}
