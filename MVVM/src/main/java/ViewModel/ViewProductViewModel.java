package ViewModel;

import Model.ProductItem;
import View.ViewProductScreen;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Tamim
 */
public class ViewProductViewModel {
    private String url = "jdbc:mysql://localhost:3306/test";
    private String username = "root";
    private String password = "FOE123456789";
    private ArrayList <ProductItem> products = new ArrayList<>();
    private ViewProductScreen s;
    
    public ViewProductViewModel(ViewProductScreen s){
        this.s = s;
        readProducts();
    }
    
    public void readProducts(){
        products.clear();
    try {
            Connection cn = DriverManager .getConnection(url,username,password);
            Statement state = cn.createStatement();
            ResultSet resultSet = state.executeQuery("select * from products");
            while(resultSet.next()) {
                ProductItem pp = new ProductItem();
                pp.setId(resultSet.getString(1));
                pp.setName(resultSet.getString(2));
                pp.setPrice(resultSet.getInt(3));
                pp.setImage(resultSet.getString(4));
                pp.setItems(resultSet.getInt(5));
                products.add(pp);
            }
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Connection Failed");
        }
    }
    public ProductItem[] getProducts(){
        return (ProductItem[]) (products).toArray(new ProductItem[products.size()]);
    }
    
}
