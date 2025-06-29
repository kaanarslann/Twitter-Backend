package com.workintech.twitter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.twitter.dto.TweetPatchRequestDto;
import com.workintech.twitter.dto.TweetRequestDto;
import com.workintech.twitter.dto.TweetResponseDto;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.service.TweetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(TweetController.class)
class TweetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TweetService tweetService;

    @Autowired
    private ObjectMapper objectMapper;


    @DisplayName("Can find tweet by id")
    @Test
    void findById() throws Exception{
        TweetResponseDto responseDto = new TweetResponseDto(1L, "Test", 0, LocalDateTime.now(), null, 1L, "TestUserNick");

        Mockito.when(tweetService.findById(1L)).thenReturn(responseDto);

        mockMvc.perform(get("/tweet/findById/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.post").value("Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1));
    }

    @Test
    void findByUserId() {
    }

    @DisplayName("Can save tweet")
    @Test
    void save() throws Exception {
        TweetRequestDto requestDto = new TweetRequestDto("Test", 1L);
        TweetResponseDto responseDto = new TweetResponseDto(1L, "Test", 0, LocalDateTime.now(), null, 1L, "TestUserNick");

        Mockito.when(tweetService.save(any(TweetRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/tweet")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.post").value("Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1));
    }

    @DisplayName("Can update tweet")
    @Test
    void update() throws Exception {
        TweetPatchRequestDto patchRequestDto = new TweetPatchRequestDto("Update Test", 1L);
        TweetResponseDto responseDto = new TweetResponseDto(1L, "Update Test", 0, LocalDateTime.now(), LocalDateTime.now(), 1L, "TestUserNick");

        Mockito.when(tweetService.update(eq(1L), any(TweetPatchRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(patch("/tweet/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(patchRequestDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.post").value("Update Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1));

    }

    @DisplayName("Can delete tweet")
    @Test
    void delete() throws Exception {
        Long tweetId = 1L;
        Long userId = 1L;

        Mockito.doNothing().when(tweetService).delete(tweetId, userId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/tweet/{id}", tweetId).param("userId", userId.toString()))
                .andExpect(status().isNoContent());

        Mockito.verify(tweetService, Mockito.times(1)).delete(tweetId, userId);
    }
}