package com.dayz.onedayclass.controller;

import com.dayz.common.aop.LoginMember;
import com.dayz.common.dto.ApiResponse;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.dto.ReadOneDayClassesByCategoryResult;
import com.dayz.onedayclass.service.OneDayClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classes")
public class OneDayClassController {

    private final OneDayClassService oneDayClassService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/categories/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<CustomPageResponse<ReadOneDayClassesByCategoryResult>> findOneDayClassesByCategory(
            @LoginMember Member member,
            @PathVariable("categoryId") Long categoryId,
            @RequestBody CustomPageRequest pageRequest) {
        CustomPageResponse<ReadOneDayClassesByCategoryResult> response = oneDayClassService
                .getOneDayClassesByCategory(member, categoryId, pageRequest.convertToPageRequest(OneDayClass.class));

        return ApiResponse.<CustomPageResponse<ReadOneDayClassesByCategoryResult>>ok(response);
    }

}
