package com.aaihc.mhp.biz.ems.domain;

import asn.util.colletion.MapUtil;
import asn.util.date.DateFormatUtil;
import asn.util.lang.StringUtil;
import asn.util.num.NumberFormatUtil;
import com.aaihc.mhp.core.config.ConfigProperty;
import com.aaihc.mhp.core.domain.BaseDomain;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.collections.map.ListOrderedMap;

/**
 * 프로젝트명	: 미들유저 참여형 헬스케어 플랫폼
 * 개발사		: 어솔루션- <a href="http://www.asolution.biz/">asolution.biz</a>
 *
 * <p>NoticeTalk (알림톡 Domain)</p>
 *
 * @author : 김형수
 * date 		: 2020. 11. 18.
 * <p>
 * modifier 	:
 * modify-date  :
 * description  :
 */
public class NoticeTalk extends BaseDomain {

    private static final long serialVersionUID = 1L;
    public static final String SENDERKEY = ConfigProperty.getString("ems.noticeTalk.senderKey");
    public static final String CHANNEL = ConfigProperty.getString("ems.noticeTalk.channel");
    public static final String SNDTYPE = ConfigProperty.getString("ems.noticeTalk.sndType");
    public static final String APPLINK = ConfigProperty.getString("ems.noticeTalk.appLink");
    public static final String REQ_DEPT_CD = ConfigProperty.getString("ems.noticeTalk.reqDeptCd");
    public static final String REQ_USR_ID = ConfigProperty.getString("ems.noticeTalk.reqDeptId");
    public static final String SMS_SND_YN = ConfigProperty.getString("ems.noticeTalk.smsSndYn");
    public static final String TRAN_STS = ConfigProperty.getString("ems.noticeTalk.tranSts");
    public static final String SMS_SND_NUM = ConfigProperty.getString("ems.noticeTalk.smsSndNum");

    private String sn; // 발송요청ID
    private String senderKey; // 발신프로필키
    private String channel; // 채널
    private String sndType; // 알림톡 발송방식
    private String phoneNum; // 수신자 휴대폰번호
    private String tmplCd; // 알림톡 템플릿코드
    private String subject; // LMS 제목
    private String sndMsg; // 알림톡 발송메시지
    private String smsSndMsg; // SMS 발송메시지
    private String smsSndNum; // 발신자 전화번호
    private String reqDeptCd; // 발송요청 부서코드
    private String reqUsrId; // 발송요청자ID
    private String reqDtm; // 발송요청일시
    private String sndDtm; // 알림톡 발송일시
    private String rsltCd; // 알림톡 발송결과코드
    private String rcptMsg; // 알림톡 발송결과메시지
    private String rcptDtm; // 알림톡발송결과 Agent수신일시
    private String smsSndDtm; // SMS발송일시
    private String smsRsltCd; // SMS발송결과코드
    private String smsRcptMsg; // SMS발송결과 메시지
    private String smsRcptDtm; // SMS발송결과 수신일시
    private String smsGb; // 우회발송된 문자유형
    private String smsSndYn; // 알림톡발송실패시 문자우회발송여부
    private long tranSn; // 전송일련번호
    private String tranSts; // 전송상태
    private String agentId; // Agent 식별자
    private String slot1; // 발송요청 부가정보1
    private String slot2; // 발송요청 부가정보2
    private String trTypeCd; // 발송유형
    private String attachment; // 버튼정보
    private String title; // 제목
    private String supplement; // 추가물품
    private String appUserId; // 앱유저아이디

    public NoticeTalk() {

    }

    /**
     * <p>바로연결 구분 맵</p>
     *
     * @return 값
     */
    public static ListOrderedMap getBtnTpMap() {
        ListOrderedMap result = new ListOrderedMap();
        result.put("AL", "앱링크");
        result.put("MD", "메시지전달");

        return result;
    }

    /**
     * <p>버튼 JSON을 생성합니다.</p>
     *
     * @param btnName 버튼 이름
     * @param btnType 버튼 타입
     * @param isAppLink 앱링크 여부
     * @return 도메인
     */
    public static String ofAttachmentJson(String btnName, String btnType, boolean isAppLink) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject1 = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        String appLink = null;
        if (isAppLink) {
            appLink = NoticeTalk.APPLINK;
        }

