package com.Sinema.demo.screenings;

import com.fasterxml.classmate.members.ResolvedParameterizedMember;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ScreeningController {

    private ScreeningServiceImpl screeningService;

    @PostMapping("/api/v1/admin/screening")
    public ResponseEntity<Screening> createScreening(@RequestBody CreateScreeningDTO newScreening){
        return ResponseEntity.ok().body(screeningService.createScreening(newScreening));
    }

    @GetMapping("/api/v1/screening/{id}")
    public ResponseEntity<Screening> getScreening(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(screeningService.getScreening(id));
    }

    @GetMapping("/api/v1/screening/{title}")
    public ResponseEntity<List<Screening>> getAllMovieScreenings(@PathVariable("title") String title){
        return ResponseEntity.ok().body(screeningService.getAllMovieScreenings(title));
    }

    @DeleteMapping("/api/v1/admin/screening/{id}")
    public ResponseEntity<String> deleteScreening(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(screeningService.deleteScreening(id));
    }

    @PutMapping("/api/v1/admin/screening/{id}")
    public ResponseEntity<Screening> changeScreeningDetails(@PathVariable("id") Long id, @RequestBody CreateScreeningDTO changedScreening){
        return ResponseEntity.ok().body(screeningService.changeScreeningDetails(id, changedScreening));
    }
}
