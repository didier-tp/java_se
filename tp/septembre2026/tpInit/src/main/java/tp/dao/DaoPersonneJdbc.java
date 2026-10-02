package tp.dao;

import tp.PersonEntity;

import java.util.List;

public class DaoPersonneJdbc implements DaoPersonne{
    @Override
    public PersonEntity findById(long id) {
        return null;
    }

    @Override
    public List<PersonEntity> findAll() {
        return List.of();
    }

    @Override
    public PersonEntity insert(PersonEntity p) {
        return null;
    }

    @Override
    public PersonEntity update(PersonEntity p) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