        jsonObject1.addProperty("name", btnName);
        jsonObject1.addProperty("type", btnType);

        if (appLink != null) {
            jsonObject1.addProperty("url_mobile", appLink);
            jsonObject1.addProperty("scheme_android", appLink);
            jsonObject1.addProperty("scheme_ios", appLink);
        }

        jsonArray.add(jsonObject1);

        jsonObject.add("button", jsonArray);

        return gson.toJson(jsonObject);
    }

    /**
     * <p>바로연결 JSON을 생성합니다.</p>
     *
     * @param btnName 버튼 이름
     * @param btnType 버튼 타입
     * @param isAppLink 앱링크 여부
     * @return 도메인
     */
    public static String ofSupplementJson(String btnName, String btnType, boolean isAppLink) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject1 = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        String appLink = null;
        if (isAppLink) {
            appLink = NoticeTalk.APPLINK;
        }

        jsonObject1.addProperty("name", btnName);
        jsonObject1.addProperty("type", btnType);

        if (appLink != null) {
            jsonObject1.addProperty("url_mobile", appLink);
            jsonObject1.addProperty("scheme_android", appLink);
            jsonObject1.addProperty("scheme_ios", appLink);
        }

        jsonArray.add(jsonObject1);

        jsonObject.add("quick_reply", jsonArray);

        return gson.toJson(jsonObject);
    }

    /**
     * <p>휴면회원 전환 3개월 전 메시지를 생성합니다.</p>
     *
     * @param ymd 휴면전환일 하루전
     * @return 값
     */
    public static String getMsgByMbrDmnc(String ymd) {
        StringBuilder sb = new StringBuilder();
        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("위플 회원님께 개인정보 유효기간제 시행에 대한 안내 드립니다.\n");
        sb.append("\n");
        sb.append("AAI헬스케어에서는 정보통신망법의 개인정보유효기간제 법률을 준수하기 위하여\n");
        sb.append("앱에 1년 이상 접속하지 않은 회원님의 개인정보를 휴면 전환 후 즉시 파기합니다.\n");
        sb.append("\n");
        sb.append("지속적인 서비스를 원하신다면 " + DateFormatUtil.format(ymd, "yyyy년 MM월 dd일") + "까지 로그인 및 앱 방문을 해 주시기 바랍니다. \n");
        sb.append("\n");
        sb.append("■ 대상 회원 : 위플 앱 및 홈페이지에 1년 이상 로그인 하지 않은 회원\n");
        sb.append("■ 개인정보 파기 관리 항목 : 회원가입 시 수집된 모든 정보\n");
        sb.append("■ 보관기간 : 휴면회원 전환 시 즉시 파기\n");
        sb.append("■ 휴면회원 해제방법 : 본인확인 인증 절차 후 서비스 이용 가능\n");
        sb.append("\n");
        sb.append("개인정보 유효기간제 관련법령\n");
        sb.append("■ 정보통신망 이용촉진 및 정보보호 등에 관한 법률 제29조(개인정보의 파기)\n");
        sb.append("■ 정보통신망 이용촉진 및 정보보호 등에 관한 법률 시행령 제16조(개인정보 파기)\n");

        return sb.toString();
    }

    /**
     * <p>건강안부콜 결제 실패 메시지를 생성합니다.</p>
     *
     * @return 값
     */
    public static String getMsgByBillingFail() {
        StringBuilder sb = new StringBuilder();
        sb.append("[건강안부콜]\n");
        sb.append("\n");
        sb.append("이번달 결제가 실패하여 서비스가 자동 해지되었습니다. 결제 수단을 다시 한번 확인하신 뒤 재신청 해주세요.");

        return sb.toString();
    }

    /**
     * <p>진단솔루션 접수시 메시지를 생성합니다.</p>
     *
     * @param mbNm 회원명
     * @return 값
     */
    public static String getMsgByDsAply(String dsTp, String mbNm) {
        StringBuilder sb = new StringBuilder();

        if (StringUtil.isNotBlank(mbNm) && StringUtil.isNotBlank(dsTp)) {
            String dsNm = StringUtil.toString(BaseDomain.getDsTpMap().get(dsTp));

            if (StringUtil.equals(dsTp, MapUtil.getKey(BaseDomain.getDsTpMap(), "질병위험도예측서비스"))) {
                sb.append("[위플]\n");
                sb.append("\n");
                sb.append(mbNm + " 님.\n");
                sb.append(dsNm + " 접수가 정상적으로 처리되었습니다.\n");
                sb.append("\n");
                sb.append("* 이 서비스는 접수 후 바로 검사가 진행되므로 서비스 취소 및 환불 불가합니다.");

                return sb.toString();
            }

            if (StringUtil.equals(dsTp, MapUtil.getKey(BaseDomain.getDsTpMap(), "암질환예측서비스"))) {
                sb.append("[위플]\n");
                sb.append("\n");
                sb.append(mbNm + " 님.\n");
                sb.append(dsNm + " 접수가 정상적으로 처리되었습니다.\n");
                sb.append("\n");
                sb.append("* 이 서비스는 접수 후 바로 검사가 진행되므로 서비스 취소 및 환불 불가합니다.");

                return sb.toString();
            }

            if (StringUtil.equals(dsTp, MapUtil.getKey(BaseDomain.getDsTpMap(), "영양미네랄&중금속분석검사"))
                    || StringUtil.equals(dsTp, MapUtil.getKey(BaseDomain.getDsTpMap(), "장내미생물분석검사"))) {
                sb.append("[위플]\n");
                sb.append("\n");
                sb.append(mbNm + " 님.\n");
                sb.append(dsNm + " 접수가 정상적으로 처리되었습니다.\n");
                sb.append(mbNm + " 님의 주소지로 검사 키트가 발송 됩니다.\n");
                sb.append("설명서를 충분히 숙지 후 절차에 따라 진행해 주세요. 유의사항 미준수로 인한 검사 오류 발생 및 재검사 진행 시에는 추가 키트 비용이 청구됩니다.\n");
                sb.append("\n");
                sb.append("* 검사 환불은 서비스 구매일로부터 7일 내 가능하며, 키트 개봉 후에는 기간 상관없이 환불 불가합니다. \n");
                sb.append("* 고객 변심으로 인한 환불 시, 취소수수료 및 키트배송비 발생으로 인해 일부 금액이 차감되어 환불됩니다.\n");
                sb.append("\n");
                sb.append("보다 자세한 내용은 위플 내 '자주 묻는 질문'이나 '1:1 문의'에서 확인해주시기 바랍니다.");

                return sb.toString();
            }
        }

        return sb.toString();
    }

    /**
     * <p>진단 솔루션 환불 승인 메시지를 생성합니다.</p>
     *
     * @return 값
     */
    public static String getMsgByDsRefnd() {
        StringBuilder sb = new StringBuilder();
        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("이용권 환불이 승인되었습니다. 환불은 최초 결제 수단에 따라 영업일 기준 7일 가량 소요될 수 있습니다.");

        return sb.toString();
    }

    /**
     * <p>포인트 소멸 메시지를 생성합니다.</p>
     *
     * @param period 기간
     * @param ymd 만료일
     * @param pnt 포인트
     * @return 값
     */
    public static String getMsgByPntExp(String period, String ymd, int pnt) {
        StringBuilder sb = new StringBuilder();
        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("보유하신 포인트의 유효 기간이 " + period + " 남았습니다.\n");
        sb.append("\n");
        sb.append("포인트 만료일: " + DateFormatUtil.format(ymd, "yyyy.MM.dd") + "\n");
        sb.append("소멸 예정 포인트: " + NumberFormatUtil.format(pnt) + " point\n");
        sb.append("\n");
        sb.append("* 본 알림톡은 " + DateFormatUtil.format(ymd, "yyyyMMdd", "yyyy년 mm월 dd일") +" 기준 소멸 예정 포인트가 있는 고객에 한해 발송됩니다.\n");
        sb.append("* 만료일이 지난 포인트는 자동 소멸됩니다.");

        return sb.toString();
    }

    /**
     * <p>이용권 구매 메시지를 생성합니다.</p>
     *
     * @return 값
     */
    public static String getMsgByCupnBuy() {
        StringBuilder sb = new StringBuilder();
        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("이용권 구매가 완료되었습니다. 자세한 구매 내역은 위플 앱 내 '마이페이지>나의 이용권/결제 내역'에서 확인 가능합니다.\n");
        sb.append("\n");
        sb.append("* 본 이용권은 구매한 서비스 외 다른 서비스에는 이용 불가합니다\n");
        sb.append("* 이용권의 유효기간은 1년이며, 이후 1회에 한해 1년 연장 가능합니다.\n");
        sb.append("* 환불 등 이용권에 관한 자세한 안내는 '설정>자주 묻는 질문' 혹은 '설정>1:1 문의'를 이용해주시기 바랍니다.");

        return sb.toString();
    }

    /**
     * <p>이용권 환불 승인 메시지를 생성합니다.</p>
     *
     * @return 값
     */
    public static String getMsgByCupnRefnd() {
        StringBuilder sb = new StringBuilder();
        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("이용권 환불이 승인되었습니다. 환불은 최초 결제 수단에 따라 영업일 기준 7일 정도 소요될 수 있습니다.");

        return sb.toString();
    }

    /**
     * <p>이용권 소멸 메시지를 생성합니다.</p>
     *
     * @param period 기간
     * @param ymd 만료일
     * @return 값
     */
    public static String getMsgByCupnExp(String period, String ymd) {
        StringBuilder sb = new StringBuilder();
        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("보유하신 이용권의 유효 기간이 " + period + "남았습니다.\n");
        sb.append("\n");
        sb.append("이용권 만료일: " + DateFormatUtil.format(ymd, "yyyy.MM.dd") + "\n");
        sb.append("\n");
        sb.append("* 유효기간이 만료된 이용권은 더 이상 이용할 수 없습니다.\n");
        sb.append("* 유효기간 연장은 1회에 한해 가능하며, 연장시 1년의 유효기간이 추가됩니다. 연장 신청은 위플 앱 내 '설정>1:1문의'에서 가능합니다.");

        return sb.toString();
    }

    /**
     * <p>이용권 소멸 메시지(연장후)를 생성합니다.</p>
     *
     * @param period 기간
     * @param ymd 만료일
     * @return 값
     */
    public static String getMsgByCupnExpAftExt(String period, String ymd) {
        StringBuilder sb = new StringBuilder();
        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("보유하신 이용권의 유효 기간이 " + period + " 남았습니다.\n");
        sb.append("\n");
        sb.append("이용권 만료일: " + DateFormatUtil.format(ymd, "yyyy.MM.dd") + "\n");
        sb.append("\n");
        sb.append("* 유효기간이 만료된 이용권은 자동 소멸됩니다. 이용을 원하실 시, 유효기간 내에 꼭 사용해 주세요.");

        return sb.toString();
    }

    /**
     * <p>챗봇 인증번호</p>
     *
     * @param num 인증번호
     * @return 값
     */
    public static String getMsgByChatbotCertNum(String num) {
        StringBuilder sb = new StringBuilder();
        sb.append("인증번호는 " + num + "입니다.\n");
        sb.append("채팅창에 인증번호를 입력해 주세요.");

        return sb.toString();
    }

    /**
     * <p>챗봇 건강상담</p>
     *
     * @return 값
     */
    public static String getMsgByChatbotCnsl() {
        String result = "'상담톡 전환하기' 버튼을 누르시면 바로 건강상담을 진행할 수 있습니다.";
        return result;
    }

    /**
     * <p>건강안부콜 수신자 정보동의</p>
     *
     * @param mbNm TFA명
     * @param rcvr 수신자명
     * @return 값
     */
    public static String getMsgByMindCallProvision(String mbNm, String rcvr, String hopeYmd, String hopeTm) {
        StringBuilder sb = new StringBuilder();
        String url = "https://wiple.aaihc.com/web/mind_call/provision.do";

        sb.append("[위플 건강안부콜]\n");
        sb.append("\n");
        sb.append(mbNm + "님께서 신청하신 '건강안부콜' 서비스가 " + rcvr + "님께 제공 될 예정입니다.\n");
        sb.append("\n");
        sb.append("서비스 제공일시:\n");
        sb.append(DateFormatUtil.format(hopeYmd, "yyyy-MM-dd") + " " + hopeTm + "\n");
        sb.append("\n");
        sb.append("'건강안부콜'은 매월 1회 건강전문가(간호사 자격)가 안부 전화를 드리고, 건강상담까지 해드리는 서비스 입니다.\n");
        sb.append("\n");
        sb.append("(약관 보기 " + url + ")\n");
        sb.append("\n");
        sb.append("수신거부를 원할 시, 02-3449-3763으로 연락 주시기 바랍니다.");

        return sb.toString();
    }

    /**
     * <p>TFA 선납 신청 만료 1주일 전</p>
     *
     * @param dcnt 회차
     * @param period 기간
     * @param expYmd 만료일
     * @return 값
     */
    public static String getMsgByTfaPrpymExpBef(int dcnt, String period, String expYmd) {
        StringBuilder sb = new StringBuilder();

        sb.append("[위플]\n");
        sb.append("\n");
        sb.append(dcnt + "회차 선납형 상품의 서비스 이용 기간이 "  + period +  " 남았습니다.\n");
        sb.append("\n");
        sb.append("추가 서비스 이용을 원할 시, 만료일 이후 재구매를 해주세요.\n");
        sb.append("\n");
        sb.append("(" + dcnt + "회차 선납형 만료일: " + DateFormatUtil.format(expYmd, "yyyy.MM.dd") + ")");

        return sb.toString();
    }

    /**
     * <p>TFA 선납 신청 만료 (TFA 유저)</p>
     *
     * @param dcnt 회차
     * @return 값
     */
    public static String getMsgByTfaPrpymExp(int dcnt) {
        StringBuilder sb = new StringBuilder();

        sb.append("[위플]\n");
        sb.append("\n");
        sb.append(dcnt + "회차 선납형 상품의 서비스 이용 기간이 만료되었습니다.\n");
        sb.append("\n");
        sb.append("추가 서비스 이용을 원할 시, 재구매해주시기 바랍니다.");

        return sb.toString();
    }

    /**
     * <p>TFA 선납 신청 만료 (선납 신청 회원)</p>
     *
     * @param mbNm TFA명
     * @return 값
     */
    public static String getMsgByMbTfaPrpymExp(String mbNm) {
        StringBuilder sb = new StringBuilder();

        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("건강어드바이저 " + mbNm + "님이 신청한 선납형 상품 이용 기간이 만료되어 '위플 프리미엄' 서비스가 종료됩니다.");

        return sb.toString();
    }

    /**
     * <p>위플 프리미엄 신청</p>
     *
     * @return 값
     */
    public static String getMsgByPremiumAply() {
        StringBuilder sb = new StringBuilder();

        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("'위플 프리미엄' 서비스 신청이 완료되었습니다. 자세한 내역은 위플 앱 내 마이페이지에서 확인 가능합니다.\n");
        sb.append("\n");
        sb.append("* 환불 등 이용권에 관한 자세한 안내는 '설정>자주 묻는 질문' 혹은 '설정>1:1 문의'를 이용해주시기 바랍니다.");
        return sb.toString();
    }

    /**
     * <p>위플 프리미엄 환불</p>
     *
     * @return 값
     */
    public static String getMsgByPremiumRefnd() {
        StringBuilder sb = new StringBuilder();

        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("'위플 프리미엄' 서비스 환불이 승인되었습니다.\n");
        sb.append("\n");
        sb.append("환불은 최초 결제 수단에 따라 영업일 기준 7일 정도 소요될 수 있습니다.");
        return sb.toString();
    }

    /**
     * <p>위플 프리미엄 만료 1주일 전</p>
     *
     * @param expYmd 만료일
     * @return 값
     */
    public static String getMsgByPremiumExpBef(String expYmd) {
        StringBuilder sb = new StringBuilder();

        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("'위플 프리미엄' 이용 기간이 1주일 남았습니다.\n");
        sb.append("\n");
        sb.append("서비스를 계속 이용하고 싶으시다면 만료일 이후 마이페이지에서 위플 프리미엄 서비스를 구매하세요!\n");
        sb.append("\n");
        sb.append("이용권 만료일: " + DateFormatUtil.format(expYmd, "yyyy.MM.dd"));

        return sb.toString();
    }

    /**
     * <p>위플 프리미엄 만료</p>
     *
     * @return 값
     */
    public static String getMsgByPremiumExp() {
        StringBuilder sb = new StringBuilder();

        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("'위플 프리미엄' 이용 기간이 만료되었습니다.\n");
        sb.append("\n");
        sb.append("서비스를 계속 이용하고 싶으시다면 마이페이지에서 위플 프리미엄 서비스 이용권을 구매하세요!");

        return sb.toString();
    }

    /**
     * <p>TFA 선납 신청 입금 처리 완료2</p>
     *
     * @return 값
     */
    public static String getMsgByTfaPrepaymentAdms2() {
        String result = "아래의 내용을 '복사하기' & '붙여넣기' 기능을 활용하여 고객에게 전달해보세요!";

        return result;
    }

    /**
     * <p>TFA 선납 신청 입금 처리 완료3</p>
     *
     * @param mbNm TFA 명
     * @return 값
     */
    public static String getMsgByTfaPrepaymentAdms3(String mbNm) {
        StringBuilder sb = new StringBuilder();

        sb.append("안녕하세요 고객님, A+에셋 " + mbNm + "입니다.\n");
        sb.append("\n");
        sb.append("24시간 365일 건강전문가와 건강상담 가능한 '위플 프리미엄 회원권(1만원 상당)'을 고객님께 선물해 드렸습니다.\n");
        sb.append("\n");
        sb.append("'위플'을 다운받아 회원가입만 하시면 바로 프리미엄 서비스를 이용하실 수 있습니다.\n");
        sb.append("\n");
        sb.append("지금 바로 확인해 보세요!\n");
        sb.append("\n");
        sb.append("☞앱 실행하기\n");
        sb.append("https://link.aaihc.com/link/Eit5\n");
        sb.append("\n");
        sb.append("☞위플 주요 서비스\n");
        sb.append("24시간 365일 건강상담 / 자가 건강 문진 / 맞춤 진단 / 맞춤 운동 영상 / 1:1코칭 / 건강마당 / 걷기 미션 / 포인트 적립 / 건강안부콜\n");
        sb.append("\n");
        sb.append("☞위플 사용 방법\n");
        sb.append("https://bit.ly/3qMs4bs");

        return sb.toString();
    }

    /**
     * <p>TFA 선납 신청 입금 처리 완료</p>
     *
     * @return 값
     */
    public static String getMsgByTfaPrepaymentAdms() {
        StringBuilder sb = new StringBuilder();

        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("고객 등록 및 헬스케어서비스 신청이 완료되었습니다.\n");
        sb.append("\n");
        sb.append("※ 등록하신 고객은 위플 앱 설치 및 회원가입하셔야 서비스 이용이 가능합니다.");

        return sb.toString();
    }

    /**
     * <p>토스 캠페인 접수 완료 (선납)</p>
     *
     * @return 값
     */
    public static String getMsgByTossPremiumAply() {
        StringBuilder sb = new StringBuilder();

        sb.append("[위플]\n");
        sb.append("\n");
        sb.append("안녕하세요 고객님! AAI헬스케어입니다.\n");
        sb.append("\n");
        sb.append("24시간 365일 건강전문가와 건강 상담이 가능한 '위플 프리미엄 회원권'의 신청이 완료되었습니다.\n");
        sb.append("\n");
        sb.append("'위플'을 다운받아 회원가입만 하시면 바로 프리미엄 서비스를 이용하실 수 있습니다.\n");
        sb.append("\n");
        sb.append("지금 바로 확인해 보세요!\n");
        sb.append("\n");
        sb.append("☞앱 실행하기");
        sb.append("\n");
        sb.append("https://link.aaihc.com/link/Eit5\n");
        sb.append("\n");
        sb.append("☞ 위플 주요 서비스");
        sb.append("\n");
        sb.append("24시간 365일 건강상담 / 자가 건강 문진 / 맞춤 진단 / 맞춤 운동 영상 / 1:1코칭 / 건강마당 / 걷기 미션 / 포인트 적립 / 건강안부콜\n");
        sb.append("\n");
        sb.append("☞ 위플 사용 방법");
        sb.append("\n");
        sb.append("https://bit.ly/3qMs4bs");

        return sb.toString();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSenderKey() {
        return senderKey;
    }

    public void setSenderKey(String senderKey) {
        this.senderKey = senderKey;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSndType() {
        return sndType;
    }

    public void setSndType(String sndType) {
        this.sndType = sndType;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTmplCd() {
        return tmplCd;
    }

    public void setTmplCd(String tmplCd) {
        this.tmplCd = tmplCd;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSndMsg() {
        return sndMsg;
    }

    public void setSndMsg(String sndMsg) {
        this.sndMsg = sndMsg;
    }

    public String getSmsSndMsg() {
        return smsSndMsg;
    }

    public void setSmsSndMsg(String smsSndMsg) {
        this.smsSndMsg = smsSndMsg;
    }

    public String getSmsSndNum() {
        return smsSndNum;
    }

    public void setSmsSndNum(String smsSndNum) {
        this.smsSndNum = smsSndNum;
    }

    public String getReqDeptCd() {
        return reqDeptCd;
    }

    public void setReqDeptCd(String reqDeptCd) {
        this.reqDeptCd = reqDeptCd;
    }

    public String getReqUsrId() {
        return reqUsrId;
    }

    public void setReqUsrId(String reqUsrId) {
        this.reqUsrId = reqUsrId;
    }

    public String getReqDtm() {
        return reqDtm;
    }

    public void setReqDtm(String reqDtm) {
        this.reqDtm = reqDtm;
    }

    public String getSndDtm() {
        return sndDtm;
    }

    public void setSndDtm(String sndDtm) {
        this.sndDtm = sndDtm;
    }

    public String getRsltCd() {
        return rsltCd;
    }

    public void setRsltCd(String rsltCd) {
        this.rsltCd = rsltCd;
    }

    public String getRcptMsg() {
        return rcptMsg;
    }

    public void setRcptMsg(String rcptMsg) {
        this.rcptMsg = rcptMsg;
    }

    public String getRcptDtm() {
        return rcptDtm;
    }

    public void setRcptDtm(String rcptDtm) {
        this.rcptDtm = rcptDtm;
    }

    public String getSmsSndDtm() {
        return smsSndDtm;
    }

    public void setSmsSndDtm(String smsSndDtm) {
        this.smsSndDtm = smsSndDtm;
    }

    public String getSmsRsltCd() {
        return smsRsltCd;
    }

    public void setSmsRsltCd(String smsRsltCd) {
        this.smsRsltCd = smsRsltCd;
    }

    public String getSmsRcptMsg() {
        return smsRcptMsg;
    }

    public void setSmsRcptMsg(String smsRcptMsg) {
        this.smsRcptMsg = smsRcptMsg;
    }

    public String getSmsRcptDtm() {
        return smsRcptDtm;
    }

    public void setSmsRcptDtm(String smsRcptDtm) {
        this.smsRcptDtm = smsRcptDtm;
    }

    public String getSmsGb() {
        return smsGb;
    }

    public void setSmsGb(String smsGb) {
        this.smsGb = smsGb;
    }

    public String getSmsSndYn() {
        return smsSndYn;
    }

    public void setSmsSndYn(String smsSndYn) {
        this.smsSndYn = smsSndYn;
    }

    public long getTranSn() {
        return tranSn;
    }

    public void setTranSn(long tranSn) {
        this.tranSn = tranSn;
    }

    public String getTranSts() {
        return tranSts;
    }

    public void setTranSts(String tranSts) {
        this.tranSts = tranSts;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getSlot1() {
        return slot1;
    }

    public void setSlot1(String slot1) {
        this.slot1 = slot1;
    }

    public String getSlot2() {
        return slot2;
    }

    public void setSlot2(String slot2) {
        this.slot2 = slot2;
    }

    public String getTrTypeCd() {
        return trTypeCd;
    }

    public void setTrTypeCd(String trTypeCd) {
        this.trTypeCd = trTypeCd;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

}