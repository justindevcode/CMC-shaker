package com.example.Heunduljang.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess","code","message","result"})
public class BaseResponse<T> {

    private final int code;
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    /**
     * 요청에 성공한 경우
     */
    public static <T> BaseResponse<T> onSuccess(T result){
        return new BaseResponse<>(1000, true, "요청에 성공하였습니다.", result);
    }


    /**
     * 요청에 실패한 경우
     */
    public static <T> BaseResponse<T> onFailure(int code,String message,T result) {
        return new BaseResponse<>(code, false, message,result);
    }

//    public static <T> BaseResponse<T> onFailure(int code,String message) {
//        return new BaseResponse<>(code, false, message);
//    }
}
