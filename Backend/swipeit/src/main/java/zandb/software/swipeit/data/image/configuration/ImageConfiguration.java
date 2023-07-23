package zandb.software.swipeit.data.image.configuration;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationProperties(prefix = "image")
@ConfigurationPropertiesScan
public class ImageConfiguration {

  @NotBlank
  private String imageDocStorePath;

  public String getImageDocStorePath() {
    return imageDocStorePath;
  }

  public void setImageDocStorePath(String imageDocStorePath) {
    this.imageDocStorePath = imageDocStorePath;
  }
}
