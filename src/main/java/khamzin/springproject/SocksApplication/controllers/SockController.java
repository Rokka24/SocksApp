package khamzin.springproject.SocksApplication.controllers;

import jakarta.validation.Valid;
import khamzin.springproject.SocksApplication.dto.SockDTO;
import khamzin.springproject.SocksApplication.models.Sock;
import khamzin.springproject.SocksApplication.services.SocksService;
import khamzin.springproject.SocksApplication.util.mapper.SockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import static khamzin.springproject.SocksApplication.util.exceptions.ErrorsUtil.returnErrorsToClient;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/socks")
public class SockController {

    private final SocksService socksService;
    private final SockMapper sockMapper;

    @PostMapping("/income")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid SockDTO sockDTO,
                                           BindingResult bindingResult) {
        int quantity = sockDTO.getQuantity();

        Sock sockToAdd = sockMapper.convertToSock(sockDTO);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        socksService.saveAll(sockToAdd.getColor(), sockToAdd.getCottonPart(), quantity);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/outcome")
    public ResponseEntity<HttpStatus> remove(@RequestBody @Valid SockDTO sockDTO,
                                             BindingResult bindingResult) {
        Sock sockToRemove = sockMapper.convertToSock(sockDTO);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        socksService.removeAll(sockToRemove, sockDTO.getQuantity());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public int findAllSelectedSocks(@RequestParam("color") String color,
                                    @RequestParam("operation") String operation,
                                    @RequestParam("cottonPart") int cottonPart) {

        return socksService.numberOfSocks(color, operation, cottonPart);
    }
}
