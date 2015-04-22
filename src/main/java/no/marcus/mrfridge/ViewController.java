package no.marcus.mrfridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.Map;

@Controller
public class ViewController {

    private final AuthorizationService authorizationService;
    private final RegistrationService registrationService;

    @Autowired
    public ViewController(AuthorizationService authorizationService, RegistrationService registrationService) {
        this.authorizationService = authorizationService;
        this.registrationService = registrationService;
    }

    @RequestMapping("/view")
    public String welcome(Map<String, Object> model) {


        return "mainview";
    }

    @RequestMapping("/bla")
    @ResponseBody
    public String res() {
        return "bla";
    }

    @RequestMapping("/start")
    public String index(Model model) {
        model.addAttribute("postParam", new PostParam());
        model.addAttribute("userCredentials", new UserCredentials("",""));
        return "index";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public @ResponseBody String post(@ModelAttribute PostParam postParam) {
        return postParam.getParam();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String post(@ModelAttribute UserCredentials userCredentials, Model model) {
        registrationService.registerUser(userCredentials);
        model.addAttribute("userCredentials", new UserCredentials("",""));
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public  String login(@ModelAttribute UserCredentials userCredentials, Model model) {
        if(authorizationService.userIsAuthorized(userCredentials)) {
            model.addAttribute("welcomeMsg", "Welcome, " + userCredentials.getUsername() + "!");
            return "loginsucc";
        } else {
            return "inc";
        }
    }
}
