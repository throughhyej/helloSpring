package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
public class RequestMappingController {

    @GetMapping
    public String getUsers() {
        log.debug("##### 멤버 목록 조회 :: getUsers");
        return "GET :: getUsers";
    }

    @PostMapping
    public String postUser(@RequestParam("id") String id) {
        log.debug("##### 멤버 등록 :: postUsers");
        return "POST :: postUsers => " + id;
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable String userId) {
        log.debug("##### 특정 멤버 조회 :: getUser");
        return "GET :: getUser => " + userId;
    }

    @PatchMapping("/{userId}")
    public String patchUser(@PathVariable String userId) {
        log.debug("##### 멤버 정보 수정 :: patchUser");
        return "PATCH :: patchUser => " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        log.debug("##### 멤버 삭제 :: deleteUser");
        return "DELETE :: deleteUser => " + userId;
    }

}
