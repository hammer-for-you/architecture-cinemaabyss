package ru.hammerforyou.proxy;

/**
 * @author Maxim Nikolsky
 */
public interface TargetUrlResolver {

    String resolve(String path);
}
