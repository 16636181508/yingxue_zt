package com.zt.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult {

    private String status;
    private String message;
    private Object data;

    public CommonResult success(String status,String message,Object data){

        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(status);
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }

    public CommonResult failed(String status,String message,Object data){

        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(status);
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }
}
