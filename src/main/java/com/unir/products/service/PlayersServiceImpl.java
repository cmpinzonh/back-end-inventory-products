package com.unir.products.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.products.data.PlayerRepository;
import com.unir.products.model.pojo.Player;
import com.unir.products.model.pojo.PlayerDto;
import com.unir.products.model.pojo.Product;
import com.unir.products.model.request.CreatePlayerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
@Slf4j
public class PlayersServiceImpl implements PlayersService{

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public List<Player> getPlayer(String name, String club, String position) {
        if (StringUtils.hasLength(name) || StringUtils.hasLength(club) || StringUtils.hasLength(position)
               ) {
            return repository.search(name, club, position);
        }
        List<Player> players = repository.getPlayers();
        return players.isEmpty() ? null : players;
    }

    @Override
    public Player getPlayer(String playerId) {
        return repository.getById(Long.valueOf(playerId));
    }

    @Override
    public Boolean removePlayer(String playerId) {
        Player player = repository.getById(Long.valueOf(playerId));

        if (player != null) {
            repository.delete(player);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Player createPlayer(CreatePlayerRequest request) {
        if (request != null && StringUtils.hasLength(request.getName().trim())
                && StringUtils.hasLength(request.getClub().trim())
                && StringUtils.hasLength(request.getPosition().trim())
                && StringUtils.hasLength(String.valueOf(request.getOverall()).trim())
                && StringUtils.hasLength(String.valueOf(request.getPace()).trim())
                && StringUtils.hasLength(String.valueOf(request.getShooting()).trim())
                && StringUtils.hasLength(String.valueOf(request.getPassing()).trim())
                && StringUtils.hasLength(String.valueOf(request.getDribbling()).trim())
                && StringUtils.hasLength(String.valueOf(request.getDefending()).trim())
                && StringUtils.hasLength(String.valueOf(request.getPhysicality()).trim())
                && StringUtils.hasLength(request.getImage().trim())

        ) {
            Player player = Player.builder().name(request.getName())
                    .club(request.getClub())
                    .position(request.getPosition())
                    .overall(request.getOverall())
                    .pace(request.getPace())
                    .shooting(request.getShooting())
                    .passing(request.getPassing())
                    .dribbling(request.getDribbling())
                    .defending(request.getDefending())
                    .physicality(request.getPhysicality())
                    .build();

            return repository.save(player);
        } else {
            return null;
        }
    }

    @Override
    public Player updatePlayer(String playerId, String updateRequest) {
        Player player = repository.getById(Long.valueOf(playerId));
        if (player != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(updateRequest));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(player)));
                Player patched = objectMapper.treeToValue(target, Player.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating product {}", player, e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Player updatePlayer(String playerId, PlayerDto updateRequest) {
        Player player = repository.getById(Long.valueOf(playerId));
        if (player != null) {
            player.update(updateRequest);
            repository.save(player);
            return player;
        } else {
            return null;
        }
    }
}
