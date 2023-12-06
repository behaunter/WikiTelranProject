package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ErrorResponseEditPageDto {
    private String code;
    private String info;
    private String docref;
}
