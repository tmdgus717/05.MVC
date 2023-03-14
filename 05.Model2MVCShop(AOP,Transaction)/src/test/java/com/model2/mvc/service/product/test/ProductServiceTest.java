package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

@RunWith(SpringJUnit4ClassRunner.class) //meta�����͸� ���� wiring(����,DI) �� ��ü ����ü ����
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })//commonservice.xml����
public class ProductServiceTest {

	@Autowired
	@Qualifier("productServiceImpl")//productServiceImpl ����
	private ProductService prodService;
	
	//@Test
	public void testAddProduct() throws Exception {
		
		Product prod = new Product(); 
		prod.setProdName("testProductName");
		prod.setProdDetail("testPordDetail");
		prod.setManuDate("20230215");
		prod.setPrice(1000);
		prod.setFileName("testFileName");
		
		prodService.addProduct(prod);
		
		//prod = prodService.getProduct(10025);
	
		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��        
		//��ü x�� y�� ��ġ���� Ȯ���մϴ�.
        //x(���� ��)�� y(���� ��)�� ������ �׽�Ʈ ���
		
		//Assert.assertEquals("testProductName", prod.getProdName());
		//Assert.assertEquals("testPordDetail", prod.getProdDetail());
		//Assert.assertEquals("20230215", prod.getManuDate());
		//Assert.assertEquals(1000, prod.getPrice());
		//Assert.assertEquals("testFileName", prod.getFileName());
		
	}
	//@Test
	public void testGetProduct() throws Exception {
		
		Product prod = new Product(); 
		//==> �ʿ��ϴٸ�...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("��⵵");
//		user.setEmail("test@test.com");
		
		//user = userService.getUser("testUserId");
		prod = prodService.getProduct(10024);
		//==> console Ȯ��
		System.out.println(prod);
		
		//==> API Ȯ��
		Assert.assertEquals("testProductName", prod.getProdName());
		Assert.assertEquals("testPordDetail", prod.getProdDetail());
		Assert.assertEquals("20230215", prod.getManuDate());
		Assert.assertEquals(1000, prod.getPrice());
		Assert.assertEquals("testFileName", prod.getFileName());
		//��ü X �� null�� �ƴ��� Ȯ���մϴ�.
		Assert.assertNotNull(prodService.getProduct(10000));
		Assert.assertNotNull(prodService.getProduct(10001));
	}
	@Test
	public void testUpdateUser() throws Exception{
	
		Product prod = prodService.getProduct(10024);
//		Assert.assertNotNull(prod);
//		
//		Assert.assertEquals("testProductName", prod.getProdName());
//		Assert.assertEquals("testPordDetail", prod.getProdDetail());
//		Assert.assertEquals("20230215", prod.getManuDate());
//		Assert.assertEquals(1000, prod.getPrice());
//		Assert.assertEquals("testFileName", prod.getFileName());

		prod.setProdName("change");
		prod.setProdDetail("���ƿ�");
		prod.setManuDate("20230216");
		prod.setPrice(2000);
		prod.setFileName("changeFile");
		prodService.updateProduct(prod);
		
		prod = prodService.getProduct(10024);
		Assert.assertNotNull(prod);
		
		//==> console Ȯ��
		//System.out.println(user);
			
		//==> API Ȯ��
		Assert.assertEquals("change", prod.getProdName());
		Assert.assertEquals("���ƿ�", prod.getProdDetail());
		Assert.assertEquals("20230216", prod.getManuDate());
		Assert.assertEquals(2000, prod.getPrice());
		Assert.assertEquals("changeFile", prod.getFileName());
		
	 }
	 //@Test
	 public void testGetProductListAll() throws Exception{ //��Żuser��
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = prodService.getProductList(search);
	 	System.out.println("�������?");
	 	List<Object> list = (List<Object>)map.get("list");
	 	//Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = prodService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
}
