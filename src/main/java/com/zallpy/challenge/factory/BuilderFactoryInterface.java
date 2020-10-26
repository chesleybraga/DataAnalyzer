package com.zallpy.challenge.factory;

/**
 * @author Chesley Braga
 */
public interface BuilderFactoryInterface<T> {

    T getBuilder(String line) throws Exception;
}