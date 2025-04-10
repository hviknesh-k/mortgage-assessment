package com.assessment.api.mortgage.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static stubs.MortgageRequestSamples.*;
import static stubs.MortgageResponseSamples.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MortgageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_eligible_mortgage_scenario() throws Exception {
        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MORTGAGE_JSON_VALID_REQUEST))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(MORTGAGE_ELIGIBLE_RESPONSE, true));
    }

    @Test
    public void test_ineligible_mortgage_scenario() throws Exception {
        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MORTGAGE_JSON_NOT_ELIGIBLE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(MORTGAGE_INELIGIBLE_RESPONSE, true));
    }

    @Test
    public void test_invalid_input_scenario() throws Exception {
        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MORTGAGE_JSON_INVALID_MP))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(MORTGAGE_ERROR_RESPONSE, true));
    }
}
