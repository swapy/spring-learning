package com.example.multimodule.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data

@Log4j2
public class Player {
    int id;
    String name;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;

        log.info("logcheck : in constructor of Player class ");
    }


    //init and destroy methods can be private
    // spring can still find them via reflection
    // keeping them private makes sense to avoid someone calling these methods again explicitly from outside
    private void init(){
        log.info("logcheck : In init method of Player");
    }

    private void destroy(){
        log.info("logcheck : In destroy method of Player");
    }
}
