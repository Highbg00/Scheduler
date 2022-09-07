package org.zerock.scheduler.controller;

import lombok.extern.log4j.Log4j2;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.scheduler.common.CommonService;
import org.zerock.scheduler.member.UserDTO;
import org.zerock.scheduler.member.MemberServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.UUID;


@Controller
@RequestMapping
@Log4j2
public class MainController {
    @Autowired
    private CommonService common;
    @Autowired private MemberServiceImpl service;
    private String naver_client_id = "__C0ZrY2Fgz292IoT25e";



    @GetMapping("/")
    public String list(){

        log.info("list.....");

        return "main";
    }

    @GetMapping ("/naverLogin")
    public String naverLogin( HttpSession session ) {

        // 3.4.2 네이버 로그인 연동 URL 생성하기
        String state = UUID.randomUUID().toString();

        // state 정보를 session 범위 내에서 계속 사용해야 하므로 session 에 담음
        session.setAttribute("state", state);


        // https://nid.naver.com/oauth2.0/authorize?response_type=code
        // &client_id=CLIENT_ID
        // &state=STATE_STRING
        // &redirect_uri=CALLBACK_URL

        StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/authorize?response_type=code");
        url.append("&client_id=").append(naver_client_id);
        url.append("&state=").append(state);
        url.append("&redirect_uri=http://localhost/clcd/navercallback");
        System.out.println(url.toString());
        return "redirect:" + url.toString();

    }

    // 네이버 로그인 콜백 요청
    @RequestMapping ("/navercallback")
    public String navercallback(@RequestParam(required = false) String code, String state
            , @RequestParam(required = false) String error, HttpSession session ) {

        // state 값이 맞지 않거나 error가 발생해도 토큰 발급 불가
        if ( !state.equals(session.getAttribute("state")) || error != null ) {
            return "redirect:/";	// 메인 페이지로 이동
        }

        // 개발 가이드 3.4.4 접근 토큰 발급 요청 참조

        // 접근 토큰 발급을 위한 요청문 샘플을 복사하여 붙여넣기...
        // https://nid.naver.com/oauth2.0/token?grant_type=authorization_code
        // &client_id=jyvqXeaVOVmV
        // &client_secret=527300A0_COq1_XV33cf
        // &code=EIc5bFrl4RibFls1
        // &state=9kgsGTfH4j7IyAkg

        StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
        url.append("&client_id=").append(naver_client_id);
        url.append("&client_secret=ElN15NYRu2");
        url.append("&code=").append(code);
        url.append("&state=").append(state);

        // 3.4.5 접근 토큰을 이용하여 프로필 API 호출하기
        JSONObject json = new JSONObject(common.requestAPI(url));
        String token = json.getString("access_token");
        String type = json.getString("token_type");

//		curl  -XGET "https://openapi.naver.com/v1/nid/me" \
//	      -H "Authorization: Bearer AAAAPIuf0L+qfDkMABQ3IJ8heq2mlw71DojBj3oc2Z6OxMQESVSrtR0dbvsiQbPbP1/cxva23n7mQShtfK4pchdk/rc="

        url = new StringBuffer("https://openapi.naver.com/v1/nid/me");
        json = new JSONObject(common.requestAPI(url, type + " " + token));

        if (json.getString("resultcode").equals("00")) {
            json = json.getJSONObject("response");

            // 회원정보를 DB에 담기 위해서 회원정보 데이터객체를 생성
            UserDTO vo = new UserDTO();
            // 소셜 로그인 형태를 담음.

            vo.setSocial_type("naver");
            vo.setId(json.getString("id"));
            vo.setEmail(json.getString("email"));
            vo.setUsername(json.getString("name"));
            vo.setGender(json.has("gender") && json.getString("gender").equals("F") ? "여" : "남");

            // 네이버 최초 로그인인 경우 회원 정보 저장 (insert)
            // 네비어 로그인 이력이 있어 회원정보가 있다면 변경 저장
            if ( service.member_social_email(vo) )
                service.member_social_update(vo);
            else
                service.member_social_insert(vo);

            // vo 에 담은 데이터를 session 의 loginInfo 에 담음
            session.setAttribute("loginInfo", vo);
        }

//		{
//			"resultcode": "00",
//			"message": "success",
//			"response": {
//			    "email": "openapi@naver.com",
//			    "nickname": "OpenAPI",
//			    "profile_image": "https://ssl.pstatic.net/static/pwe/address/nodata_33x33.gif",
//			    "age": "40-49",
//			    "gender": "F",
//			    "id": "32742776",
//			    "name": "오픈 API",
//			    "birthday": "10-01"
//			}
//		}

        return "redirect:/"; 	// 로그인 시 루트(home.jsp)로 이동
    }
}
