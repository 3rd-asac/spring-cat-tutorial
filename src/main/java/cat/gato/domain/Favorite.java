package cat.gato.domain;

import lombok.Data;

@Data
public class Favorite {
    private String image_id;
    private String sub_id;

    public Favorite(String imageId, String subId) {
        this.image_id = imageId;
        this.sub_id = subId;
    }
}