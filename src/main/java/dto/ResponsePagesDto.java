package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponsePagesDto {
    private int pageid;
    private int ns;
    private String title;

}
