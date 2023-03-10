package com.kh.tresure.mypage.model.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tresure.heart.model.vo.Heart;
import com.kh.tresure.review.model.vo.Review;
import com.kh.tresure.sell.model.vo.Sell;

@Repository
public class MyPageDao {
	
	/**
	 * 판매수 조회 */
	public int sellCount(SqlSession sqlSession, int userNo) {
		return sqlSession.selectOne("mypageMapper.sellCount", userNo);
	}
	
	/**
	 * 팔로워 수 조회 */
	public int followCount(SqlSession sqlSession, int userNo) {
		return sqlSession.selectOne("mypageMapper.followCount", userNo);
	}
	
	/**
	 * 신고 수 조회 */
	public int reportCount(SqlSession sqlSession, int userNo) {
		return sqlSession.selectOne("mypageMapper.reportCount", userNo);
	}
	
	/**
	 * 상점 오픈일 */
	public int marketOpen(SqlSession sqlSession, int userNo) {
		return sqlSession.selectOne("mypageMapper.marketOpen", userNo);
	}
	
	/**
	 * 상점후기 작성한 상점 등급 */
	public int reviewAvg(SqlSession sqlSession, int userNo) {
		return sqlSession.selectOne("mypageMapper.reviewAvg", userNo);
	}
	
	/**
	 * 마이페이지 상품 목록 */
	public List<Sell> mypageSellList(SqlSession sqlSession, int userNo){
		return (List)sqlSession.selectList("sellMapper.mypageSellList", userNo);
	}
	
	/**
	 * 마이페이지 찜 상품 목록 */
	public List<Heart> mypageHeartList(SqlSession sqlSession, int userNo){
		return (List)sqlSession.selectList("heartMapper.mypageHeartList", userNo);
	}
	
	/**
	 * 마이페이지 상점후기 목록 */
	public List<Review> mypageReviewList(SqlSession sqlSession, int userNo) {
		return (List)sqlSession.selectList("reviewMapper.mypageReviewList", userNo);
	}
	
	/**
	   * 판매내역 상태변경
	   */
	public int changeStatus(SqlSession sqlSession, int sellNo) {
		return sqlSession.update("mypageMapper.changeStatus", sellNo);

	}
	
	public List<Sell> mypagetSellList(SqlSession sqlSession, int userNo){
		return (List)sqlSession.selectList("sellMapper.mypagetSellList", userNo);
	}

	/**
	 * 마이페이지 구매목록 */
	public List<Sell> mypagePurchaseList(SqlSession sqlSession, int userNo) {
		return sqlSession.selectList("sellMapper.mypagePurchaseList",userNo);
	}
	
	public int heartCount(SqlSession sqlSession, int userNo) {
		return sqlSession.selectOne("heartMapper.heartCount", userNo);
	}

}
