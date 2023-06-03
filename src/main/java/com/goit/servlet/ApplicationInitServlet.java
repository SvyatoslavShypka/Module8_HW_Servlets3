package com.goit.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationInitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) {
        log.info("Started configuration init");
        log.info("Configuration init finished");
    }
}
