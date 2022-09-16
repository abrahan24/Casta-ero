package uap.usic.siga.funciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextoFecha {
    public Date parseFecha(String fecha){
       SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
       Date fechaDate = null;
       
       try{ 
            fechaDate = formato.parse(fecha);
       }
       catch(ParseException ex){
            System.out.println(ex);
       }
       return fechaDate;
    }
}
