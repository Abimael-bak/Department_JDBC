package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	Connection conn = null;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				Department dep = instantiateDepartment(rs);
				
				Seller sel = instantiateSeller(rs, dep);
				
				return sel;
			}
			 return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.clossePreparedStatement(ps);
			DB.closseResultSet(rs);
		}
		
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller sel = new Seller();
		sel.setId(rs.getInt("id"));
		sel.setName(rs.getString("Name"));
		sel.setEmail(rs.getString("Email"));
		sel.setBirthDate(rs.getDate("BirthDate"));
		sel.setBaseSalary(rs.getDouble("BaseSalary"));
		sel.setDepartment(dep);
		
		return sel;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department dep) {
		
		 PreparedStatement ps = null;
		  ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name");
			ps.setInt(1, dep.getId());
			rs = ps.executeQuery();
			
			List <Seller> seller = new ArrayList<>();
			Map<Integer, Department> map = new HashMap();
			
			while(rs.next()) {
				
				Department depp = map.get(rs.getInt("DepartmentId"));
				
				if(depp == null) {
					 depp = instantiateDepartment(rs);
					 map.put(rs.getInt("DepartmentId"), depp);
				}
			   
				Seller sel = instantiateSeller(rs, depp);
				seller.add(sel);
			}
			return seller;
			
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.clossePreparedStatement(ps);
			DB.closseResultSet(rs);
		}
		
	}

}
