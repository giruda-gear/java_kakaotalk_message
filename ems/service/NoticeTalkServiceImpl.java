package com.aaihc.mhp.biz.ems.service;

import asn.util.lang.StringUtil;
import asn.util.num.NumberUtil;
import com.aaihc.mhp.biz.ems.domain.NoticeTalk;
import com.aaihc.mhp.biz.ems.domain.TemplateCode;
import com.aaihc.mhp.biz.ems.repository.NoticeTalkMapper;
import com.aaihc.mhp.core.domain.BaseDomain;
import com.aaihc.mhp.core.domain.PagingArrayList;
import com.aaihc.mhp.core.domain.PagingList;
import com.aaihc.mhp.core.domain.Search;
import com.aaihc.mhp.core.exception.MhpappException;
import com.aaihc.mhp.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 프로젝트명	:미들유저 참여형 헬스케어 플랫폼
 * 개발사		: 어솔루션- <a href="http://www.asolution.biz/">asolution.biz</a>
 *
 * <p>NoticeTalkServiceImpl (알림톡 Service Implement)</p>
 *
 * @author 	    : 김형수
 * date 		: 2020. 11. 18.
 *
 * modifier 	:
 * modify-date  :
 * description  :
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class NoticeTalkServiceImpl extends BaseService implements NoticeTalkService {

    @Autowired
    private NoticeTalkMapper noticetalkMapper;

    /**
     * <p>알림톡을 등록합니다</p>
     *
     * @see NoticeTalkService#add(String, String, String)
     * @param cph 휴대폰
     * @param tmplCd 템플릿 코드
     * @param sndMsg 발송 메시지
     * @return 수
     * @throws com.aaihc.mhp.core.exception.MhpappException mhpapp 예외
     */
    public int add(String cph, String tmplCd, String sndMsg) {
        NoticeTalk noticeTalk = new NoticeTalk();

        noticeTalk.setSenderKey(NoticeTalk.SENDERKEY);
        noticeTalk.setChannel(NoticeTalk.CHANNEL);
        noticeTalk.setSndType(NoticeTalk.SNDTYPE);
        noticeTalk.setPhoneNum(cph);
        noticeTalk.setTmplCd(tmplCd);
        noticeTalk.setSndMsg(sndMsg);
        noticeTalk.setReqDeptCd(NoticeTalk.REQ_DEPT_CD);
        noticeTalk.setReqUsrId(NoticeTalk.REQ_USR_ID);
        noticeTalk.setSmsSndYn(NoticeTalk.SMS_SND_YN);
        noticeTalk.setTranSts(NoticeTalk.TRAN_STS);
        noticeTalk.setSmsSndNum(NoticeTalk.SMS_SND_NUM);

        TemplateCode templateCode = TemplateCode.getVal(tmplCd);

        if (StringUtil.equals(templateCode.getTp(), "A")) {
            noticeTalk.setAttachment(NoticeTalk.ofAttachmentJson(templateCode.getBtnName(), templateCode.getBtnType(), templateCode.isAppLink()));
        } else if (StringUtil.equals(templateCode.getTp(), "S")) {
            noticeTalk.setSupplement(NoticeTalk.ofSupplementJson(templateCode.getQckRplName(), templateCode.getQckRplType(), templateCode.isAppLink()));
        }

        int result = noticetalkMapper.insert(noticeTalk);

        if (result <= 0) {
            throw MhpappException.msgCdOf("exceptaion.notice_talk.send");
        }

        return result;
    }

    /**
     * <p>지정된 알림톡을 가져옵니다</p>
     *
     * @see com.aaihc.mhp.biz.ems.service.NoticeTalkService#findOne(int)
     * @param seq 일련번호
     * @return 도메인
     */
    @Transactional(readOnly = true)
    public BaseDomain findOne(int seq) {
        return noticetalkMapper.select(seq);
    }

    /**
     * <p>지정된 알림톡의 수를 가져옵니다</p>
     *
     * @see com.aaihc.mhp.biz.ems.service.NoticeTalkService#findTotalCnt(Search)
     * @param search 검색
     * @return 수
     */
    @Transactional(readOnly = true)
    public int findTotalCnt(Search search) {
        return noticetalkMapper.selectTotalCnt(search);
    }

    /**
     * <p>알림톡의 목록을 가져옵니다</p>
     *
     * @see com.aaihc.mhp.biz.ems.service.NoticeTalkService#findList(Search)
     * @param search 검색
     * @return 목록
     */
    @Transactional(readOnly = true)
    public List<BaseDomain> findList(Search search) {
        return noticetalkMapper.selectList(search);
    }

    /**
     * <p>알림톡의 목록을 가져옵니다</p>
     *
     * @see com.aaihc.mhp.biz.ems.service.NoticeTalkService#findPage(Search)
     * @param search 검색
     * @return 페이징 목록
     */
    @Transactional(readOnly = true)
    public PagingList findPage(Search search) {
        PagingList result = null;

        if (search.getInt("pg") > 0 && search.getInt("pgSz") > 0) {
            search.setRow();
            result = new PagingArrayList(this.findList(search), NumberUtil.toInt(this.findTotalCnt(search)), search);
        }

        return result;
    }

}