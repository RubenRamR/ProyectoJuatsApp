/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Run;

import LogIn.frmMainMenu;
import negocio.JANegocio;

/**
 *
 * @author santi
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        

        JANegocio negocio =new JANegocio();
        
        frmMainMenu mMenu = new frmMainMenu(negocio);
        mMenu.show();
        
        
    }
    
}
