package pl.ujbtrinity.devplatform.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    /**
     * Inject services
     */

    /**
     * endpoints that should be here
     */

    @GetMapping("user/read/{id}")
    public String readProfile(@PathVariable String id) {
        // Here the user should see a summary of their profile
        return "user profile";
    }

    @PostMapping("user/personals/{id}")
    public String addPersonals(@PathVariable String id) {
        // supplementing basic personal data
        return "user personals/next step";
    }

    @PostMapping("user/technologies/{id}")
    public String addTechnologiesUsedByCurrentUser(@PathVariable String id) {
        // complementing the technologies used
        return "user technologies/next step";
    }

    @PostMapping("user/frameworks/{id}")
    public String addFrameworksUsedByCurrentUser(@PathVariable String id) {
        // complementing the frameworks used
        return "user frameworks/next step";
    }

    @PostMapping("user/photo/{id}")
    public String adPhotography(@PathVariable String id) {
        // adding a user's photo
        return "user photo/next step";
    }

    /**
     * This is an option to think about
     *
     creating a new entity containing additional data about the user -
     for example, an address, something about yourself; or add the appropriate fields to User entity
     */

    /**
     * endpoints for editing data
     */


}
