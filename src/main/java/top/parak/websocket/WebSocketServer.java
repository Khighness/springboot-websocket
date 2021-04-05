package top.parak.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.parak.config.CustomEndpointConfigure;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author KHighness
 * @since 2021-04-05
 */

@RestController
@RequestMapping("/websocket")
@ServerEndpoint(value = "/websocket/{username}", configurator = CustomEndpointConfigure.class)
public class WebSocketServer {

    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 在线人数
     */
    public static AtomicLong onlineCount = new AtomicLong();

    /**
     * 在线用户
     * key:username，value:session
     */
    public static Map<String, Session> sessionStorage = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        // 用户上线
        sessionStorage.put(username, session);
        // 数量增加
        WebSocketServer.onlineCount.incrementAndGet();
        // 群发消息
        try {
            // 构建消息
            Map<String, Object> map = new HashMap<>();
            map.put("type", "onlineCount");
            map.put("onlineCount", onlineCount.get());
            map.put("username", username);
            sendMessage(session, objectMapper.writeValueAsString(map));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        logger.info("用户{}上线，SESSION_ID = {}", username, session.getId());
    }

    /**
     * 连接关闭调用
     */
    @OnClose
    public void onClose(Session session) {
        String username = "";
        for (Map.Entry<String, Session> entry : sessionStorage.entrySet()) {
            if (entry.getValue() == session) {
                username = entry.getKey();
                // 移除用户
                sessionStorage.remove(username);
                // 数量减少
                onlineCount.decrementAndGet();
                break;
            }
        }
        logger.info("用户{}下线，SESSION_ID = {}", username, session.getId());
    }

    /**
     * 收到客户端消息调用
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        // json -> hashmap
        try {
            HashMap hashMap = objectMapper.readValue(message, HashMap.class);
            Map srcUser = (Map) hashMap.get("srcUser");
            Map tarUser = (Map) hashMap.get("tarUser");

            // 如果点击自己，则为群聊
            if (srcUser.get("username").equals(tarUser.get("username"))) {
                groupChat(session, hashMap);
            } else { // 私聊
                privateChat(session, tarUser, hashMap);
            }
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 发生错误调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error(error.getMessage());
    }

    /**
     * 群发
     */
    private void sendMessage(Session session, String message) {
        for (Map.Entry<String, Session> entry : sessionStorage.entrySet()) {
            try {
                if (entry.getValue() != session) {
                    entry.getValue().getBasicRemote().sendText(message);
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 私聊
     */
    private void privateChat(Session session, Map target, HashMap message) {
        Session targetSession = sessionStorage.get(target.get("username"));
        if (targetSession == null) { // 目标用户不在线，向自己发送<对方不在线>
            try {
                // 构建消息
                Map<String, Object> map = new HashMap<>();
                map.put("type", "0");
                map.put("message", "对方不在线");
                session.getBasicRemote().sendText(objectMapper.writeValueAsString(map));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }  else {
            try {
                message.put("type", "1");
                targetSession.getBasicRemote().sendText(new ObjectMapper().writeValueAsString(message));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 群聊
     */
    private void groupChat(Session session, HashMap hashMap) {
        for (Map.Entry<String, Session> entry : sessionStorage.entrySet()) {
            if (entry.getValue() != session) {
                try {
                    hashMap.put("type", "2");
                    entry.getValue().getBasicRemote().sendText(new ObjectMapper().writeValueAsString(hashMap));
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

}
