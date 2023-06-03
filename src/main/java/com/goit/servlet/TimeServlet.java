package com.goit.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

//@WebServlet(value = "/time")
@Slf4j
public class TimeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        log.info("INIT METHOD");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String message = String.format("GET METHOD:%s", req.getRequestURI());
        log.info(message);

        String adjustTime = "";
        Long correctTime;
        String timeZone = req.getParameter("timezone");
        LocalDateTime currentTime = LocalDateTime.now();
        ZoneId zone = ZoneId.of("UTC");
        ZonedDateTime currentUtcTime = ZonedDateTime.of(currentTime, zone);
        if (timeZone != null) {
            if (timeZone.contains("-")) {
                adjustTime = "-" + timeZone.split("-")[1];
            } else {
                adjustTime = "+" + timeZone.split(" ")[1];
            }
            try {
                correctTime = Long.parseLong(adjustTime);
                currentUtcTime = currentUtcTime.plusHours(correctTime);
            } catch (Exception e) {
                adjustTime = "";
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss zzz");

        resp.setContentType("text/html; charset=utf-8");

        try {
            resp.getWriter().write(currentUtcTime.format(formatter) + adjustTime);
            resp.getWriter().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        log.info("DESTROY METHOD");
    }
}
