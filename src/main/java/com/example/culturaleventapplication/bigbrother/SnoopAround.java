package com.example.culturaleventapplication.bigbrother;

import com.example.culturaleventapplication.User.dto.UserDto;
import com.example.culturaleventapplication.User.mapper.Mappers;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
//@AllArgsConstructor
public class SnoopAround {
    private static final Logger log = LoggerFactory.getLogger(SnoopAround.class);
    private static Mappers mappers;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
