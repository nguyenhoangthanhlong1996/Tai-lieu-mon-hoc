package vn.edu.hcmut.api.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.edu.hcmut.core.enums.Errors;
import vn.edu.hcmut.core.model.MessageResponse;

@RestController
public class HomeController {
	public MessageResponse home() {
		return new MessageResponse(Errors.OK.getCode(), Errors.OK.getText());
	}
}
