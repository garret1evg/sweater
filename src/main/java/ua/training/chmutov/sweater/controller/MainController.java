package ua.training.chmutov.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.chmutov.sweater.domain.Message;
import ua.training.chmutov.sweater.domain.User;
import ua.training.chmutov.sweater.repos.MessageRepo;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String,Object> model) {
        return "greeting";
    }
    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
            Model model){
        Iterable<Message> messages = messageRepo.findAll();

        if (filter == null|| filter.isEmpty())
            messages = messageRepo.findAll();
        else
            messages = messageRepo.findByTag(filter);

        model.addAttribute("messages",messages);
        model.addAttribute("filter",filter);
        return "main";
    }
    @PostMapping("/main")
    public String add (
                        @AuthenticationPrincipal User user,
                        @RequestParam String text,
                        @RequestParam String tag,
                        Map<String,Object> model){
        Message message = new Message(text,tag,user);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages",messages);

        return "main";
    }


}