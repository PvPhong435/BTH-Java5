package com.java5.slide8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class HomeController {

    private final LocaleResolver localeResolver;

    // Inject LocaleResolver từ cấu hình
    public HomeController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @RequestMapping("/home/index")
    public String index(Model model, 
                        @RequestParam(defaultValue = "Asia/Ho_Chi_Minh") String timezone,
                        HttpServletRequest request) {

        // Lấy thời gian hiện tại theo timezone
        ZonedDateTime currentTime = ZonedDateTime.now(java.time.ZoneId.of(timezone));
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));

        model.addAttribute("currentTime", formattedTime);
        return "home/index";
    }

    @RequestMapping("/change-language")
    public String changeLanguage(@RequestParam String lang,
                                 @RequestParam String timezone,
                                 HttpServletRequest request, 
                                 HttpServletResponse response) {
        // Thay đổi Locale dựa trên lang (ví dụ: en, vi, ja, zh)
        Locale locale = new Locale(lang);
        localeResolver.setLocale(request, response, locale);

        // Redirect về trang chính với timezone mới
        return "redirect:/home/index?timezone=" + timezone;
    }
}
