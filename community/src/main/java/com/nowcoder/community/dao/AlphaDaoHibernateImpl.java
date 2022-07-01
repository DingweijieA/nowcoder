package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository("hi")       //括号内是类别名，用来根据类名获取bean
//@Scope("prototype")   每次实例化创建新对象
//@Primary 调用接口时，优先调用此实现类
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "hibernate";
    }
}
