package Model;

import View.*;
import ViewModel.AddProductViewModel;

/**
 *
 * @author Tamim
 */
                            //THIS CLASS IS ONLY FOR TESTING
public class main {
    public static void main(String[] args){
        
        AddProductViewModel v = new AddProductViewModel(new AddProductScreen());
        System.out.print(v.checkId("1000"));
        
   
}
}
