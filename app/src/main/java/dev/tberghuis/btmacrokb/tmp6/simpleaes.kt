package dev.tberghuis.btmacrokb.tmp6

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

object SimpleAES {
    private const val ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES/GCM/NoPadding"
    private const val IV_LENGTH = 12
    private const val GCM_TAG_LENGTH = 128
    private const val KEY_LENGTH = 32  // 256-bit key

    // Convert password into a 256-bit AES key (without salt)
    private fun deriveKey(password: String): SecretKeySpec {
        val digest = MessageDigest.getInstance("SHA-256")
        val keyBytes = digest.digest(password.toByteArray(StandardCharsets.UTF_8))
        return SecretKeySpec(keyBytes.copyOf(KEY_LENGTH), ALGORITHM)
    }

    // Generate random IV
    private fun generateIV(): ByteArray {
        val iv = ByteArray(IV_LENGTH)
        SecureRandom().nextBytes(iv)
        return iv
    }

    // Encrypt data
    fun encrypt(data: String, password: String): String {
        val iv = generateIV()
        val key = deriveKey(password)

        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, key, GCMParameterSpec(GCM_TAG_LENGTH, iv))

        val encryptedBytes = cipher.doFinal(data.toByteArray(StandardCharsets.UTF_8))

        // Combine IV and encrypted data, then encode in Base64
        val combined = iv + encryptedBytes
        return Base64.getEncoder().encodeToString(combined)
    }

    // Decrypt data
    fun decrypt(encryptedData: String, password: String): String {
        val combined = Base64.getDecoder().decode(encryptedData)

        // Extract IV and encrypted content
        val iv = combined.copyOfRange(0, IV_LENGTH)
        val encryptedBytes = combined.copyOfRange(IV_LENGTH, combined.size)

        val key = deriveKey(password)

        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(GCM_TAG_LENGTH, iv))

        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes, StandardCharsets.UTF_8)
    }
}
