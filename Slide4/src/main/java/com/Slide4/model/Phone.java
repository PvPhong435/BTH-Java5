package com.Slide4.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Phone {
	private String phoneNumber="0333002951";
	private String name="Pham Van phong";
	private String note="there no note";
	private String address="no address";
}
