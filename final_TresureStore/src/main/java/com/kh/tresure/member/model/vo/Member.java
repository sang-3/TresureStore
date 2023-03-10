package com.kh.tresure.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
	private int userNo;
	private String userName;
	private String phone;
	private String birth;
	private String email;
	private int count;
	private Date createDate;
	private String status;
	private int heartCount;
	private String blackListStatus;
	private String account;
	private int ReportCount;

}
