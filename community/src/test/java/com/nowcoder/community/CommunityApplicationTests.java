package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
	private  ApplicationContext applicationContext;
	@Test
	void contextLoads() {
		System.out.println(applicationContext);
		AlphaDao alphaDao =applicationContext.getBean(AlphaDao.class);//通过接口实例化
		 alphaDao =applicationContext.getBean("hi",AlphaDao.class);//通过指定类名实例化
		System.out.println(alphaDao.select());
		AlphaService alphaService =applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);//被spring容器管理的bean默认只实例化一次，否则加scope("prototype")
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 this.applicationContext=applicationContext;
	}
	@Test
	public  void testBean(){


	}

	//注入这个bean,相当于直接get
	@Autowired
	@Qualifier("hi")//注入特定的接口实现类
	private  AlphaDao alphaDao;

}
