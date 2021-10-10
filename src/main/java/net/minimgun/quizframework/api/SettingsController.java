package net.minimgun.quizframework.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import net.minimgun.quizframework.models.info.entity.Info;
import net.minimgun.quizframework.models.info.repository.InfoService;

@RestController
@RequestMapping(value = "/settings", produces = MediaType.APPLICATION_JSON_VALUE)
public class SettingsController {

    @Autowired
    private InfoService infoService;

    @Operation(summary = "Get the most recent information")
    @GetMapping("/get/info/current")
    ResponseEntity<Info> getCurrentInfo() {
        return ResponseEntity.ok(infoService.getInfo());
    }

    @Operation(summary = "Get a Info object by its id")
    @GetMapping("/get/info/id={id}")
    ResponseEntity<Info> getInfoById(@PathVariable long id) {
        return ResponseEntity.ok(infoService.getInfo(id));
    }
}
