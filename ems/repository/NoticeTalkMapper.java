package com.aaihc.mhp.biz.ems.repository;

import com.aaihc.mhp.core.domain.BaseDomain;
import com.aaihc.mhp.core.domain.Search;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 프로젝트명	:미들유저 참여형 헬스케어 플랫폼
 * 개발사		: 어솔루션- <a href="http://www.asolution.biz/">asolution.biz</a>
 *
 * <p>NoticeTalkMapper Mapper (알림톡 Mapper)</p>
 *
 * @author 	    : 김형수
 * date 		: 2020. 11. 18.
 *
 * modifier 	:
 * modify-date  :
 * description  :
 */
@Repository
public interface NoticeTalkMapper {

    /**
     * <p>지정된 알림톡을 조회합니다</p>
     *
     * @param domain 도메인
     * @return 수
     */
    public int insert(BaseDomain domain);

    /**
     * <p>지정된 알림톡을 조회합니다</p>
     *
     * @param seq 일련번호
     * @return 도메인
     */
    public BaseDomain select(int seq);

    /**
     * <p>지정된 알림톡을 갯수를 조회합니다</p>
     *
     * @param search 검색
     * @return 수
     */
    public int selectTotalCnt(Search search);

    /**
     * <p>알림톡 목록을 조회합니다</p>
     *
     * @param search 검색
     * @return 목록
     */
    public List<BaseDomain> selectList(Search search);

}