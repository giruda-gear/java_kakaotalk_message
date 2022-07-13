package com.aaihc.mhp.biz.ems.service;

import com.aaihc.mhp.core.domain.BaseDomain;
import com.aaihc.mhp.core.domain.PagingList;
import com.aaihc.mhp.core.domain.Search;

import java.util.List;

/**
 * 프로젝트명	:미들유저 참여형 헬스케어 플랫폼
 * 개발사		: 어솔루션- <a href="http://www.asolution.biz/">asolution.biz</a>
 *
 * <p>NoticeTalk Service (알림톡 Service)</p>
 *
 * @author 	    : 김형수
 * date 		: 2020. 11. 18.
 *
 * modifier 	:
 * modify-date  :
 * description  :
 */
public interface NoticeTalkService {

	/**
	 * <p>알림톡을 등록합니다</p>
	 *
	 * @param cph 휴대폰
	 * @param tmplCd 템플릿 코드
	 * @param sndMsg 발송 메시지
	 * @return 수
	 */
	public int add(String cph, String tmplCd, String sndMsg);

	/**
	 * <p>지정된 알림톡을 가져옵니다</p>
	 *
	 * @param seq 일련번호
	 * @return 도메인
	 */
	public BaseDomain findOne(int seq);

	/**
	 * <p>지정된 알림톡의 수를 가져옵니다</p>
	 *
	 * @param search 검색
	 * @return 수
	 */
	public int findTotalCnt(Search search);

	/**
	 * <p>지정된 알림톡의 목록을 가져옵니다</p>
	 *
	 * @param search 검색
	 * @return 목록
	 */
	public List<BaseDomain> findList(Search search);

	/**
	 * <p>알림톡의 목록을 가져옵니다</p>
	 *
	 * @param search 검색
	 * @return 페이징 목록
	 */
	public PagingList findPage(Search search);

}