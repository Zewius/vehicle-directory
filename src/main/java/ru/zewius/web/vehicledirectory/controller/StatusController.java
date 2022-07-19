package ru.zewius.web.vehicledirectory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zewius.web.vehicledirectory.data.Status;
import ru.zewius.web.vehicledirectory.service.StatusService;

@RestController
@RequestMapping("api/status")
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public Status showCountOfEntries() {
        return new Status(statusService.showNumberOfEntries(), statusService.showDatabaseSize());
    }
}
