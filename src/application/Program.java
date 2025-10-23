package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
	
        
        SellerDao sellerDao = DaoFactory.createSellerDao();
        
        
        System.out.println("Test 1: Seller findById");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        
        System.out.println();
        System.out.println("Test 2: Seller findByDepartment");
        Department dep = new Department(2, null);
        List<Seller>  sell = sellerDao.findByDepartment(dep);
        for(Seller obj: sell) {
        	 System.out.println(obj);
             
        }
       
        System.out.println();
        System.out.println("Test 3: Seller findAll");
        sell = sellerDao.findAll();
        for(Seller obj: sell) {
        	 System.out.println(obj);
             
        }
        
        
        System.out.println();
        System.out.println("Test 4: Seller insert"); 
        Seller newseller = new Seller(null, "Greg", "greg@gmail.com", new Date(),4000.00,dep);
        sellerDao.insert(newseller);
        System.out.println("Inserted! New id = "+ newseller.getId());
        
        System.out.println();
        System.out.println("Test 5: Seller update"); 
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
   	}

}
