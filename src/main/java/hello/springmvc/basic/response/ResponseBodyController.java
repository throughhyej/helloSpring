package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
// @RestController <= @Controller + @ResponseBody
public class ResponseBodyController {

    /* HTTP API
     * HTML이 아니라 데이터를 전달
     * -> 정적 리소스나 뷰 템플릿을 거치지 않고, 직접 HTTP 응답 메시지를 전달
     **/

    @RequestMapping("/response-body-string-v1")
    public void responseBodyStringV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("OK");
    }


    @RequestMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyStringV2(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping("/response-body-string-v3")
    public String responseBodyStringV3(){
        return "OK";
    }


    @RequestMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("user1");
        helloData.setAge(100);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK) /* 상태코드 동적 제공이 불 */
    @ResponseBody
    @RequestMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("user1");
        helloData.setAge(100);
        return helloData;
    }

}
