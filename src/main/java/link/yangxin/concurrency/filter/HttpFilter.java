package link.yangxin.concurrency.filter;

import link.yangxin.concurrency.example.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author yangxin
 * @date 2019/5/27
 */
@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("do filter,{}",Thread.currentThread().getId());
        RequestHolder.add(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest, servletResponse);

    }
}