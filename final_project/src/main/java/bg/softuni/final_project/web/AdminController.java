package bg.softuni.final_project.web;

import bg.softuni.final_project.service.StatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final StatsService statsService;

    public AdminController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("stats", statsService.getStats());
        return "admin";
    }
}
