package uap.usic.siga.utilidades;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import uap.usic.siga.entidades.GdocConsejos;
import uap.usic.siga.entidades.GdocConvenios;
import uap.usic.siga.entidades.GdocResoluciones;
import uap.usic.siga.entidades.GdocTitulados;
import uap.usic.siga.entidades.GdocTitulos;
import uap.usic.siga.entidades.SacComprobantes;
import uap.usic.siga.entidades.ScsContrataciones;
import uap.usic.siga.entidades.ScsPrsContratados;

/**
 * Rectorado - USIC
 * Fecha: 2019-09-08
 * @author Freddy Morales
 */
public class AdjuntarArchivo {
    
    MultipartFile file; 

    public AdjuntarArchivo() {
     }
    
    public String crearSacDirectorio(String sDirectorio){
        File directorio = new File("C:/"+sDirectorio);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                  return  directorio.getPath()+"/";
            } else {
                  return null;
            }
        }
        
        return directorio.getPath()+"/";
    }
    
    public Integer adjuntarArchivo(SacComprobantes sacComprobantes, String rutaArchivo) throws FileNotFoundException, IOException{

          // Save file on system
      file = sacComprobantes.getFile();
      if (!file.getOriginalFilename().isEmpty()) {
         BufferedOutputStream outputStream = new BufferedOutputStream(
               new FileOutputStream(
                     new File(rutaArchivo, sacComprobantes.getNombreArchivo())));//file.getOriginalFilename())));
         outputStream.write(file.getBytes());
         outputStream.flush();
         outputStream.close();
      }else{
         return 0; // Error: No es posible adjuntar
      }
      
      return 1; // Adjuntado Correctamente
   }
  
   public Integer adjuntarArchivoSicoes(ScsContrataciones scsContrataciones, String rutaArchivo) throws FileNotFoundException, IOException{

          // Save file on system
      file = scsContrataciones.getFile();
      if (!file.getOriginalFilename().isEmpty()) {
         BufferedOutputStream outputStream = new BufferedOutputStream(
               new FileOutputStream(
                     new File(rutaArchivo, scsContrataciones.getNombreArchivo())));//file.getOriginalFilename())));
         outputStream.write(file.getBytes());
         outputStream.flush();
         outputStream.close();
      }else{
         return 0; // Error: No es posible adjuntar
      }
      
      return 1; // Adjuntado Correctamente
   }
    
   public Integer adjuntarFotosSicoes(ScsPrsContratados scsPrsContratados, String rutaArchivo) throws FileNotFoundException, IOException{

          // Save file on system
      file = scsPrsContratados.getFile();
      if (!file.getOriginalFilename().isEmpty()) {
         BufferedOutputStream outputStream = new BufferedOutputStream(
               new FileOutputStream(
                     new File(rutaArchivo, scsPrsContratados.getNombreArchivo())));//file.getOriginalFilename())));
         outputStream.write(file.getBytes());
         outputStream.flush();
         outputStream.close();
      }else{
         return 0; // Error: No es posible adjuntar
      }
      
      return 1; // Adjuntado Correctamente
   } 
   
   public String leerComprobante(String nombreArchivo) throws IOException{
        String filePath = "C:/SacComprobantes/";
         byte[] input_file = Files.readAllBytes(Paths.get(filePath+nombreArchivo));

        byte[] encodedBytes = Base64.getEncoder().encode(input_file);
        String encodedString =  new String(encodedBytes);
      
        return encodedString;
    }
          
   public Integer adjuntarArchivoResolucion(GdocResoluciones gdocResoluciones, String rutaArchivo) throws FileNotFoundException, IOException{

       // Save file on system
   file = gdocResoluciones.getFile();
   if (!file.getOriginalFilename().isEmpty()) {
      BufferedOutputStream outputStream = new BufferedOutputStream(
            new FileOutputStream(
                  new File(rutaArchivo, gdocResoluciones.getNombreArchivo())));//file.getOriginalFilename())));
      outputStream.write(file.getBytes());
      outputStream.flush();
      outputStream.close();
   }else{
      return 0; // Error: No es posible adjuntar
   }
   
   return 1; // Adjuntado Correctamente
}

   public Integer adjuntarArchivoConvenios(GdocConvenios gdocConvenios, String rutaArchivo) throws FileNotFoundException, IOException{
       file = gdocConvenios.getFile();
      if (!file.getOriginalFilename().isEmpty()) {
          BufferedOutputStream outputStream = new BufferedOutputStream(
            new FileOutputStream(
                  new File(rutaArchivo, gdocConvenios.getNombreArchivo())));//file.getOriginalFilename())));
      outputStream.write(file.getBytes());
      outputStream.flush();
      outputStream.close();
   }else{
      return 0; // Error: No es posible adjuntar
   }
   
   return 1; // Adjuntado Correctamente
}
   
   public Integer adjuntarArchivoTitulos(GdocTitulos gdocTitulos, String rutaArchivo) throws FileNotFoundException, IOException{
       file = gdocTitulos.getFile();
      if (!file.getOriginalFilename().isEmpty()) {
          BufferedOutputStream outputStream = new BufferedOutputStream(
            new FileOutputStream(
                  new File(rutaArchivo, gdocTitulos.getNombreArchivo())));//file.getOriginalFilename())));
      outputStream.write(file.getBytes());
      outputStream.flush();
      outputStream.close();
   }else{
      return 0; // Error: No es posible adjuntar
   }
   
   return 1; // Adjuntado Correctamente
}
   
   public Integer adjuntarArchivoTitulados(GdocTitulados gdocTitulados, String rutaArchivo) throws FileNotFoundException, IOException{
       file = gdocTitulados.getFile();
      if (!file.getOriginalFilename().isEmpty()) {
          BufferedOutputStream outputStream = new BufferedOutputStream(
            new FileOutputStream(
                  new File(rutaArchivo, gdocTitulados.getNombreArchivo())));//file.getOriginalFilename())));
      outputStream.write(file.getBytes());
      outputStream.flush();
      outputStream.close();
   }else{
      return 0; // Error: No es posible adjuntar
   }
   
   return 1; // Adjuntado Correctamente
}
}
