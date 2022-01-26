package com.cd.wj.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    private Integer code = 200;
    private String msg = "操作成功";
    private T data;

    public CommonResult(T data) {
        this.data = data;
    }

    public CommonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> CommonResult<T> error(String msg){
        return new CommonResult<>(500,msg);
    }

    public static <T> CommonResult<T> error(Integer code,String msg){
        return new CommonResult<>(code,msg);
    }

    public static <T> CommonResult<T> success(T data){
        return new CommonResult<>(data);
    }
}
