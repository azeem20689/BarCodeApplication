package com.azeem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.azeem.dto.MasterCartonViewDto;
import com.azeem.dto.Master_Carton_Detail_View_Dto;

@Configuration
public class Dao {

	private Connection cn;
	private Statement stm;

	public Dao() {
		try {
			this.cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/barcodedb", "postgres", "postgres");
			this.stm = cn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MasterCartonViewDto> connect(String sql) throws SQLException {
		ResultSet rs = stm.executeQuery(sql);
		MasterCartonViewDto obj;
		List<MasterCartonViewDto> list = new ArrayList<>();
		while (rs.next()) {
			obj = new MasterCartonViewDto();
			obj.setKey(rs.getInt(1));
			obj.setColor(rs.getString(2));
			obj.setCommodity(rs.getString(3));
			obj.setDeleted(rs.getBoolean(4));
			obj.setEan(rs.getString(5));
			obj.setModel(rs.getString(6));
			obj.setNetweight(rs.getString(7));
			obj.setPodate(rs.getString(8));
			obj.setPono(rs.getString(9));
			obj.setTemplateid(rs.getString(10));
			obj.setTimestamp(rs.getTimestamp(11));
			obj.setTotalcarton(rs.getInt(12));
			obj.setUserid(rs.getString(13));
			obj.setLastcarton(rs.getInt(14));
			list.add(obj);
		}
		rs.close();
		return list;
	}

	
	public List<Master_Carton_Detail_View_Dto> masterCartonDetailViewDtoGetAll(String sql){
		try {
			ResultSet rs = stm.executeQuery(sql);
			Master_Carton_Detail_View_Dto obj;
			List<Master_Carton_Detail_View_Dto> list = new ArrayList<>();
			while(rs.next()) {
				obj = new Master_Carton_Detail_View_Dto();
				obj.setKey(rs.getString(1));
				obj.setColor(rs.getString(2));
				obj.setCommodity(rs.getString(3));
				obj.setDeleted(rs.getBoolean(4));
				obj.setEan(rs.getString(5));
				obj.setModel(rs.getString(6));
				obj.setNetweight(rs.getString(7));
				obj.setPodate(rs.getString(8));
				obj.setPono(rs.getString(9));
				obj.setTemplateid(rs.getString(10));
				obj.setTimestamp(rs.getTimestamp(11));
				obj.setTotalcarton(rs.getInt(12));
				obj.setUserid(rs.getString(13));
				obj.setId(rs.getInt(14));
				obj.setBarcodelist(rs.getString(15));
				obj.setCartonno(rs.getInt(16));
				obj.setGrossweight(rs.getString(17));
				obj.setMsn(rs.getString(18));
				obj.setQuantity(rs.getString(19));
				list.add(obj);
			}
			rs.close();
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public void closeConnection() {
		try {
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
