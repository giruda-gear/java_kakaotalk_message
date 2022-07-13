package com.aaihc.mhp.biz.ems.domain;

/**
 * 프로젝트명	: 미들유저 참여형 헬스케어 플랫폼
 * 개발사		: 어솔루션- <a href="http://www.asolution.biz/">asolution.biz</a>
 *
 * <p>PointPolicyNo (포인트 정책  일련번호 Domain)</p>
 *
 * @author 	    : 김형수
 * date 		: 2020. 03. 27.
 *
 * modifier 	:
 * modify-date  :
 * description  :
 */
public enum TemplateCode {
    TC01_MB_DMNC_BEF_3M("wiple_0001", "S", null, null, "AL", "앱 실행하기", true)           // 휴면 회원 3개월 전
    , TC02_BILL_FAIL("wiple_0002", "S", null, null, "AL", "앱 실행하기", true)             // 건강안부콜 결제 실패
    , TC03_DS1_APLY("wiple_0003", "S", null, null, "AL", "앱 실행하기", true)              // 질병위험도예측서비스 신청 완료
    , TC04_DS2_APLY("wiple_0004", "S", null, null, "AL", "앱 실행하기", true)              // 영양미네랄중금속분석검사 신청 완료
    , TC05_DS2_REFND("wiple_0005", "S", null, null, "AL", "앱 실행하기", true)             // 영양미네랄중금속분석검사 환불 승인
    , TC06_DS3_APLY("wiple_0006", "S", null, null, "AL", "앱 실행하기", true)              // 장내미생물분석검사 신청 완료
    , TC07_DS3_REFND("wiple_0007", "S", null, null, "AL", "앱 실행하기", true)             // 장내미생물분석검사 환불 승인
    , TC38_DS4_APLY("wiple_0038", null, null, null, null, null, false)                     // 암질환예측서비스 신청 완료
    , TC08_PNT_EXP_BEF_1M("wiple_0008", "S", null, null, "AL", "앱 실행하기", true)        // 포인트 소멸 1개월 전
    , TC09_PNT_EXP_BEF_1W("wiple_0009", "S", null, null, "AL", "앱 실행하기", true)        // 포인트 소멸 1주일 전
    , TC10_CUPN_BUY("wiple_0010", "S", null, null, "AL", "앱 실행하기", true)              // 이용권 구매
    , TC11_CUPN_REFND("wiple_0011", "S", null, null, "AL", "앱 실행하기", true)            // 이용권 환불 승인
    , TC12_CUPN_EXP_BEF_1M("wiple_0012", "S", null, null, "AL", "앱 실행하기", true)       // 이용권 소멸 1개월 전
    , TC13_CUPN_EXP_BEF_1W("wiple_0013", "S", null, null, "AL", "앱 실행하기", true)       // 이용권 소멸 1주일 전
    , TC14_CUPN_EXP_BEF_1M_EXT("wiple_0014", "S", null, null, "AL", "앱 실행하기", true)   // 이용권 소멸 1주일 전 (연장 후)
    , TC15_CUPN_EXP_BEF_1W_EXT("wiple_0015", "S", null, null, "AL", "앱 실행하기", true)   // 이용권 소멸 1개월 전 (연장 후)
    , TC34_CHATBOT_COACHING_CERT_NUM("wiple_0034", null, null, null, null, null, false)    // 챗봇 인증번호
    , TC21_CHATBOT_COUNSEL("wiple_0021", "A", "BC", "상담톡 전환하기", null, null, false)  // 챗봇 건강상담
    , TC24_MINDCALL_APLY("wiple_0024", null, null, null, null, null, false)                // 건강안부콜 수신자 정보동의
    , TC27_TFA_PRPYM_EXP_BEF_1W("wiple_0027", null, null, null, null, null, false)         // TFA 선납 신청 만료 1주일 전
    , TC28_TFA_PRPYM_EXP("wiple_0028", null, null, null, null, null, false)                // TFA 선납 신청 만료 (TFA 유저)
    , TC29_MB_TFA_PRPYM_EXP("wiple_0029", null, null, null, null, null, false)             // TFA 선납 신청 만료 (선납 신청 회원)
    , TC30_PREMIUM_APLY("wiple_0030", null, null, null, null, null, false)                 // 위플 프리미엄 신청
    , TC31_PREMIUM_REFND("wiple_0031", null, null, null, null, null, false)                // 위플 프리미엄 환불
    , TC32_PREMIUM_EXP_BEF_1W("wiple_0032", null, null, null, null, null, false)           // 위플 프리미엄 만료 1주일 전
    , TC33_PREMIUM_EXP("wiple_0033", null, null, null, null, null, false)                  // 위플 프리미엄 만료
    , TC40_TFA_PREPAYMENT_ADMS("wiple_0040", null, null, null, null, null, false)          // TFA 선납 신청 입금 처리 완료
    , TC37_TFA_PREPAYMENT_ADMS2("wiple_0037", null, null, null, null, null, false)         // TFA 선납 신청 입금 처리 완료2
    , TC36_TFA_PREPAYMENT_ADMS3("wiple_0036", null, null, null, null, null, false)         // TFA 선납 신청 입금 처리 완료3
    , TC41_TOSS_PREMIUM_APLY("wiple_0041", null, null, null, null, null, false);           // 토스 캠페인 접수 완료 (선납)

    private String cd; // 코드
    private String tp;  // attchment/supplement 타입
    private String btnType; // 버튼 타입
    private String btnName; // 버튼 이름
    private String qckRplType; // 바로연결 타입
    private String qckRplName; // 바로연결 이름
    private boolean appLink; // 앱링크

    TemplateCode(String cd, String tp, String btnType, String btnName, String qckRplType, String qckRplName, boolean appLink) {
        this.cd = cd;
        this.tp = tp;
        this.btnType = btnType;
        this.btnName = btnName;
        this.qckRplType = qckRplType;
        this.qckRplName = qckRplName;
        this.appLink = appLink;
    }

    public static TemplateCode getVal(String tmplCd) {
        TemplateCode templateCode = null;
        for (TemplateCode tc : TemplateCode.values()) {
            if (tc.getCd().equals(tmplCd)) {
                return tc;
            }
        }

        return templateCode;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getBtnType() {
        return btnType;
    }

    public void setBtnType(String btnType) {
        this.btnType = btnType;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getQckRplType() {
        return qckRplType;
    }

    public void setQckRplType(String qckRplType) {
        this.qckRplType = qckRplType;
    }

    public String getQckRplName() {
        return qckRplName;
    }

    public void setQckRplName(String qckRplName) {
        this.qckRplName = qckRplName;
    }

    public boolean isAppLink() {
        return appLink;
    }

    public void setAppLink(boolean appLink) {
        this.appLink = appLink;
    }
}
