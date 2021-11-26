import org.ciapge.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/5/7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class ConsumerTest {

    @Resource(name = "helloService")
    private HelloService helloService;

    @Test
    public void test() {
        
        String hi = helloService.say("hi");
        System.out.println("测试结果：" + hi);
    }

}
