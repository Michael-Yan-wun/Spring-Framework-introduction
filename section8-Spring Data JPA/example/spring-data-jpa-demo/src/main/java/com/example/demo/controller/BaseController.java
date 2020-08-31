package com.example.demo.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;


public class BaseController {

    protected int currentPage = 1;
    protected int pageSize = 15;


    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ex.printStackTrace();
        ModelAndView mav = new ModelAndView();
        mav.addObject("timestamp", new Date());
        mav.addObject("error", ex.getMessage());
        mav.addObject("status", 500);
        mav.addObject("exception", ex);
        mav.addObject("message", ex.getMessage());
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
