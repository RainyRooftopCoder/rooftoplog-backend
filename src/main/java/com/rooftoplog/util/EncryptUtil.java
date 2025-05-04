package com.rooftoplog.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
@Component
@RequiredArgsConstructor
public class EncryptUtil {

    private static final BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    // AES key는 반드시 16, 24, 32 byte 중 하나
    @Value("${encrypt.aes.key}")
    private String secretKeyProp;

    private SecretKeySpec secretKeySpec;

    @PostConstruct
    public void init() {
        this.secretKeySpec = new SecretKeySpec(secretKeyProp.getBytes(), "AES");
    }

    /**
     * 단방향 암호화
     *
     * @param planText 평문
     */
    public String encryptPassword(String planText) {
        // 사용
        // String encoStr1 = encryptUtil.encryptPassword(password);

        return bcryptEncoder.encode(planText);
    }

    /**
     * 단방향 문자열 비교
     *
     * @param planText 평문
     * @param hashed 해시된 문자열
     * @return 일치 여부(true: 일치, false: 불일치)
     */
    public Boolean decryptPassword(String planText, String hashed) {
        // 사용
        // boolean match = encryptUtil.decryptPassword(password, encoStr1);
        
        return bcryptEncoder.matches(planText, hashed);
    }

    /**
     * 양방향 암호화(AES)
     *
     * @param planText 평문
     * @return 암호화된 문자열
     */
    public String enctyptAES(String planText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(planText.getBytes());

            // 사용
            // String encoStr1 = encryptUtil.enctyptAES(password);

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            log.error("에러 :: {}", ex);
            log.error("에러_getCause :: {}", ex.getCause());
            throw new RuntimeException("AES 암호화 실패", ex);
        }
    }

    /**
     * 양방향 복호화(AES)
     *
     * @param encryptedText 암호화 분자열
     * @return decryptedText 복호화 문자열
     */
    public String dectyptAES(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

            // 사용
            // String decoStr1 = encryptUtil.dectyptAES(encoStr1);
            
            return new String(decrypted);
        } catch (Exception ex) {
            log.error("에러 :: {}", ex);
            log.error("에러_getCause :: {}", ex.getCause());
            throw new RuntimeException("AES 복호화 실패.");
        }
    }
}
