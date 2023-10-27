package cat.gato.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CatImage {

    private String id;
    private String url;
    private Image image;
    private int width;
    private int height;
    private List<Breed> breeds;

    @Getter
    @Setter
    public static class Image {
        private String url;
    }

    @Getter
    @Setter
    public static class Breed {
        private String id;
        private String name;
        private String temperament;
        private String origin;
        private String country_code;
    }
}
