package decoder;

import code.CesarCode;

public class DecoderByKey extends Decoder {
    private CesarCode cesarCode;

    public void setCesarCode(CesarCode cesarCode) {
        this.cesarCode = cesarCode;
    }

    public DecoderByKey(CesarCode cesarCode) {
        this.cesarCode = cesarCode;
    }

    @Override
    public String decode(String encryptedText) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = encryptedText.toCharArray();
        int key = cesarCode.getKey();

        for (int i = 0; i < charArray.length; i++) {
            sb.append((char)(charArray[i] - key));
        }

        return sb.toString();
    }
}
