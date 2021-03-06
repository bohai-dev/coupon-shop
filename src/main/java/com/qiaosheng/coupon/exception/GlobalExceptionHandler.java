package com.qiaosheng.coupon.exception;

import com.qiaosheng.coupon.vo.ResponseHeader;
import javafx.scene.shape.VLineTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by cxy on 2018/11/6
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常处理
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = CouponException.class)
    @ResponseBody
    public ResponseHeader handleException(HttpServletRequest req,CouponException e){

        ResponseHeader header = new ResponseHeader();
        header.setErrorCode(e.getErrorCode());
        header.setErrorMsg(e.getErrorMsg());

        LOGGER.error(e.getErrorCode()+":"+e.getErrorMsg());

        return header;
    }

    /**
     * 参数校验异常处理
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseHeader handleValidateException(HttpServletRequest req,MethodArgumentNotValidException e){

        ResponseHeader header = new ResponseHeader();

        BindingResult result=e.getBindingResult();
        if (result.hasErrors()){
            List<ObjectError> errors=result.getAllErrors();

            String errMsg=errors.get(0).getDefaultMessage();

            header.setErrorCode(ErrorConstant.PARAMS_VALIDATE_ERROR.getErrorCode());
            header.setErrorMsg(errMsg);

        }



        return header;
    }


   /* @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseHeader handleException(HttpServletRequest req,Exception e){
        ResponseHeader header = new ResponseHeader();
        header.setErrorCode(ErrorConstant.LOGIN_CHECK_ERROR.getErrorCode());
        header.setErrorMsg(e.getMessage());
        return header;
    }
*/



}
