package com.security.demo.rest;


import com.security.demo.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperController {


    private final List<Developer> developers = Stream.of(
            new Developer(1L,"Alex","Nar"),
            new Developer(2L,"Bob","Stone"),
            new Developer(3L,"Jack", "Smith"))
            .collect(Collectors.toList());


    @GetMapping
    public List<Developer> getAll() {
        return developers;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public Developer getById(@PathVariable Long id) {
        return developers.stream().filter(developer -> developer.getId().equals(id)).findFirst().orElse(null);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public Developer create(@RequestBody Developer developer){
        this.developers.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void deleteById(@PathVariable Long id){
        this.developers.removeIf(developer -> developer.getId().equals(id));
    }

}
