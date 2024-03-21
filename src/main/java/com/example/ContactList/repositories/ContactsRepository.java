package com.example.ContactList.repositories;

import com.example.ContactList.entity.Contact;
import com.example.ContactList.repositories.mappers.ContactRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContactsRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Contact> findAll() {
        log.info("Calling repo.findAll");

        String query = "SELECT * from contacts ORDER BY id";

        return jdbcTemplate.query(query, new ContactRowMapper());
    }

    public Optional<Contact> findById(Long id) {
        log.info("Calling repo.findById with id {}", id);

        String query = "SELECT * from contacts where id = ?";

        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        query,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)
                )
        );

        return Optional.ofNullable(contact);
    }

    public void save(Contact contact) {
        log.info("Calling repo.save with Contact: {}", contact);

        String query = "INSERT INTO contacts (first_name, last_name, email, " +
                "phone) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(query, contact.getFirstName(), contact.getLastName(),
                contact.getEmail(), contact.getPhone());
    }

    public void update(Contact contact) {
        log.info("Calling repo.update with Contact: {}", contact);

        String query = "UPDATE contacts SET first_name = ?, last_name = ?, " +
                "email = ?, phone = ? WHERE id = ?";
        jdbcTemplate.update(query, contact.getFirstName(), contact.getLastName(),
                contact.getEmail(), contact.getPhone(), contact.getId());
    }

    public void deleteById(Long id) {
        log.debug("Calling repo.delete with id: {}", id);

        String query = "DELETE FROM contacts WHERE ID = ?";
        jdbcTemplate.update(query, id);
    }

}
