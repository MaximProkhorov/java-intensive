package ru.prokhorov.currencyratesprediction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationStartupListener {

    private final CommandExecutor executor;

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("\nВведите команду:\n");
                String command = reader.readLine();

                executor.execute(command);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
