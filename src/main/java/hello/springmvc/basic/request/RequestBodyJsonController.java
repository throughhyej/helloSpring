package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @ResponseBody
    @RequestMapping("/request-body-json-v1")
    public String requestBodyJsonV1(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("msgBody = > {}", msgBody);

        HelloData helloData = objectMapper.readValue(msgBody, HelloData.class);
        log.info("helloData => {}", helloData);

        return "OK";
    }


    @ResponseBody
    @RequestMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String msgBody) throws IOException {
        log.info("msgBody = > {}", msgBody);

        HelloData helloData = objectMapper.readValue(msgBody, HelloData.class);
        log.info("helloData => {}", helloData);

        return "OK";
    }


    /** @RequestBody 생략 불가능: 생략 시 @ModelAttribute로 인지 => 요청 파라미터 값 찾게됨 => 초기값 **/
    @ResponseBody
    @RequestMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("helloData => {}", helloData);
        return "OK";
    }


    @RequestMapping("/request-body-json-v4")
    public HttpEntity<String> requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        HelloData helloData = httpEntity.getBody();
        log.info("helloData => {}", helloData);
        return new HttpEntity<>("OK");
    }


    @ResponseBody
    @RequestMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("helloData => {}", helloData);
        return helloData;
    }

}
