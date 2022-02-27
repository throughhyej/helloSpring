package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /* @Controller: String 반환 시 view 조회
     * [view 조회 하기 싫을 때]
     * 방안 1. 메소드 return type: void 후 response.getWrite().write() 사용
     * 방안 2. 클래스를 @RestController로 변경
     * 방안 3. 메소드에 @ResponseBody 사용
     **/

    /** 스프링 MVC - 기본 기능 > HTTP 요청 파라미터 - 쿼리 파라미터, HTML Form
     *  POST 일 때, /basic/hello-form.html 이용
     * **/
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }


    /* 서버단 변수명을 파라미터와 맞추지 않아도 됨 */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String membername, @RequestParam("age") int memberage) {
        log.info("username={}, age={}", membername, memberage);
        return "OK";
    }


    /* 변수명을 파라미터와 맞춰 ("username"), ("age") 생략 가능 */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "OK";
    }


    /* String, Int, Interger 등 단순타입으로 @RequestParam 생략 가능 */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "OK";
    }


    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username
            , @RequestParam(required = false) Integer age) {
        /* username의 경우, null과 ""는 다르기 때문에 ""값이 될 경우 통과됨
         * int의 경우, null 대입이 불가하기 때문에 Integer 로 타입 변경 */
        log.info("username={}, age={}", username, age);
        return "OK";
    }


    /* default value 설정 시, required 값 무의미
     * null이 아닌 빈 문자("")에도 defaultValue로 설정
     **/
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username
            , @RequestParam(defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "OK";
    }


    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "OK";
    }


    @ResponseBody
    @RequestMapping("/request-param-multiValueMap")
    public String requestParamMultiValueMap(@RequestParam MultiValueMap<String, String> multiValueMap) {
        log.info("username={}, age={}", multiValueMap.get("username"), multiValueMap.get("age"));
        return "OK";
    }


    @ResponseBody
    @RequestMapping("/model-attrivute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("HelloData = {}", helloData);
        return "OK";
    }


    @ResponseBody
    @RequestMapping("/model-attrivute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("HelloData = {}", helloData);
        return "OK";
    }

}
