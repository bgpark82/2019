package com.letshadow.back.repository;

import com.letshadow.back.domain.Board;
import com.letshadow.back.domain.Person;
import com.letshadow.back.domain.Reply;
import com.letshadow.back.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void save_테스트(){
        Board board = Board.builder()
                .title("테스트")
                .createdDate(LocalDate.now())
                .content("테스트 컨텐츠입니다.")
                .build();
        Reply reply = Reply.builder()
                .title("댓글 테스트")
                .name("박병길")
                .content("댓글 내용 테스트")
                .createdDate(LocalDate.of(2020,1,22))
                .build();
        board.setReply(reply);
        Board newBoard = boardRepository.save(board);
        System.out.println(newBoard);
    }



}