package com.example.Heunduljang.user.controller;

import com.example.Heunduljang.common.base.BaseResponse;
import com.example.Heunduljang.user.dto.request.LoginRequestDto;
import com.example.Heunduljang.user.dto.request.SignUpRequestDto;
import com.example.Heunduljang.user.dto.response.SignUpResponseDto;
import com.example.Heunduljang.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags="유저")
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    /**
     * 로그인
     */
    @ApiOperation(
            value = "로그인",
            notes = "로그인 하는 API"
    )
    @GetMapping("/login")
    public BaseResponse<String>login(){
        return BaseResponse.onSuccess("로그인 성공");
    }

    /**
     * 회원가입
     */
    @ApiOperation(
            value = "회원가입",
            notes = "회원가입 하는 API"
    )
    @PostMapping("/signUp")
    public BaseResponse<SignUpResponseDto>signUp(@Validated @RequestBody SignUpRequestDto signUpRequestDto
            , BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
            return BaseResponse.onFailure(400, objectError.getDefaultMessage(), null);
        }

        // 회원 가입 비즈니스 로직
        SignUpResponseDto result = userService.signUp(signUpRequestDto);

        return BaseResponse.onSuccess(result);
    }

    /**
     *
     */

}
