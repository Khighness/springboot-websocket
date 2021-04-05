package top.parak.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import top.parak.websocket.WebSocketServer;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author KHighness
 * @since 2021-04-05
 */

@RestController
@RequestMapping("/websocket")
public class CommonController {

    /**
     * 登录
     */
    @RequestMapping("/login/{username}")
    public ModelAndView login(@PathVariable("username") String username) {
        return new ModelAndView("socketChart.html", "username", username);
    }

    /**
     * 登出
     */
    @RequestMapping("/logout/{username}")
    public String logout(@PathVariable("username") String username) {
        return username + "退出成功";
    }

    /**
     * 获取在线用户
     */
    @RequestMapping("/getOnlineList")
    public List<String> getOnlineList(String username) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Session> entry : WebSocketServer.sessionStorage.entrySet()) {
            if (!entry.getKey().equals(username)) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

}
