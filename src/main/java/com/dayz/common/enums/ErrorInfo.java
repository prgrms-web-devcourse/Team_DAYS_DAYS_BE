package com.dayz.common.enums;

import lombok.Getter;

@Getter
public enum ErrorInfo {

    UNKNOWN("UNKNOWN", "서버 에러로 인해 데이터를 로드 할 수 없습니다."),
    LOGIN_FAIL("LOGIN_FAIL", "로그인에 실패하셨습니다."),

    MEMBER_NOT_FOUND("ERR001","존재하지 않는 사용자입니다."),
    ADDRESS_NOT_FOUND ("ERR002", "존재하지 않는 지역입니다."),
    ATELIER_NOT_FOUND("ERR003", "존재하지 않는 공방입니다."),
    ONEDAYCLASS_NOT_FOUND("ERR004", "존재하지 않는 클래스입니다.");

    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

}
