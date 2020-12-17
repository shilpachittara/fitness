package com.iot.xeynse.exception;

import java.io.Serializable;

public class ErrorCode implements Serializable {

    private static final long serialVersionUID = 4495018255391152782L;

    // 1 - 10 Service exceptions
    public static final ErrorCode MAINTENANCE = new ErrorCode(1, "maintenance period");
    // 11 - ... Client exceptions
    public static final ErrorCode BAD_APP_VERSION = new ErrorCode(11, "unsupported app version");
    public static final ErrorCode RETRY_LIMIT_EXCEEDED = new ErrorCode(12, "retry limit exceeded");
    public static final ErrorCode NOT_PAID_ACTIVITIES = new ErrorCode(13, "not paid rides is more than 0");
    public static final ErrorCode INVALID_NUMBER = new ErrorCode(14, "invalid phone number");
    public static final ErrorCode RESERVATION_FAILED = new ErrorCode(15, "payment reservation failed");
    public static final ErrorCode TEST_PAYMENT_FAILED = new ErrorCode(16, "test payment failed");
    public static final ErrorCode INVALID_COUPON = new ErrorCode(17, "invalid coupon");


    public int code;
    public String description;

    private ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
