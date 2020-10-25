//package pl.ujbtrinity.devplatform.Controller;
//
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//import pl.ujbtrinity.devplatform.service.impl.UserServiceImpl;
//
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@RestController
//public class TestController {
//
//    private final UserServiceImpl userService;
//
//    public TestController(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    private static final String TEST_USER_PROFILE_ENDPOINT = "/test/user/profile/{id}";
//
//
//    @GetMapping(TEST_USER_PROFILE_ENDPOINT)
//    public ResponseEntity<byte[]> userPhoto(@PathVariable Long id) throws IOException {
//        Path path = Paths.get("src/main/webapp/resources/images/" + id + ".png");
//        byte[] bytes = Files.readAllBytes(path);
//        return ResponseEntity
//                .ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .body(bytes);
//
//    }
//}
//
