package khamzin.springproject.SocksApplication.repositories;

import khamzin.springproject.SocksApplication.models.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SockRepository extends JpaRepository<Sock, Long> {
    List<Sock> findAllByColorAndCottonPart(String color, int cottonPart);

    List<Sock> findAllByColorAndCottonPartIsGreaterThan(String color, int cottonPart);

    List<Sock> findAllByColorAndCottonPartIsLessThan(String color, int cottonPart);

    List<Sock> findAllByColorAndCottonPartEquals(String color, int cottonPart);
}
