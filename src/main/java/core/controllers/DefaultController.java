package core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DefaultController {

    @RequestMapping(value="/")
    public View jspIndex() {
        return new RedirectView("/docs/");
    }

    @RequestMapping(value="/login")
    public View login() {
        return new RedirectView("/docs/");
    }

    @RequestMapping(value="/logout")
    public View logout() {
        return new RedirectView("/docs/");
    }
}
