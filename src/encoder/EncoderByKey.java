package encoder;
import code.CesarCode;

public class EncoderByKey extends Encoder {
    private CesarCode cesarCode;
    public EncoderByKey(CesarCode cesarCode) {
        this.cesarCode = cesarCode;
    }

    @Override
    public String encode(String text) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = text.toCharArray();
        int key = cesarCode.getKey();

        for (int i = 0; i < charArray.length; i++) {
            sb.append((char)(charArray[i] + key));
        }

        return sb.toString();
    }
}
