package uap.usic.siga.controladores;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fabriceci.fmc.error.FileManagerException;
import com.fabriceci.fmc.impl.LocalFileManager;

@Controller
public class FileManagerController {

	@GetMapping(value = "/file")
    public String home() {
        return "fileManager";
    }

    @RequestMapping(value = "/api")
    public void fm(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws FileManagerException {
        Map<String, String> options = new HashMap<>();

        new LocalFileManager(options).handleRequest(request, response);
    }
    
}
