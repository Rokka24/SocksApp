package khamzin.springproject.SocksApplication.services;

import khamzin.springproject.SocksApplication.models.Sock;
import khamzin.springproject.SocksApplication.repositories.SockRepository;
import khamzin.springproject.SocksApplication.util.exceptions.SocksException;
import khamzin.springproject.SocksApplication.util.exceptions.SocksNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SocksService {
    private final SockRepository sockRepository;

    public List<Sock> findAllByColorAndCottonPartIsGreaterThan(String color, int cottonPart) {
        return sockRepository.findAllByColorAndCottonPartIsGreaterThan(color, cottonPart);
    }

    public List<Sock> findAllByColorAndCottonPartIsLessThan(String color, int cottonPart) {
        return sockRepository.findAllByColorAndCottonPartIsLessThan(color, cottonPart);
    }

    public List<Sock> findAllByColorAndCottonPartEquals(String color, int cottonPart) {
        return sockRepository.findAllByColorAndCottonPartEquals(color, cottonPart);
    }

    @Transactional
    public void saveAll(String color, int cottonPart, int quantity) {
        List<Sock> socksToAdd = new ArrayList<>();

        IntStream.range(0, quantity)
                .mapToObj(i -> new Sock(color, cottonPart))
                .forEach(socksToAdd::add);

        sockRepository.saveAll(socksToAdd);
    }

    @Transactional
    public void removeAll(Sock sock, int quantity) {
        List<Sock> socksToRemove = sockRepository.findAllByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
        if (socksToRemove.isEmpty())
            throw new SocksNotFoundException("No such socks at the storage");

        socksToRemove.removeAll(socksToRemove.subList(quantity, socksToRemove.size()));

        sockRepository.deleteAll(socksToRemove);
    }

    public int numberOfSocks(String color, String operation, int cottonPart) {
        switch (operation) {
            case "moreThan" -> {
                return findAllByColorAndCottonPartIsGreaterThan(color, cottonPart).size();
            }
            case "lessThan" -> {
                return findAllByColorAndCottonPartIsLessThan(color, cottonPart).size();
            }
            case "equals" -> {
                return findAllByColorAndCottonPartEquals(color, cottonPart).size();
            }
            default -> throw new SocksException("Incorrect operation!");
        }
    }
}