package org.example.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

/*
    * JPA에서 제공하는 어노테이션

    @Entity
    - 테이블과 링크될 클래스
    - 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_) 으로 테이블 이름을 매칭
    
    @Id
    - 해당 테이블의 PK 필드
    
    @GenerateValue
    - PK의 생성 규칙
    - 스프링 부트 2.0 에서는 GenerationType.IDENTITY 옵션을 추가해야 auto_increment 된다.
    
    @Column
    - 테이블의 칼럼을 나타내며, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼
    - 기본값 외에 추가로 변경이 필요한 옵션이 있을때 사용
        예) varchar(255)가 기본값, 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶은 경우에 사용. 
 */

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length=500, nullable=false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;


    // @Builder : 해당 클래스의 빌더 패턴 클래스를 생성
    //  생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String author, String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }


}
