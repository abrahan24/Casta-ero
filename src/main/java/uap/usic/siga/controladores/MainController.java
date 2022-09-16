package uap.usic.siga.controladores;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



 @Controller
public class MainController {

    @RequestMapping(value = {"/", "", "/welcome"}, method = RequestMethod.GET)
    public String getWelcomePage(Model model,HttpSession session) {
        model.addAttribute("nombreSis", "system.name");
         model.addAttribute("sisGral", "gral");
        return "welcome";
    }
    
    @GetMapping("/gaceta")
    public String getGaceta(Model model, HttpSession session) {
    	
    	model.addAttribute("nombreSis", "system.name");
        model.addAttribute("sisGral", "gral");
    	
    	return "gacetaImp";
    }
    
}
