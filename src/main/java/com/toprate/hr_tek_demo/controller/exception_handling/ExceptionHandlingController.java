package com.toprate.hr_tek_demo.controller.exception_handling;

import com.toprate.hr_tek_demo.utils.Constants;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 17:05 17/02/2021
 */
@Controller
public class ExceptionHandlingController extends AbstractErrorController {

    public ExceptionHandlingController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        int status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        model.addAttribute("statusCode", status);
        model.addAttribute("errorMessage", Constants.ERROR.ERROR_MESSAGE);
        String error;
        switch (status) {
            case Constants.ERROR.BAD_REQUEST_CODE:
                error = Constants.ERROR.BAD_REQUEST_TEXT;
                break;
            case Constants.ERROR.NOT_FOUND_CODE:
                error = Constants.ERROR.NOT_FOUND_TEXT;
                break;
            case Constants.ERROR.INTERNAL_SERVER_ERROR_CODE:
                error = Constants.ERROR.INTERNAL_SERVER_ERROR_TEXT;
                break;
            case Constants.ERROR.REQUEST_TIMEOUT_CODE:
                error = Constants.ERROR.REQUEST_TIMEOUT_TEXT;
                break;
            case Constants.ERROR.FORBIDDEN_CODE:
                error = Constants.ERROR.FORBIDDEN_TEXT;
                break;
            default:
                Map<String, Object> errorAttributes = getErrorAttributes(request, true);
                error = (String) errorAttributes.get("error");
                break;
        }
        model.addAttribute("error", error);
        return "error/default";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}