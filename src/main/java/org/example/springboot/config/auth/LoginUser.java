package org.example.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    @Target(ElementType.PARAMETER)
    - 어노테이션이 생성될 수 있는 위치를 지정
    - PARAMETER 지정시 메소드의 파라미터로 선언된 객체에서만 사용할 수 있다.

    @interface
    - 이 파일을 인터페이스 클래스로 지정
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
