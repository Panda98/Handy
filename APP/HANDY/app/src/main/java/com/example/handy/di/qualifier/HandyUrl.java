package com.example.handy.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author wangziang
 * @date 2019/04/07
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface HandyUrl {
}
