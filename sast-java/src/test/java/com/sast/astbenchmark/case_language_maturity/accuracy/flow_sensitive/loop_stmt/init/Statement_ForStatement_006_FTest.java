package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.loop_stmt.init;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {Statement_ForStatement_006_F.class})
@ExtendWith(SpringExtension.class)
class Statement_ForStatement_006_FTest {
    @Autowired
    private Statement_ForStatement_006_F statement_ForStatement_006_F;

    /**
     * Method under test: {@link Statement_ForStatement_006_F#testcase(String)}
     */
    @Test
    void testTestcase() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/accuracy/flow_sensitive/loop_stmt/init/Statement_ForStatement_006_F/{cmd}", "Cmd");
        MockMvcBuilders.standaloneSetup(statement_ForStatement_006_F)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Map><status>error</status></Map>"));
    }

    /**
     * Method under test: {@link Statement_ForStatement_006_F#testcase(String)}
     */
    @Test
    void testTestcase2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/accuracy/flow_sensitive/loop_stmt/init/Statement_ForStatement_006_F/{cmd}", "Uri Variables",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(statement_ForStatement_006_F)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Map><status>error</status></Map>"));
    }
}

