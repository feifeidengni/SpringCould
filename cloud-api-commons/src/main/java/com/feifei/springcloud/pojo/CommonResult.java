package com.feifei.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 *  返回 前端的 数据
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;    // 类似于 404、200 等  代码
    private String  message; // 异常信息
    private T       data;    //携带的某一个实体参数

    /**
     * 防止 实体类为空 可调用的 构造方法
     */
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}