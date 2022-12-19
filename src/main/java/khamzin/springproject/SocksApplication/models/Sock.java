package khamzin.springproject.SocksApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Please, specify the color!")
    @Size(min = 2, max = 100, message = "Color should be in range of 2 to 100 symbols")
    private String color;
    @Column(name = "cotton_part")
    @NotNull(message = "Please, specify the cotton part!")
    private Integer cottonPart;

    public Sock(String color, Integer cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }
}