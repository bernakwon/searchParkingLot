package com.berna.scheduler.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeMessageInfo {

	@JsonProperty("CODE")
	private String code;
	@JsonProperty("MESSAGE")
	private String message;


}
