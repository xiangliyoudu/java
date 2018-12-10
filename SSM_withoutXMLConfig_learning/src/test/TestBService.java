package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import webinitializertest.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class})
public class TestBService {
	//private static Logger log = Logger.getLogger(TestBService.class);
	@Test
	public void test1() {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext(
		//		"spring-mybatis.xml");
		/*IBackendUserService service = (IBackendUserService) ctx
				.getBean("iBackendUserServiceImpl");
		
		BackendUser bUser = service.findByNameAndPwd("admin", "123456");
		log.info(bUser.getUserName());*/
		
		/*AppInfoService aservice = 
				(AppInfoService) ctx.getBean("AppInfoServiceImpl");
		//log.info(aservice.findAll().size());
*/		
		/*IDataDictionaryService dservice = 
				(IDataDictionaryService) ctx.getBean("IDataDictionaryServiceImpl");
		log.info(dservice.findListByTypeCode("USER_TYPE").size());*/
		
		/*IAppCategoryService acservice = 
				(IAppCategoryService) ctx.getBean("IAppCategoryServiceImpl");
		log.info(acservice.findLevel1().size());
		log.info(acservice.findLevel2().size());*/
		
		/*IAppVersionService avservice = 
				(IAppVersionService) ctx.getBean("IAppVersionServiceImpl");
		
		log.info(avservice.findById(38).getAppName());*/
		
		
		
	}

	@Autowired
	Environment env;
	
	@Test
	public void testEnv(){
		String drName = env.getProperty("url");
		System.out.println(drName);
	}
	
}













