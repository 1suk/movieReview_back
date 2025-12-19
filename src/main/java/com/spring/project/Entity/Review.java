package com.spring.project.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="REVIEW", uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID", "MOVIE_NO"}))
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="REVIEW_NO")
    private Long reviewNo;

    @Column(nullable = false)
    private Double rating;

    @Column(length = 2000, nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_NO")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @PreUpdate  // 수정 시 자동으로 시간 업데이트
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }

    public void putUpdate(Double rating, String content) {
        this.rating = rating;
        this.content = content;
    }

    public void patchUpdate(Double rating, String content) {
        if(rating != null)
            this.rating = rating;

        if(content != null)
            this.content = content;
    }
}
