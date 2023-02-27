package com.reggie_takeout.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public R<String> handleException(Exception e) {


            if(e.getMessage().contains("Duplicate entry")) {

                String[] split = e.getMessage().split(" "); // split by '
                String msg = split[9] + " already exists";


                return R.error(msg);
            }
            return R.error("unknown error");
        }


}
