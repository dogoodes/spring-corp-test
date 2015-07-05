package br.com.orm;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.bo.IBOorm;

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
		
		ORM orm = new ORM();
		
		orm.setBigDecimal(new BigDecimal(1000));
		orm.setInteger(new Integer(1000));
		orm.setString(new String("Alberto Cerqueira"));
		orm.setCalendar(Calendar.getInstance());
		
		boorm.inserir(orm);
	}
}