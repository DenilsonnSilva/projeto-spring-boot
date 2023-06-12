import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre createGenre(@RequestBody Genre genre) {
        return genreRepository.save(genre);
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable Long id, @RequestBody Genre updatedGenre) {
        Genre existingGenre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre not found"));
        existingGenre.setName(updatedGenre.getName());
        return genreRepository.save(existingGenre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable Long id) {
        genreRepository.deleteById(id);
    }
}