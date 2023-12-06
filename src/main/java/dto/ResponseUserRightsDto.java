package dto;

import lombok.*;

import java.util.ArrayList;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseUserRightsDto {
        public String user;
        public int userid;
        public ArrayList<Object> added;
        public ArrayList<Object> removed;

}
