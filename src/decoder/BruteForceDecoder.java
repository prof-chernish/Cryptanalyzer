package decoder;

import code.CesarCode;

public class BruteForceDecoder extends Decoder {
    private final static int NUMBER_OF_ATTEMPTS = 1000;

    @Override
    public String decode(String encryptedText) {
        int key = 0;
        CesarCode cesarCode = new CesarCode(key);
        DecoderByKey decoderByKey = new DecoderByKey(cesarCode);
        while (key < NUMBER_OF_ATTEMPTS) {
            String decryptedText = decoderByKey.decode(encryptedText);
            if (!isDecodingCorrect(decryptedText)) {
                key++;
                cesarCode = new CesarCode(key);
                decoderByKey.setCesarCode(cesarCode);
            } else {
                return decryptedText;
            }
        }
        return "Decryption Failed";

    }


}
