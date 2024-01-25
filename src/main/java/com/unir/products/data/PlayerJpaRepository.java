package com.unir.products.data;

import com.unir.products.model.pojo.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PlayerJpaRepository extends JpaRepository<Player, Long>, JpaSpecificationExecutor<Player> {

    List<Player> findByName(String name);

    List<Player> findByClub(String club);

    List<Player> findByPosition(String position);

    List<Player> findByNameAndClub(String name, String club);

}