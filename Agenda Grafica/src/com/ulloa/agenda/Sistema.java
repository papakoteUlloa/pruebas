package com.ulloa.agenda;

import com.ulloa.agenda.iu.Ventana;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sistema {

    public static void main(String arg[]) {
        try {
            Ventana ventana = new Ventana();
        } catch (Exception ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
