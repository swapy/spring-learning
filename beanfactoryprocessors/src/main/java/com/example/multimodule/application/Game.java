package com.example.multimodule.application;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class Game {
    int gameId;
    List<Player> playerList;

    public Game(int id, List<Player> playerList) {
        this.gameId = id;
        this.playerList = playerList;

        log.info("logcheck : in constructor of Game class ");
    }


    private void init(){
        log.info("logcheck : In init method of Game");
    }

    private void destroy(){
        log.info("logcheck : In destroy method of Game");
    }
}
