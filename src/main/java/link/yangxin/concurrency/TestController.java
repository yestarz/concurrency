package link.yangxin.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangxin
 * @date 2019/5/22
 */
@Controller
@Slf4j
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }

}