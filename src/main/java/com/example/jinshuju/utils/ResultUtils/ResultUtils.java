package com.example.jinshuju.utils.ResultUtils;

/**
 * 通用返回工具类
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @return
     */
    public static Result success() {
        Result r = new Result();
        r.setData(null);
        r.setMsg(ResultEnum.SUCCESS.getMsg());
        r.setCode(ResultEnum.SUCCESS.getCode());
        return r;
    }

    /**
     * 成功并返回自定义信息不附带数据
     *
     * @param message
     * @return
     */
    public static Result success(String message) {
        Result r = new Result();
        r.setData(null);
        r.setMsg(message);
        r.setCode(ResultEnum.SUCCESS.getCode());
        return r;
    }

    /**
     * 成功并自定义返回信息 和返回数据
     *
     * @param message
     * @param object
     * @return
     */
    public static Result success(String message, Object object) {
        Result r = new Result();
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setMsg(message);
        r.setData(object);
        return r;
    }


    /**
     * 失败
     *
     * @return
     */
    public static Result fail() {
        Result r = new Result();
        r.setCode(ResultEnum.FAIL.getCode());
        r.setMsg(ResultEnum.FAIL.getMsg());
        return r;
    }

    /**
     * 失败并返回自定义信息
     *
     * @param message
     * @return
     */
    public static Result fail(String message) {
        Result r = new Result();
        r.setCode(ResultEnum.FAIL.getCode());
        r.setMsg(message);
        return r;
    }

    /**
     * 失败并返回自定义信息和自定义code
     *
     * @param code
     * @param message
     * @return
     */
    public static Result fail(int code, String message) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(message);
        return r;
    }
}
