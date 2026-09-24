package top.lll44556.auth.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.junit5.api.DBRider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import top.lll44556.auth.AuthTestApplication;

/**
 * @author liululu
 * @date 2026/9/24 16:49
 * @description:
 */

@SpringBootTest(classes = AuthTestApplication.class)
@AutoConfigureMockMvc
@DBRider
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {


    /**
     * 用于发起模拟 HTTP 请求。
     */
    @Autowired
    protected MockMvc mockMvc;

    /**
     * 使用应用实际配置的 ObjectMapper 构造 JSON 请求体。
     */
    @Autowired
    protected ObjectMapper objectMapper;


}
