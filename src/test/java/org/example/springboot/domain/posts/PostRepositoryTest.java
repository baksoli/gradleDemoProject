package org.example.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    @RunWith(SpringRunner.class)
    - 테스트를 진행할 때, JUnit에 내장된 실행자 외에 다른실행자를 실행
    - SpringRunner라는 스프링 실행자를 사용, 스프링 부트 테스트와 JUnit사이에 연결자 역할
    
    @SpringBootTest
    - 별 다른 설정 없이 @SpringBootTest 사용시 H2 데이터베이스를 자동으로 실행
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    /*
        @After
        - Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
        - 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
        
     */
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    /*
        postsRepository.save
        : 테이블 posts에 insert/update 쿼리 실행
        id 값이 있다면 update가, 없으면 insert 쿼리 실행
        
        postsRepository.findAll
        : 테이블 posts에 있는 모든 데이터 조회
     */
    
    @Test
    public void getBoardContent(){
        //given
        String title = "테스트게시글";
        String content = "테스트본문";

        postsRepository.save(Posts.builder().title(title).content(content).author("ss@gmail.com").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_reg(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,6,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
