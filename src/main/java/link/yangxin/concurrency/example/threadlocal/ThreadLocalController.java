package link.yangxin.concurrency.example.threadlocal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangxin
 * @date 2019/5/27
 */
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @GetMapping("/test")
    public Long test(){
        return RequestHolder.getId();
    }

}