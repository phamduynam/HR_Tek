package com.toprate.hr_tek_demo.utils;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 19:10 04/02/2021
 */
public final class Constants {
    public interface ROLE {
        String ADMIN = "ADMIN";
        String MANAGER = "MANAGER";
        String HR = "HR";
    }

    public interface ERROR {
        String ERROR_MESSAGE = "Có điều gì đó không ổn... vui lòng liên hệ quản trị viên để được hỗ trợ";

        int INTERNAL_SERVER_ERROR_CODE = 500;
        int NOT_FOUND_CODE = 404;
        int FORBIDDEN_CODE = 403;
        int BAD_REQUEST_CODE = 400;
        int REQUEST_TIMEOUT_CODE = 408;

        String INTERNAL_SERVER_ERROR_TEXT = "Lỗi máy chủ";
        String NOT_FOUND_TEXT = "Trang không tồn tại";
        String BAD_REQUEST_TEXT = "Yêu cầu không hợp lệ";
        String REQUEST_TIMEOUT_TEXT = "Hết thời gian yêu cầu";
        String FORBIDDEN_TEXT = "Bạn không có quyền truy cập";
    }
}
