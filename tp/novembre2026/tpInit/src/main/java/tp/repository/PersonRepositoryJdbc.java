package tp.repository;

import tp.entity.PersonEntity;

import java.util.List;
import java.util.Optional;

public class PersonRepositoryJdbc implements PersonRepository {
    @Override
    public Optional<PersonEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PersonEntity> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public PersonEntity insert(PersonEntity p) {
        return null;
    }

    @Override
    public void update(PersonEntity p) {

    }
}
