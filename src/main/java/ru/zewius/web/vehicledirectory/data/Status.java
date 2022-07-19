package ru.zewius.web.vehicledirectory.data;

import lombok.Data;

@Data
public class Status {
    private final long numberOfEntries;
    private final String databaseSize;
}
