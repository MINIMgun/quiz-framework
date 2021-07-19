package net.minimgun.quizframework.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minimgun.quizframework.models.info.entity.Info;
import net.minimgun.quizframework.models.info.repository.InfoService;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private InfoService infoService;

    @GetMapping(value = "/get/info/current", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Info> getCurrentInfo() {
        return ResponseEntity.ok(infoService.getInfo());
    }

    @GetMapping(value = "/get/info/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Info> getInfoById(@PathVariable long id) {
        return ResponseEntity.ok(infoService.getInfo(id));
    }
}
