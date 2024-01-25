package com.unir.products.controller;

import com.unir.products.model.pojo.Player;
import com.unir.products.model.pojo.PlayerDto;
import com.unir.products.model.request.CreatePlayerRequest;
import com.unir.products.service.PlayersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Players Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre jugadores alojados en una base de datos en memoria.")
public class PlayersController {
    private final PlayersService service;

    @GetMapping("/players")
    public ResponseEntity<List<Player>> getPlayers(
            @RequestHeader Map<String, String> headers,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String club,
            @RequestParam(required = false) String position) {

        log.info("headers: {}", headers);
        List<Player> players = service.getPlayer(name, club, position);

        if (players != null) {
            return ResponseEntity.ok(players);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/players/{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable String playerId) {

        log.info("Request received for player {}", playerId);
        Player player = service.getPlayer(playerId);

        if (player != null) {
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/players")
    public ResponseEntity<Player> addPlayer(@RequestBody CreatePlayerRequest request) {

        Player createdPlayer = service.createPlayer(request);

        if (createdPlayer != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable String playerId) {

        Boolean removed = service.removePlayer(playerId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/players/{playerId}")
    public ResponseEntity<Player> patchPlayer(@PathVariable String playerId, @RequestBody String patchBody) {

        Player patched = service.updatePlayer(playerId, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/players/{playerId}")
    public ResponseEntity<Player> updatePlayer(@PathVariable String playerId, @RequestBody PlayerDto body) {

        Player updated = service.updatePlayer(playerId, body);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
