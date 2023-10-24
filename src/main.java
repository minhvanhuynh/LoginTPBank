/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import controller.ManageLoginTBBank;
/**
 *
 * @author Admin
 */
public class main {
    public static void main(String[] args) {        
        String title= "Login Program TB Bank";
        String[] s= new String[] {"Vietnamese","English","Exit"};
        new ManageLoginTBBank(title, s).run();       
    }
}
