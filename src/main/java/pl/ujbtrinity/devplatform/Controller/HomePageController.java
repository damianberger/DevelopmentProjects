package pl.ujbtrinity.devplatform.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/api/v1/admin")
    public String databaseTest() {
        return "admin/databaseTest";
    }
}
