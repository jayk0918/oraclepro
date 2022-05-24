package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	// 필드
	private String id = "admin";
	private String pw = "Jayk09180918";
	private String url = "jdbc:oracle:thin:@phonedb_high?TNS_ADMIN=/Users/jaykim0918/Dropbox/Wallet_phonedb";
	private String driver = "oracle.jdbc.OracleDriver";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 생성자
	// gs
	// 일반
	
	// connection
	public void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error : 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
	}
	// connection end - close
	public void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// insert
	public int dbInsert(PersonVo personVo) {
		int count = -1;

		getConnection();

		try {
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			// 실행
			count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 등록되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}
	
	// update
	public int dbUpdate(PersonVo personVo) {
		int count = -1;
		getConnection();
		try {
			String query = "";
			query += " update person ";
			query += " set name = ? ";
			query += "    ,hp = ? ";
			query += "    ,company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 수정되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}
	
	// delete
	public int dbDelete(PersonVo personVo) {
		int count = -1;
		getConnection();
		try {
			String query = "";
			query += " delete from person ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personVo.getPersonId());

			count = pstmt.executeUpdate();
			System.out.println("["+ count + "건 삭제되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
		return count;
	}
	
	// select
	public List<PersonVo> dbSelect(){
		List<PersonVo> dbList = new ArrayList<PersonVo>();
		getConnection();
		
		try {
			String query = "";
			query += " select person_id ";
			query += "     	 ,name ";
			query += "       ,hp ";
			query += "       ,company ";
			query += " from person ";
			query += " order by person_id asc ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				PersonVo personVo = new PersonVo(personId, name, hp, company);
				dbList.add(personVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return dbList;
	}
	
	// search
	public List<PersonVo> dbSearch(String search){
		List<PersonVo> dbList = new ArrayList<PersonVo>();
		getConnection();
		
		try {
			String query = "";
			query += " select person_id ";
			query += "     	 ,name ";
			query += "       ,hp ";
			query += "       ,company ";
			query += " from  person ";
			query += " where  name like ? ";
			query += "  	  or hp like ? ";
			query += "  	  or company like ? ";
			query += " order by person_id asc ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setString(3, "%" + search + "%");
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVo personSVo = new PersonVo(personId, name, hp, company);
				dbList.add(personSVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		return dbList;
	}
	
	
	
	
	
	
	
	
	
	

}// class end
