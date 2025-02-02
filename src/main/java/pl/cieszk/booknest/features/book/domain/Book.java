package pl.cieszk.booknest.features.book.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import pl.cieszk.booknest.features.author.domain.Author;
import pl.cieszk.booknest.features.category.domain.Category;
import pl.cieszk.booknest.features.publisher.domain.Publisher;
import pl.cieszk.booknest.features.review.domain.Review;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(length = 50)
    private String genre;

    @Column
    private Integer publishYear;

    @Column(unique = true, length = 20)
    private String isbn;

    @Column(length = 50)
    private String language;

    @Column
    private Integer pageCount;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @ToString.Exclude
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @ToString.Exclude
    private Set<Author> authors;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ToString.Exclude
    private List<Category> categories;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private Set<BookInstance> bookInstances;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private Set<Review> reviews;
}