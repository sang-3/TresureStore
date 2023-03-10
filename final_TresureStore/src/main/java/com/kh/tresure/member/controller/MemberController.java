package com.kh.tresure.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.kh.tresure.heart.model.vo.Heart;
import com.kh.tresure.member.model.service.KakaoAPI;
import com.kh.tresure.member.model.service.MemberService;
import com.kh.tresure.member.model.service.NaverLoginBO;
import com.kh.tresure.member.model.vo.Account;
import com.kh.tresure.member.model.vo.Member;
import com.kh.tresure.mypage.model.service.MyPageService;


import com.kh.tresure.purchase.model.vo.Purchase;
import com.kh.tresure.report.model.vo.Report;

import com.kh.tresure.review.model.vo.Review;
import com.kh.tresure.sell.model.vo.Sell;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
@Primary
public class MemberController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private MessageController messageController;
	private KakaoAPI kakao;
	private MemberService memberService;
	private MyPageService mypageService;
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	// 기본생성자
	public MemberController() {
	}

	@Autowired

	public MemberController(MessageController messageController, MemberService memberService, KakaoAPI kakao, MyPageService mypageService, NaverLoginBO naverLoginBO){
		this.messageController = messageController;
		this.memberService = memberService;
		this.kakao = kakao;
		this.mypageService=mypageService;
		this.naverLoginBO=naverLoginBO;
	}
	
	/*
	 * // 통합로그인 창으로 이동하는 메소드
	 * 
	 * @RequestMapping(value = "/loginJoinForm", method = RequestMethod.GET) public
	 * String enrollForm() {
	 * 
	 * logger.info(">> 회원가입 폼으로 이동");
	 * 
	 * return "member/memberLoginForm"; }
	 */

	// 본인인증 창으로 이동하는 메소드
	@RequestMapping(value = "/loginJoin/identification", method = RequestMethod.GET)
	public String identificationForm() {

		logger.info(">> 본인인증 폼으로 이동");

		return "member/identificationForm";
	}

	// 인증번호 입력창으로 이동하는 메소드
	@RequestMapping(value = "/loginJoin/authenticationNumber", method = RequestMethod.POST)
	public String authenticationNumberForm(@RequestParam(value = "userName") String userName, // 사용자이름
			@RequestParam(value = "birth") String birth, // 생년월일
			@RequestParam(value = "birth2") int birth2, // 뒷 첫번째 자리(필요는 없음)
			@RequestParam(value = "phone") String phone, // 핸드폰 번호
			Model model, Member m, HttpSession session) {

		logger.info(">> 인증번호 입력하기 폼으로 이동");

		int blackUser = memberService.blackConsumer(m, userName, phone);

		if (blackUser > 0) {
			session.setAttribute("alertMsg", "로그인 및 회원가입을 할 수 없는 유저입니다.");
			return "redirect:/";
		}

		// 메세지 보내기 실행

		//int randomNum = messageController.sendOne(phone);
		
		model.addAttribute("userName", userName);
		model.addAttribute("birth", birth);
		model.addAttribute("phone", phone);
		model.addAttribute("randomNum",123123);
		
		return "member/authenticationNumberForm";
	}

	// 인증번호 입력하고 회원가입 및 로그인하기
	@RequestMapping(value = "/loginJoin/loginStrart", method = RequestMethod.POST)
	public String loginAndMemberEnroll(Member member, @RequestParam(value = "inputNumber") String inputNumber, // 사용자가
																												// 입력한
																												// 인증번호
			@RequestParam(value = "randomNum") String randomNum, // 생성된 인증번호
			Model model, HttpSession session) {

		if (inputNumber.equals(randomNum)) {
			// 인증번호와 같은경우
			Member loginUser = memberService.loginAndMemberEnroll(member);
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("alertMsg", loginUser.getUserName() + "님 환영합니다");
			
			String accountInfo =  memberService.userAcountIs(loginUser.getUserNo());
			session.setAttribute("accountInfo", accountInfo);

		}

		return "redirect:/";
	}

	// 임의적으로 관리자로 로그인하는 컨트롤러생성(이거 문자가 무제한이아니라서 임의적으로 넣어둔 거 - 삭제예정)
	@RequestMapping(value = "/loginJoin/pp")
	public String pp(HttpServletRequest request) {

		Member loginUser = Member.builder().userNo(10).userName("관리자").phone("01012345678").count(0).status("Y")
				.blackListStatus("N").build();

		logger.info(">> 관리자로 로그인");

		HttpSession session = request.getSession();
		session.setAttribute("loginUser", loginUser);
		session.setAttribute("alertMsg", loginUser.getUserName() + "님 환영합니다");

		logger.info("loginUser ? " + loginUser);

		return "redirect:/";
	}

	// 임의적으로 사용자로 로그인하는 컨트롤러생성(이거 문자가 무제한이아니라서 임의적으로 넣어둔 거 - 삭제예정)
	@RequestMapping(value = "/loginJoin/qq")
	public String qq(HttpServletRequest request) {

		Member loginUser = Member.builder().userNo(1).userName("사용자").phone("01099887766").count(0).status("Y")
				.blackListStatus("N").build();

		logger.info(">> 사용자로 로그인");

		HttpSession session = request.getSession();
		session.setAttribute("loginUser", loginUser);
		session.setAttribute("alertMsg", loginUser.getUserName() + "님 환영합니다");

		return "redirect:/";
	}

	// 로그아웃 하는 메소드
	@RequestMapping(value = "/logout")
	public String memberLogOut(HttpServletRequest request) {

		logger.info(">> 로그아웃");

		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");

		session.removeAttribute("oauthToken");	
		session.removeAttribute("accountInfo");
		
		session.setAttribute("alertMsg", "다음에 또 오세요 ^ㅁ^");
		return "redirect:/";
	}

	/**
	 * kakao 로그인
	 * 
	 * @param code
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/loginJoin/kakao")
	public String login(@RequestParam("code") String code, HttpSession session) {

		// 받은 code로 access_token 가져오기
		String access_Token = kakao.getAccessToken(code);

		// access_token으로 user정보 가져오기 (닉네임, 이메일계정)
		Member member = kakao.getUserInfo(access_Token);
		logger.info("login Controller : " + member.toString());

		// 클라이언트의 이메일이 존재할 때(즉, 카카오 로그인 성공)
		if (member != null) {

			// email과 nickname으로 회원체크, 회원가입/로그인 성공 후 member객체 반환
			member = memberService.loginAndMemberEnroll(member);
			logger.info("member : " + member);

			if (member == null) {
				kakao.unlink(access_Token);
				session.setAttribute("alertMsg", "로그인 및 회원가입을 할 수 없는 유저입니다.");
				return "redirect:/";
			}
			String accountInfo =  memberService.userAcountIs(member.getUserNo());
			session.setAttribute("accountInfo", accountInfo);
			
			session.setAttribute("loginUser", member);
			session.setAttribute("access_Token", access_Token);
			session.setAttribute("alertMsg", member.getUserName() + "님 환영합니다");
		}

		return "redirect:/";
	}

	/**
	 * kakao 이 서비스에서만 로그아웃
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout/kakao")
	public String logout(HttpSession session) throws IOException {
		String access_Token = (String) session.getAttribute("access_Token");
		if (access_Token != null && !"".equals(access_Token)) {
			kakao.kakaoLogout(access_Token);
			session.removeAttribute("access_Token");
			session.removeAttribute("loginUser");
			session.removeAttribute("userId");
			session.removeAttribute("accountInfo");
			session.setAttribute("alertMsg", "다음에 또 오세요 ^ㅁ^");
		} else {
			System.out.println("access_Token is null");
		}
		return "redirect:/";
	}

	/**
	 * kakao 계정과 함께 로그아웃(연결끊기)
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/kakaounlink")
	public String unlink(HttpSession session) {
		kakao.unlink((String) session.getAttribute("access_Token"));
		session.invalidate();
		session.removeAttribute("loginUser");
		session.removeAttribute("accountInfo");
		session.setAttribute("alertMsg", "다음에 또 오세요 ^ㅁ^");
		return "redirect:/";
	}

	
	//로그인 첫 화면 요청 메소드
			@RequestMapping(value = "/loginJoinForm", method = { RequestMethod.GET, RequestMethod.POST })
			public String login(Model model, HttpSession session) {
				
				logger.info(">> 회원가입 폼으로 이동");
				
				/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
				String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
				
				//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
				//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
				System.out.println("네이버:" + naverAuthUrl);
				
				//네이버 
				model.addAttribute("url", naverAuthUrl);
		 
				return "member/memberLoginForm";
			}
			
			//네이버 로그인 성공시 callback호출 메소드
			@RequestMapping(value = "/naverCallback", method = { RequestMethod.GET, RequestMethod.POST })
			public String callback(Member m, Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
				
				System.out.println("여기는 callback");
				OAuth2AccessToken oauthToken;
		        oauthToken = naverLoginBO.getAccessToken(session, code, state);
		 
		        m = naverLoginBO.getNavUserInfo(oauthToken);
		        
				Member userInfo = memberService.loginAndMemberEnroll(m);
				
				if(userInfo==null) {
					
					session.setAttribute("alertMsg", "로그인 및 회원가입을 할 수 없는 유저입니다.");
				
				}else {
				
					//4.파싱 닉네임 세션으로 저장
					session.setAttribute("loginUser",userInfo); //세션 생성
					session.setAttribute("oauthToken", oauthToken);
					
					String accountInfo =  memberService.userAcountIs(userInfo.getUserNo());
					session.setAttribute("accountInfo", accountInfo);


					session.setAttribute("alertMsg", m.getUserName()+"님 환영합니다");



					
					
					System.out.println(""+userInfo);
				
				}
			     
				return "redirect:/";
			}
	
	
// 회원탈퇴
			@RequestMapping(value = "delete", method = RequestMethod.GET)
			public String deleteMember(HttpSession session ,Model model) {
			      
				int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
				
				
				logger.info("여기임");
				
				memberService.deleteMember(userNo);


				if ((String) session.getAttribute("access_Token") != null) {
					kakao.unlink((String) session.getAttribute("access_Token"));
					session.removeAttribute("access_Token");
					
					
				}

				//네이버 회원탈퇴
						if((OAuth2AccessToken)session.getAttribute("oauthToken") != null) {
							
								logger.info("apiUrl====="+apiUrl);
								try {
									String res = naverLoginBO.requestToServer(apiUrl);
									model.addAttribute("res", res); //결과값 찍어주는용
									
									session.removeAttribute("oauthToken");

								} catch (IOException e) {

							OAuth2AccessToken oauthToken = (OAuth2AccessToken) session.getAttribute("oauthToken");

							
							String apiUrl = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id="+NaverLoginBO.CLIENT_ID+"&client_secret="+NaverLoginBO.CLIENT_SECRET+"&access_token="+oauthToken.getAccessToken()+"&service_provider=NAVER";

									
										
										logger.info("apiUrl====="+apiUrl);
										try {
											String res = naverLoginBO.requestToServer(apiUrl);
											model.addAttribute("res", res); //결과값 찍어주는용
											
											session.removeAttribute("oauthToken");
											
											
										} catch (IOException e) {
											
											e.printStackTrace();
							
						}
						
					}
          session.removeAttribute("accountInfo");
					session.setAttribute("alertMsg", "감사했습니다 ^_^7");
					session.removeAttribute("loginUser");
				
		      
				return "redirect:/";
			}
	   
	
	//계좌추가
	@ResponseBody
	@RequestMapping(value = "member/account", method = RequestMethod.POST)
	public int userAddAccount (Integer result, Model model, Account accountInfo, HttpSession session, String account, String bankInfo) {		
		
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		
		accountInfo.setUserNo(userNo);
		accountInfo.setAccount(account);
		accountInfo.setBank(bankInfo);
		
		result =  memberService.userAddAccount(accountInfo);

		logger.info(result+"result값");
		logger.info(" 계좌 >>"+ accountInfo);
		logger.info(" >> 계좌 리스트에 추가 완료");
		
		return result;
		
	}

	
	//계좌 수정
	@ResponseBody
	@RequestMapping(value = "member/accountUpdate", method = RequestMethod.POST)
	public int updateAccount (Integer result, Account accountInfo, HttpSession session, String account, int userNo, String bankInfo) {
		
		accountInfo.setUserNo(userNo);
		accountInfo.setAccount(account);
		accountInfo.setBank(bankInfo);
		
		result =  memberService.updateAccount(accountInfo);
		
		return result;
		
	}
	

		
	//로그인 유저 계좌 가져오기
	@ResponseBody
	@RequestMapping(value = "member/sellEnter", method = RequestMethod.POST)
	public int sellInsertForm(HttpServletRequest request, int userNo, Account account) { 
		
		account.setAccount(String.valueOf(userNo));
		
		int result = memberService.accountNumber(account);
		
		if(result > 0) {
			return Integer.parseInt(account.getAccount());
		}
		return result;
	}

	

	
	//관리자 페이지로 이동
	@RequestMapping(value = "common/admin", method = RequestMethod.GET)
	public String admin() { 
	
		return "common/admin";
	}
	

	//관리자페이지 결제관리
	@RequestMapping(value = "admin/payAdmin", method = RequestMethod.GET)
	public String accountList(Model model, HttpSession session) {


	

}
