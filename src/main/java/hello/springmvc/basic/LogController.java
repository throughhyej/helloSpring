package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* 문자 그대로 넘겨주기 위해 @RestController 사용
 * @Controller: return String -> view 파일 찾음
 **/
@RestController
@Slf4j
public class LogController {

    // Lombok의 @Slf4j 로 대체 가능
    // public final Logger log = LoggerFactory.getLogger(getClass());
    // public final Logger log = LoggerFactory.getLogger(LogController.class);

    // default: info 설정
    // 보통 개발: debug, 운영: info
    // 설정 파일에서 설정 > logging.level.hello.springmvc=debug

    // ** 더하기 연산은 메모리 낭비

    @RequestMapping("/log-test")
    public String logProcess() {
        String str = "log method";
        log.trace(" log trace :: {} ", str);
        log.debug(" log debug :: {} ", str);
        log.info(" log info :: {} ", str);
        log.warn(" log warn :: {} ", str);
        log.error(" log error :: {} ", str);

        return "OK";
    }

}
