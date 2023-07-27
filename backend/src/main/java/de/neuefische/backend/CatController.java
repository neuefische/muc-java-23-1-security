package de.neuefische.backend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cats")
public class CatController {

    @GetMapping
    public String getCats() {
        return "Alle Katzen";
    }

    @PostMapping("/{id}")
    public String getCatById(@PathVariable String id) {
        return "Katze mit ID " + id + "!";
    }

}
