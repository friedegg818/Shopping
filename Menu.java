package shopping;

import java.util.Scanner;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	private int ch;
	
	private UserService userService;
	private Product product = new ProductImpl();
	
	public Menu(UserService userService){
		this.userService = userService;
	}

	private ProductService productservice = new ProductService(product);
	private Board board = new BoardImpl(product);
	private Pay pay = new PayImpl(product);
	

	public void userMenu() {
		
		System.out.println("'"+userService.loginMember().getName()+"'님 환영합니다.");
		board.listBoard();
		product.printProduct();
		
		while(true) {
			System.out.println("\n메인메뉴 ▶ 회원메뉴");
			
			
			do {
				System.out.print("1.장바구니담기  2.마이페이지  3.장바구니   4.게시판보기  5.후기작성  6.회원탈퇴  7.로그아웃 => ");
				ch=sc.nextInt();
			}while(ch<1 || ch>7);
			
			if(ch==7) {
				userService.logout();
				break;
			}
			
			switch(ch) {
			case 1: cartMenu(); break;
			case 2: userService.MypageUser(); break;
			case 3: product.cartlist(userService.loginMember().getId()); break;
			case 4: board.boardList(); break;
			case 5: board.qAndainsert(userService.loginMember()); break;
			case 6: userService.delete(); break;
			}
		}
	}


	public void adminMenu() {
		
		while (true) {
			System.out.println("\n메인메뉴 ▶ 관리자메뉴");
			
			do {
				System.out.print("1.제품관리  2.주문관리 3.가계부 4.회원관리  5.게시판관리  6.메인메뉴로 => ");
				ch = sc.nextInt();
			}while(ch<1 || ch>6);
			
			if (ch==6) {
				userService.logout();
				break;
			}

			switch (ch) {
			case 1: productMenu(); break;
			case 2:  break;
			case 3: salesMenu(); break;
			case 4: memberMenu(); break;
			case 5: boardMenu(); break;
			}

		}
	}
	
	public void productMenu() {
		int ch;
		
		while(true) {
			System.out.println("제품 관리");
			do { 
				System.out.print("1.상품 리스트 2.상품 등록 3.상품 정보 수정 4.상품 삭제 5.상품 검색 6.뒤로가기 => ");
				ch = sc.nextInt();
			} while (ch < 1 || ch > 6);
			
			if(ch==6) { 
				break;
			}
			
			switch(ch) {
			case 1: product.printProduct(); break;
			case 2: productservice.joinProduct(); break;
			case 3: productservice.updateProduct(); break;
			case 4: productservice.deleteProduct(); break;
			case 5: productservice.findProduct(); break;

			}
		}
}
	
	
	public void boardMenu() {
		while(true) {
			do {
				System.out.println("\n메인메뉴 ▶ 관리자메뉴 ▶ 게시판관리");
				System.out.print("1.공지사항등록  2.게시글보기  3.Q&A답변달기&조회 4.게시글삭제  5.후기에답글  6.관리자메뉴로 => ");			
				ch=sc.nextInt();
			}while(ch<1 || ch>6);
		
			if(ch==6) break;
		
			switch(ch) {
			case 1 : board.notice(); break;
			case 2 : board.boardList(); break;
			case 3 : board.qAnda(); break;
			case 4 :  break;
			case 5 :  break;
			}
		}
	}
	
	public void memberMenu() {
				
		do {
			System.out.println("\n메인메뉴 ▶ 관리자메뉴 ▶ 회원관리");
			System.out.print("1.회원리스트  2.회원검색  3.관리자메뉴로 => ");
			ch=sc.nextInt();
		}while(ch<1 || ch>3);
		
		if(ch==3) return;
		
		switch(ch) {
		case 1 : userService.userList(); break;
		case 2 : userService.search(); break;
		}
	}
	
	public void cartMenu() {
		
		product.printProduct();
		
		do {
			System.out.println("\n 장바구니메뉴 ");
			System.out.println("1. 상품담기 2. 장바구니 리스트보기 3.메인으로");
			ch = sc.nextInt();
		}while(ch<1 || ch>3);
		
		if(ch==3) return;
		
		switch(ch) {
		case 1 : product.cart(); break;
		case 2 : product.cartlist(userService.loginMember().getId()); break;
		}
	}

	
	public void salesMenu() {
		
		do {
			System.out.println("\n메인메뉴 ▶ 관리자메뉴 ▶ 가계부메뉴");
			System.out.println("1.총매출리스트 2.전제품 매출순위 3.관리자메뉴로");
			ch = sc.nextInt();
		} while (ch<1 || ch>3);
		
		if(ch==3) return;
		
		switch(ch) {
		case 1 : pay.pTotalList(); break;
		case 2 : pay.pList(); break;
		}
		
	}
	
	
	
}
