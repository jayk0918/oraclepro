package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean phone = true;
		PhoneDao phoneDao = new PhoneDao();
		
		
		System.out.println("***************************");
		System.out.println("*    전화번호 관리 프로그램    *");
		System.out.println("***************************");
		System.out.println();
		
		while(phone) {
			System.out.println("1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료" );
			System.out.println("-----------------------------------");
			System.out.print(">메뉴번호: ");
			int menu = sc.nextInt();
			
			switch(menu){
				case 1:
					System.out.println("<1.리스트>");
					List<PersonVo> dbList = phoneDao.dbSelect();
					for(int i=0; i<dbList.size(); i++) {
						int personId = dbList.get(i).getPersonId();
						String name = dbList.get(i).getName();
						String hp = dbList.get(i).getHp();
						String company = dbList.get(i).getCompany();
						
						System.out.println(personId + ".\t" + name + "\t" + hp + "\t" + company);
					}
					System.out.println();
					break;
					
				case 2:
					System.out.println("<2.등록>");
					System.out.print("이름 > ");
					sc.nextLine();
					String name = sc.nextLine();
					System.out.print("휴대전화 > ");
					String phoneNum = sc.nextLine();
					System.out.print("회사번호 > ");
					String companyNum = sc.nextLine();
					
					PersonVo personVo = new PersonVo (name, phoneNum, companyNum);
					phoneDao.dbInsert(personVo);
					System.out.println();
					
					break;
					
				case 3:
					System.out.println("<3.수정>");
					System.out.print("번호 > ");
					int listNum = sc.nextInt();
					System.out.print("이름 > ");
					sc.nextLine();
					String nameEdit = sc.nextLine();
					System.out.print("휴대전화 > ");
					String phoneEdit = sc.nextLine();
					System.out.print("회사번호 > ");
					String companyEdit = sc.nextLine();
					
					PersonVo personEVo = new PersonVo(listNum, nameEdit, phoneEdit, companyEdit);
					phoneDao.dbUpdate(personEVo);
					System.out.println();
					
					break;
					
				case 4:
					System.out.println("<4.삭제>");
					System.out.print("> 번호 : ");
					int deleteNum = sc.nextInt();
					
					PersonVo personDVo = new PersonVo(deleteNum);
					phoneDao.dbDelete(personDVo);
					System.out.println();
					break;
					
				case 5:
					System.out.println("<5.검색>");
					System.out.print("검색어 : ");
					sc.nextLine();
					String search = sc.nextLine();
					
					List<PersonVo> pSearch = phoneDao.dbSearch(search);
					
					for(int i=0; i<pSearch.size(); i++) {
						int personId = pSearch.get(i).getPersonId();
						String sName = pSearch.get(i).getName();
						String sHp = pSearch.get(i).getHp();
						String sCompany = pSearch.get(i).getCompany();
						
						System.out.println(personId + ".\t" + sName + "\t" + sHp + "\t" + sCompany);
					}
					System.out.println();
					break;
					
				case 6:
					System.out.println("*******************");
					System.out.println("*     감사합니다     *");
					System.out.println("*******************");
					phone = false;
					break;
					
				default :
					System.out.println("[다시 입력해주세요.]");
					break;
			}
		}
		
		sc.close();
	} // main end
}// class end
