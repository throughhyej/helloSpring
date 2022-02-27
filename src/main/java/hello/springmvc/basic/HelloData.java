package hello.springmvc.basic;

import lombok.Data;

@Data /* @Getter, @Setter, @ToString , @EqualsAndHashCode , @RequiredArgsConstructor 적용됨 */
public class HelloData {

    private String username;
    private int age;

}
