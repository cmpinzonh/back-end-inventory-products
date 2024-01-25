package com.unir.products.service;

import com.unir.products.model.pojo.Player;
import com.unir.products.model.pojo.PlayerDto;
import com.unir.products.model.request.CreatePlayerRequest;

import java.util.List;

public interface PlayersService {

    List<Player> getPlayer(String name, String club, String position);

    Player getPlayer(String playerId);

    Boolean removePlayer(String playerId);

    Player createPlayer(CreatePlayerRequest request);

    Player updatePlayer(String playerId, String updateRequest);

    Player updatePlayer(String playerId, PlayerDto updateRequest);
}
