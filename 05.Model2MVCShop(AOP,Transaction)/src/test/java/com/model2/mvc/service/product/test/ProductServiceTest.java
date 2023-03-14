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

@RunWith(SpringJUnit4ClassRunner.class) //meta데이터를 통한 wiring(생성,DI) 할 객체 구현체 지정
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })//commonservice.xml실행
public class ProductServiceTest {

	@Autowired
	@Qualifier("productServiceImpl")//productServiceImpl 생성
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
	
		//==> console 확인
		//System.out.println(user);
		
		//==> API 확인        
		//객체 x와 y가 일치함을 확인합니다.
        //x(예상 값)와 y(실제 값)가 같으면 테스트 통과
		
		//Assert.assertEquals("testProductName", prod.getProdName());
		//Assert.assertEquals("testPordDetail", prod.getProdDetail());
		//Assert.assertEquals("20230215", prod.getManuDate());
		//Assert.assertEquals(1000, prod.getPrice());
		//Assert.assertEquals("testFileName", prod.getFileName());
		
	}
	//@Test
	public void testGetProduct() throws Exception {
		
		Product prod = new Product(); 
		//==> 필요하다면...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("경기도");
//		user.setEmail("test@test.com");
		
		//user = userService.getUser("testUserId");
		prod = prodService.getProduct(10024);
		//==> console 확인
		System.out.println(prod);
		
		//==> API 확인
		Assert.assertEquals("testProductName", prod.getProdName());
		Assert.assertEquals("testPordDetail", prod.getProdDetail());
		Assert.assertEquals("20230215", prod.getManuDate());
		Assert.assertEquals(1000, prod.getPrice());
		Assert.assertEquals("testFileName", prod.getFileName());
		//객체 X 가 null이 아닌지 확인합니다.
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
		prod.setProdDetail("좋아요");
		prod.setManuDate("20230216");
		prod.setPrice(2000);
		prod.setFileName("changeFile");
		prodService.updateProduct(prod);
		
		prod = prodService.getProduct(10024);
		Assert.assertNotNull(prod);
		
		//==> console 확인
		//System.out.println(user);
			
		//==> API 확인
		Assert.assertEquals("change", prod.getProdName());
		Assert.assertEquals("좋아요", prod.getProdDetail());
		Assert.assertEquals("20230216", prod.getManuDate());
		Assert.assertEquals(2000, prod.getPrice());
		Assert.assertEquals("changeFile", prod.getFileName());
		
	 }
	 //@Test
	 public void testGetProductListAll() throws Exception{ //토탈user수
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = prodService.getProductList(search);
	 	System.out.println("여기까지?");
	 	List<Object> list = (List<Object>)map.get("list");
	 	//Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
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
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
}
