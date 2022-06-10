package io.github.xingkongqwq.xweb.core.exceptions;

/**
 * 当指定主类未继承XWebApplication时抛出
 */
public class IllegalMainClassException extends Exception {
    public IllegalMainClassException(String message) {
        super(message);
    }
}
