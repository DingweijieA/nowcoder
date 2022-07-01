package com.nowcoder.community.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class AlphaService {
    public AlphaService(){
        System.out.println("service构造器");
    }

    @PostConstruct//在构造器之后调用，一般用来做初始化
    public void init(){
        System.out.println("初始化service");
    }

    @PreDestroy//在销毁之前调用
    public  void  destory(){
        System.out.println("销毁service");

    }

}
