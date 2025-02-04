package pl.cieszk.booknest.features.author;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cieszk.booknest.features.author.domain.Author;
import pl.cieszk.booknest.features.author.domain.dto.AuthorRequestDto;
import pl.cieszk.booknest.features.author.domain.dto.AuthorResponseDto;
import pl.cieszk.booknest.features.author.domain.dto.AuthorSummaryDto;
import pl.cieszk.booknest.features.author.mapper.AuthorMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponseDto getAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author with id: " + id + " not found"));
        return authorMapper.toResponseDto(author);
    }

    public List<AuthorSummaryDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.toSummaryDtoList(authors);
    }

    public AuthorResponseDto addAuthor(AuthorRequestDto requestDto) {
        Author author = authorMapper.toEntity(requestDto);
        author = authorRepository.save(author);
        return authorMapper.toResponseDto(author);
    }

    public AuthorResponseDto updateAuthor(AuthorRequestDto requestDto, Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author with id: " + id + " not found"));
        authorMapper.updateEntity(author, requestDto);
        author = authorRepository.save(author);
        return authorMapper.toResponseDto(author);
    }

    public void deleteAuthor(Long id) {
        if(!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Author with id: " + id + " not found");
        }
        authorRepository.deleteById(id);
    }
}
