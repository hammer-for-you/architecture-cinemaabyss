package ru.hammerforyou.proxy;

/**
 * @author Maxim Nikolsky
 */
public interface TargetUrlResolver {

    public String resolve(String path);
}
