package br.com.orm.onetomany;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.corp.framework.utils.UUIDUtils;

import br.com.bo.IBOorm;
import br.com.orm.ORM;

public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
			new String[] {
				"classpath:/META-INF/spring/datasource-context.xml",
				"classpath:/META-INF/spring/aop-context.xml",
				"classpath:/META-INF/spring/bo-context.xml",
				"classpath:/META-INF/spring/controller-context.xml",
				"classpath:/META-INF/spring/cep-context.xml",
				"classpath:/META-INF/spring/csv-context.xml",
				"classpath:/META-INF/spring/email-context.xml",
				"classpath:/META-INF/spring/thread-local-context.xml",
				"classpath:/META-INF/spring/upload-context.xml"
			});

		BeanFactory factory = (BeanFactory) appContext;
		IBOorm boorm = (IBOorm) factory.getBean("boorm");
		
		ORM orm1 = new ORM();
		orm1.setBigDecimal(new BigDecimal(1000));
		orm1.setInteger(new Integer(1000));
		orm1.setString(new String("Alberto Cerqueira"));
		orm1.setCalendar(Calendar.getInstance());
		
		ORM orm2 = new ORM();
		orm2.setBigDecimal(new BigDecimal(2000));
		orm2.setInteger(new Integer(2000));
		orm2.setString(new String("Cerqueira Alberto"));
		orm2.setCalendar(Calendar.getInstance());
		
		EntityOneToMany entityOneToMany = new EntityOneToMany();
		entityOneToMany.setUuid(UUIDUtils.generateUUID());
		entityOneToMany.setNome("Alberto Cerqueira");
		entityOneToMany.addOrm(orm1);
		entityOneToMany.addOrm(orm2);
		
		boorm.inserir(entityOneToMany);
	}
}