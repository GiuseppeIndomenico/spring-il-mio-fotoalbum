package org.java.app.api;

import org.java.app.db.pojo.Message;
import org.java.app.db.serv.MessageServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/messages")
public class MessageApiController {

	@Autowired
	private MessageServ messageServ;

	@GetMapping
	public ResponseEntity<List<Message>> getAllMessages() {
		List<Message> messages = messageServ.findAll();
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Message> createMessage(@RequestBody Message message) {
		Message savedMessage = messageServ.save(message);
		return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
	}

}
