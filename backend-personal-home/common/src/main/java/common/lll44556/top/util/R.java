package common.lll44556.top.util;

import common.lll44556.top.enums.RCode;
import lombok.Data;

@Data
public class R<T> {
    private Integer code;
    private String msg;
    private T data;


    private R() {}


    public static <T> R<T> of(T data, RCode rCode) {
        R<T> r = new R<T>();
        r.setCode(rCode.getCode());
        r.setMsg(rCode.getMessage());
        r.setData(data);
        return r;
    }

    public static <T> R<T> ok(T data) {
        return of(data, RCode.OK);
    }

    public static <T> R<T> ok() {
        return of(null, RCode.OK);
    }

    public static <T> R<T> fail(RCode rCode) {
        return of(null, rCode);
    }

    public static <T> R<T> fail() {
        return of(null, RCode.BAD_REQUEST);
    }
}
