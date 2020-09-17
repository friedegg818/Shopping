package shopping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PayImpl implements Pay{
	  private Map<String, ProductVO> pmap;
	   private Map<String, OrderVO> omap;
	   private Map<Integer, PayVO> paymap = new HashMap<>();

	   private Product product;

	   private int tot = 0;
	   private ProductVO pvo;
	   private OrderVO ovo;

	   public PayImpl(Product product) {
	      this.product = product;
	   }

	   @Override
	   public void pTotalList() {	// 총 매출 리스트

		  omap = product.omap();
		  int cnt=0;
		   
	      System.out.println("\n===== 총 매출 리스트  =====");
	      System.out.print("번호\t제품명\t제품가격\t아이디");

	      Iterator<String> it = pmap.keySet().iterator();

	      while (it.hasNext()) {
	         
	    	 String key = it.next();
	    	 ovo = omap.get(key);
	        
	         // Ordern에서 가져와야함
	    	 System.out.print(cnt+"\t");
	         System.out.print(ovo.getPname() + "\t");
	         System.out.print(ovo.getPrice() + "\t");
	         System.out.print(ovo.getUserId() + "\t");
	         
	         tot = ovo.getPrice();
	         
	         cnt++;
	         
	      }

	      System.out.println("총매출 : " + tot);

	      System.out.println();
	   }


	   @Override
	   public void pList() {

		  pSell(); 
		   
		  System.out.println("\n===== 전체 물건 리스트  =====");
	      System.out.print("제품명\t팔린수량\t팔린집계\t순위");
	      
	      // 리스트 출력
	      Iterator<Integer> it2 = paymap.keySet().iterator();
	      while (it2.hasNext()) {
	         Integer key = it2.next();
	         PayVO vo = paymap.get(key);

	         System.out.print(vo.getpName() + "\t");
	         System.out.print(vo.getCnt() + "\t"); // 팔린수량가져와야함
	         System.out.print(vo.getTot()+"\t");
	   

	      }
	      System.out.println();
	   }

	   
	   @Override
	   public void pSell() {
		   
		   omap = product.omap();
		   
			  
		   int cnt = 0;
		   pmap = product.pmap();
		   
		   
		   // 제품 목록을 가져오는 Iterator
		   Iterator<String> it2 = pmap.keySet().iterator();
		   while (it2.hasNext()) {
			   String key = it2.next();
			   pvo = pmap.get(key);
			   PayVO vo = new PayVO();
			   
			   vo.setpName(pvo.getpName());
			   vo.setPrice(pvo.getPrice());
			   vo.setTot(0);
			   vo.setCnt(0);

			   paymap.put(cnt,vo);
			   cnt++;
		   }
		   
		   
		   // 제품의 orderlist를 가져오는 Iterator
		   Iterator<String> it1 = omap.keySet().iterator();
		   while (it1.hasNext()) {
			   String key = it1.next();
			   ovo = omap.get(key);
			 
			   // orderlist 제품이름으로 팔린 수량을 확인하는것
			   Iterator<Integer> it3 = paymap.keySet().iterator();
			   while (it3.hasNext()) {
				   int key1 = it3.next();
				   PayVO vo = paymap.get(key1);
				   
				   if(vo.getpName() ==  ovo.getPname()) {
					   vo.setCnt(vo.getCnt()+1);
					   vo.setTot(vo.getTot()+vo.getPrice());
				   }
				   				   
			   }
			   
		   }
		   
		  
		   
	   }

	   @Override
	   public void pRank() {
		   		   
		  // 보류
		   Iterator<Integer> it = paymap.keySet().iterator();
		   while (it.hasNext()) {
			   int key1 = it.next();
			   PayVO vo = paymap.get(key1);
			   
			   
			   
		   }
		
		   
		  
	   }


	
}
