package ua.training.chmutov.sweater.repos;

import org.springframework.data.repository.CrudRepository;
import ua.training.chmutov.sweater.domain.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);
}
