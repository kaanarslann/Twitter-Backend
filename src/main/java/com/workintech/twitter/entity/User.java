package com.workintech.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user", schema = "twitter")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(max = 50)
    private String userName;

    @Column(name = "full_name")
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(max = 100)
    private String fullName;

    @Column(name = "email")
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(max = 100)
    @Email
    private String email;

    @Column(name = "user_name")
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(max = 20)
    private String password;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDate createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Tweet> tweets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments;
}
