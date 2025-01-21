package com.hekate.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
public class JournalEntry {
    @Id
    private ObjectId id;

//    @NonNull
    private String title;

    private String content;

    private ZonedDateTime createdDate;

    private ZonedDateTime updatedDate;
}
