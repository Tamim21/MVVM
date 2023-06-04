package ViewModel;
import View.*;

import Model.ProductItem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tamim
 */
public class AddProductViewModel {
    
    private String url = "jdbc:mysql://localhost:3306/test";
    private String username = "root";
    private String password = "FOE123456789";
    private ProductItem pp = new ProductItem();
    private AddProductScreen aps;
    
    public AddProductViewModel(AddProductScreen aps){
        this.aps = aps;
    }
    
    
    public void AddProduct (){
        try {
            Connection cn = DriverManager .getConnection(url,username,password);
            PreparedStatement state1 = cn.prepareStatement("insert into Products values('"+pp.getId()+"','"+pp.getName()+"',+"+pp.getItems()+",'"+pp.getImage()+"',"+pp.getItems()+")");
            state1.execute();
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Connection Failed");
        }
    }
    public boolean checkId(String Id){ 
        try {
            Connection cn = DriverManager .getConnection(url,username,password);
            Statement state = cn.createStatement();
            ResultSet resultSet = state.executeQuery("select * from products");
            while(resultSet.next()) {
                if(resultSet.getString(1).equals(Id)){
                    cn.close();
                return true;  
                }
            } 
            
             cn.close();
        } catch (SQLException ex) {
            System.out.println("Connection Failed");
        }
        
            return false;
        
    }
    
    public int Validate(String Id,String name,String price,String image,String items){
        int x,y;
        if("".equals(Id) || "".equals(name) || "".equals(price) || "".equals(items)) return 1;
        else if(checkId(Id) || Id.length()>4) return 2; 
        try{
              x = Integer.parseInt(price);
              y = Integer.parseInt(items);
             if(x<0 || y<0) return 3;
        }catch(NumberFormatException e){
            return 4;
        }
        this.pp.setId(Id);
        this.pp.setName(name); 
        this.pp.setImage(image);
        this.pp.setPrice(x);
        this.pp.setItems(y);
        AddProduct();
        return 0;
    }
    
    
    
}
