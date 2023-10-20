package org.java.app.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.app.db.pojo.Message;
import org.java.app.db.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServ {

	@Autowired
	private MessageRepo messageRepo;
	
	public Message save (Message message) {
		return messageRepo.save(message);
	}
	
	public List<Message> findAll() {
		return messageRepo.findAll();
	}


	public void deleteMessage(Message message) {
		messageRepo.delete(message);
	}

	public Optional<Message> findById(int id) {
		return messageRepo.findById(id);
	}
	
	public List<Message> findMessagesByUserId(int userId) {
	
	    return messageRepo.findByUserId(userId);
	}

	

}

