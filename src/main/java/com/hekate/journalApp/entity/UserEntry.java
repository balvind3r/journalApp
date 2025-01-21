package com.hekate.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user_data")
@Data
public class UserEntry {
    @Id
    private ObjectId Id;
    @NonNull
    @Indexed(unique = true)
    private String userName;

    @NonNull
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
}




//        In PostgreSQL, you can achieve similar functionality to MongoDB's `@DBRef` by using foreign key relationships. Here is how you can modify your `UserEntry` and `JournalEntry` entities to use JPA annotations for relational mapping:
//
//        1. Modify the `UserEntry` class to use `@OneToMany` and `@JoinColumn` annotations.
//2. Modify the `JournalEntry` class to include a reference to the `UserEntry`.
//
//Here are the changes:
//
//        **UserEntry.java**
//        ```java
//package com.hekate.journalApp.entity;
//
//import lombok.*;
//        import org.springframework.data.annotation.Id;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import javax.persistence.*;
//        import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "user_data")
//@Data
//public class UserEntry {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NonNull
//    private String password;
//
//    @Column(unique = true)
//    @NonNull
//    private String userName;
//
//    @OneToMany(mappedBy = "userEntry", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<JournalEntry> journalEntries = new ArrayList<>();
//}
//```
//
//        **JournalEntry.java**
//        ```java
//package com.hekate.journalApp.entity;
//
//import lombok.Data;
//import lombok.NonNull;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.Id;
//import javax.persistence.*;
//        import java.time.ZonedDateTime;
//
//@Entity
//@Table(name = "journal_entries")
//@Data
//public class JournalEntry {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NonNull
//    private String title;
//
//    private String content;
//
//    private ZonedDateTime createdDate;
//
//    private ZonedDateTime updatedDate;
//
//    @ManyToOne
//    @JoinColumn(name = "user_entry_id", nullable = false)
//    private UserEntry userEntry;
//}
//```
//
//These changes will create a foreign key relationship between `UserEntry` and `JournalEntry` in PostgreSQL.