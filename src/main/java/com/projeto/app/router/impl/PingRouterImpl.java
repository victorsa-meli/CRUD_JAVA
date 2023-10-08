package com.projeto.app.router.impl;

import com.projeto.app.router.BaseRouter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class PingRouterImpl implements BaseRouter {
    @Override
    @GetMapping("/ping")
    public String getPing() {
        return "pong";
    }
}
