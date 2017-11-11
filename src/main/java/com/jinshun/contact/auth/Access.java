package com.jinshun.contact.auth;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {

    String value() default "";

    Authorities authorities() default Authorities.AUTHORIED;
}
