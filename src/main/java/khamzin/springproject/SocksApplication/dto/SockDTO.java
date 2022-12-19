package khamzin.springproject.SocksApplication.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SockDTO {

    @NotEmpty(message = "Please, specify the color!")
    @Size(min = 2, max = 100, message = "Color should be in range of 2 to 100 symbols")
    private String color;
    @Column(name = "cotton_part")
    @NotNull(message = "Please, specify the cotton part!")
    @Min(value = 1, message = "Cotton part should be more than 0")
    @Max(value = 100, message = "Cotton part should be less than 100")
    private Integer cottonPart;
    @NotNull(message = "Please set value of \"quantity\"!")
    @Min(value = 1, message = "Quantity should be more than 0")
    private Integer quantity;
}
