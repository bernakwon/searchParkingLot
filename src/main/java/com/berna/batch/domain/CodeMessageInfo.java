package com.berna.batch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeMessageInfo {
	@JsonProperty("CODE")
	private String code;
	@JsonProperty("MESSAGE")
	private String message;


}
