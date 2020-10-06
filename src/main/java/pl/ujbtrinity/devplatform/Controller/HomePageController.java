package pl.ujbtrinity.devplatform.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

    @GetMapping("/")
    public String HomePage(){
        return "Test";
    }

}
