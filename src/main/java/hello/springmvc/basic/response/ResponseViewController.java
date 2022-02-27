package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mv = new ModelAndView("response/hello");
        mv.addObject("data", "hello from modelAndView");
        return mv;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello from model, return String");
        return "response/hello";
    }


    /* view 논리 경로 + return void : 권장하지 않음
     * HttpServletRequest, OutputStream(Writer) 미사용 시, URI의 view template 호출
     **/
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello from model, return void");
    }

}
