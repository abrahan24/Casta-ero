package uap.usic.siga.entidades;

import org.springframework.web.multipart.MultipartFile;

/**
 * Rectorado - USIC
 * Fecha: 2019-08-25
 * @author Freddy Morales
 */

public class ArchivoAdjunto {
    
    private MultipartFile file;
	
    private String description;

    private String fileName;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	} 

    
}
