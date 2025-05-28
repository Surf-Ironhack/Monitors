package com.Surf.monitors_surf.TestMonitors;

import com.Surf.monitors_surf.models.Monitors;
import com.Surf.monitors_surf.repository.MonitorsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class MonitorsControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MonitorsRepository monitorsRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Monitors monitors = new Monitors(3L, "Pedro", "Intermedio", 1L);
    }

    @Test
    void postCreatedMonitors() throws Exception {
        Monitors monitors = new Monitors();
        monitors.setNameStaff("Ana");
        monitors.setSpecialtyLevel("Intermedio");
        monitors.setClassesId(1L);

        String body = objectMapper.writeValueAsString(monitors);
        MvcResult mvcResult = mockMvc.perform(post("/monitors/register")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Intermedio"));
    }

    @Test
    void getMonitorsId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/monitors/5"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Intermedio"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ana"));
    }

    @Test
    public void postAndDeleteMonitors() throws Exception {
        Monitors monitors = new Monitors();
        monitors.setNameStaff("Ron");
        monitors.setSpecialtyLevel("Intermedio");
        monitors.setClassesId(1L);

        String body = objectMapper.writeValueAsString(monitors);
        MvcResult mvcResult = mockMvc.perform(post("/monitors/register")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated()).andReturn();

        mockMvc.perform(delete("/monitors/delete/11"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateMonitorLevel() throws Exception {
        Monitors monitors = new Monitors();
        monitors.setNameStaff("Susana");
        monitors.setSpecialtyLevel("Avanzado");
        monitors.setClassesId(1L);

        mockMvc.perform(patch("/monitors/10")
                        .content(objectMapper.writeValueAsString(monitors))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}

