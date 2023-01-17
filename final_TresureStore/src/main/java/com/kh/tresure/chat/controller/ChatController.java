package com.kh.tresure.chat.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.tresure.chat.model.service.ChatService;
import com.kh.tresure.chat.model.vo.ChatRoom;

@Controller
public class ChatController {

	private Logger logger = LoggerFactory.getLogger(ChatController.class);
	private ChatService chatService;
	
	// 기본생성자
	public ChatController() {
		
	}
	
	@Autowired
	public ChatController( ChatService chatService){
		this.chatService = chatService;
	}
	
	
	//채팅방 목록 조회
	@RequestMapping(value = "chat/chatRoomList", method = RequestMethod.GET)
	public String selectChatRoomList(Model model) {
		
		List<ChatRoom> crList = chatService.selectChatRoomList();
		
		model.addAttribute("chatRoomList", crList);
		
		logger.info(">> 채팅방 리스트로 이동");
		
		return "chat/chatRoomList";
	}
	
}