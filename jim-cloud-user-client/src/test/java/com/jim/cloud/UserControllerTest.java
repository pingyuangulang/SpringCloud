package com.jim.cloud;

import com.jim.cloud.controller.impl.UserControllerImpl;
import com.jim.cloud.response.vo.BaseSingleResponse;
import com.jim.cloud.util.FastJsonUtil;
import com.jim.cloud.vo.UserVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 用户接口测试类
 *
 * @author jim
 * @date 2019/6/24 0:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JimCloudUserClientApplication.class)
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private FastJsonUtil fastJsonUtil;

    private MockMvc mvc;

    @Before
    public void init() {
        mvc = MockMvcBuilders.standaloneSetup(new UserControllerImpl()).build();
    }

    @Test
    public void select() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/user/select/{id}")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();
        BaseSingleResponse<UserVo> userVoBaseSingleResponse = (BaseSingleResponse<UserVo>) mvcResult.getAsyncResult();
        System.out.println(fastJsonUtil.toJsonString(userVoBaseSingleResponse));
    }
}
