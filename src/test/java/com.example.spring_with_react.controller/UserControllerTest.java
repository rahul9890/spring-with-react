package com.example.spring_with_react.controller;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.example.spring_with_react.model.response.createUser.UserResponse;
import com.example.spring_with_react.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    void setUpLogSpy() {
        Logger rootLogger = (Logger) LoggerFactory.getLogger("com.example.spring_with_react");
        listAppender = new ListAppender<>();
        listAppender.start();
        rootLogger.addAppender(listAppender);
    }

    @Test
    @DisplayName("GET /users - should log correlation ID and method name")
    public void testFindAllUsersLogsWithCorrelationId() throws Exception {
        // Arrange
        UserResponse user1 = new UserResponse();
        user1.setUserName("Rahul");
        user1.setUserEmail("rahul@example.com");

        UserResponse user2 = new UserResponse();
        user2.setUserName("Amit");
        user2.setUserEmail("amit@example.com");

        when(userService.findAllUsers()).thenReturn(Arrays.asList(user1, user2));

        // Act
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Assert logs
        List<ILoggingEvent> logs = listAppender.list;

        assertThat(logs).anyMatch(event ->
                event.getFormattedMessage().contains("Logging before execution of method: findAllUsers")
        );

        assertThat(logs).anyMatch(event ->
                event.getMDCPropertyMap().containsKey("correlationId") &&
                        event.getMDCPropertyMap().get("correlationId").matches("[a-f0-9\\-]{36}")
        );
    }
}
