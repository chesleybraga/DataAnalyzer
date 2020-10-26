package com.zallpy.challenge.builder;

/**
 * @author Chesley Braga
 */
public interface BuilderInterface<T> {

    T build(String line) throws Exception;
}