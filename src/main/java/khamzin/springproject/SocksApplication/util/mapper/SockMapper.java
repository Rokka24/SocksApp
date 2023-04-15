package khamzin.springproject.SocksApplication.util.mapper;

import khamzin.springproject.SocksApplication.dto.SockDTO;
import khamzin.springproject.SocksApplication.models.Sock;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SockMapper {

    private final ModelMapper modelMapper;

    public Sock convertToSock(SockDTO sockDTO) {
        return modelMapper.map(sockDTO, Sock.class);
    }
}
