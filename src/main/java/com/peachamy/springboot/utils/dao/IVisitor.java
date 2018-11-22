package com.peachamy.springboot.utils.dao;



/**
 *   
 *   访问者模式接�?, 参�?�http://www.javaeye.com/topic/207092
 */

public interface IVisitor {
    public abstract void visit(IElement ielement);
}
