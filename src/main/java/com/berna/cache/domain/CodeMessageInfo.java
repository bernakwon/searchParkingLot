package com.berna.cache.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeMessageInfo {

	/*api return code*/
	@JsonProperty("CODE")
	private String code;

	/*api return message*/
	@JsonProperty("MESSAGE")
	private String message;


}
