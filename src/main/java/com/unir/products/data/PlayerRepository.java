package com.unir.products.data;

import com.unir.products.data.utils.PlayerSearchCriteria;
import com.unir.products.data.utils.SearchOperation;
import com.unir.products.data.utils.SearchStatement;
import com.unir.products.model.pojo.Player;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlayerRepository {

    private final PlayerJpaRepository repository;

    public List<Player> getPlayers() {
        return repository.findAll();
    }

    public Player getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Player save(Player player) {
        return repository.save(player);
    }

    public void delete(Player Player) {
        repository.delete(Player);
    }

    public List<Player> search(String name, String club, String position) {
        PlayerSearchCriteria<Player> spec = new PlayerSearchCriteria<>();
        if (StringUtils.isNotBlank(name)) {
            spec.add(new SearchStatement("name", name, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(club)) {
            spec.add(new SearchStatement("club", club, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(position)) {
            spec.add(new SearchStatement("position", position, SearchOperation.MATCH));
        }

        return repository.findAll(spec);
    }
}
