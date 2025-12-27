package br.secure.passaword.securepassaword.controller;

import br.secure.passaword.securepassaword.service.PassawordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final PassawordService passwordService;

    public ApiController(PassawordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/validate")
    public ResponseEntity<List<String>> isSafe(@RequestBody BodyRequest request) {

        var failures = passwordService.validate(request.password());

        if (failures.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().body(failures);
    }
}