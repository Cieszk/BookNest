package pl.cieszk.booknest.features.book;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cieszk.booknest.features.book.domain.Book;
import pl.cieszk.booknest.features.book.domain.BookInstance;
import pl.cieszk.booknest.features.book.domain.dto.BookInstanceRequestDto;
import pl.cieszk.booknest.features.book.domain.dto.BookInstanceSummaryDto;
import pl.cieszk.booknest.features.book.domain.enums.BookStatus;
import pl.cieszk.booknest.features.book.mapper.BookInstanceMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookInstanceService {
    private final BookInstanceRepository bookInstanceRepository;
    private final BookRepository bookRepository;
    private final BookInstanceMapper bookinstanceMapper;

    @Transactional(readOnly = true)
    public BookInstance findAvailableInstance(Long bookId) {
        List<BookInstance> instances = bookInstanceRepository.findByBook_BookId(bookId);

        return instances.stream()
                .filter(BookInstance::isAvailable)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No available copies of this book"));
    }

    @Transactional
    public BookInstanceSummaryDto addBookInstance(BookInstanceRequestDto bookInstanceRequestDto) {
        Book book = bookRepository.findById(bookInstanceRequestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        BookInstance bookInstance = BookInstance.builder()
                .book(book)
                .bookStatus(BookStatus.ACTIVE)
                .build();
        bookInstance = bookInstanceRepository.save(bookInstance);
        return BookInstanceSummaryDto.builder().bookInstanceId(bookInstance.getBookInstanceId()).build();
    }

    @Transactional
    public void updateBookInstanceStatus(Long bookInstanceId, BookInstanceRequestDto instanceRequest) {
        BookInstance bookInstance = bookInstanceRepository.findById(bookInstanceId)
                .orElseThrow(() -> new EntityNotFoundException("BookInstance not found"));

        validateStatusChange(bookInstance.getBookStatus(), instanceRequest.getBookStatus());

        if (bookInstance.isLoaned()) {
            throw new IllegalStateException("Cannot change status: Book is currently loaned");
        }
        if (bookInstance.isReserved()) {
            throw new IllegalStateException("Cannot change status: Book is currently reserved");
        }

        bookInstance.setBookStatus(instanceRequest.getBookStatus());
        bookInstanceRepository.save(bookInstance);
    }

    private void validateStatusChange(BookStatus currentStatus, BookStatus newStatus) {
        if (currentStatus == BookStatus.DESTROYED && newStatus == BookStatus.ACTIVE) {
            throw new IllegalStateException("Cannot change status: Book is destroyed");
        }
    }

    public List<BookInstanceSummaryDto> getAllInstancesForTheBook(Long id) {
        List<BookInstance> instances = bookInstanceRepository.findByBook_BookId(id);
        return bookinstanceMapper.toResponseDtoList(instances);
    }
}
