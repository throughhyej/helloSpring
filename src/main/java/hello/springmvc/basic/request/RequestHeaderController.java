package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String getHeaders(HttpServletRequest request,
                             HttpServletResponse response,
                             HttpMethod httpMethod,
                             Locale locale,
                             @RequestHeader MultiValueMap<String, String> multiValueMap,
                             @RequestHeader("host") String host,
                             @CookieValue(value="cookie", required = false) String cookie
                             ) {

        log.debug("##### request => {}", request); // org.apache.catalina.connector.RequestFacade@7e3faa9e
        log.debug("##### response => {}", response); // org.apache.catalina.connector.ResponseFacade@4c53d687
        log.debug("##### httpMethod => {}", httpMethod); // GET
        log.debug("##### locale => {}", locale); // ko_KR
        log.debug("##### multiValueMap => {}", multiValueMap); // {host=[localhost:8080], connection=[keep-alive], sec-ch-ua=[" Not A;Brand";v="99", "Chromium";v="98", "Google Chrome";v="98"], sec-ch-ua-mobile=[?0], sec-ch-ua-platform=["macOS"], upgrade-insecure-requests=[1], user-agent=[Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36], accept=[text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9], sec-fetch-site=[same-origin], sec-fetch-mode=[navigate], sec-fetch-user=[?1], sec-fetch-dest=[document], referer=[http://localhost:8080/], accept-encoding=[gzip, deflate, br], accept-language=[ko-KR,ko;q=0.9], cookie=[JSESSIONID=9C20C48E64BA118CCB4594C50CA5A190]}
        log.debug("##### host => {}", host); // localhost:8080
        log.debug("##### cookie => {}", cookie); // null

        return "Headers OK";

    }
}
