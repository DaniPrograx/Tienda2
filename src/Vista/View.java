/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Vista;

import java.util.List;

/**
 *
 * @author Pablo
 */
public interface View<Ent>  {
     public void show(Ent ent);
    public void showMessage(String msg);
    public void showError(String err);
    public boolean validateRequired();
}
