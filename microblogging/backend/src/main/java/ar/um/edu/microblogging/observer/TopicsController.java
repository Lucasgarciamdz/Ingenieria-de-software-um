package ar.um.edu.microblogging.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicsController {

  @Autowired private TopicsService topicsService;

  @PostMapping("/register")
  public void register(@RequestParam String email) {
    topicsService.registerEmail(email);
  }

//  @PostMapping("/unregister")
//  public void unregister(@RequestParam String email) {
//    topicsService.unregisterEmail(email);
//  }
}
