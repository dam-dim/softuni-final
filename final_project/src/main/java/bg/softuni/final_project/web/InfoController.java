package bg.softuni.final_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }
}
