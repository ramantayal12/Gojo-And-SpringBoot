package org.gojo.encrytion;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;


@Converter
public class Encryption implements AttributeConverter<String, String> {

  private final EncryptionUtil encryptionUtil;

  @Autowired
  public Encryption(EncryptionUtil encryptionUtil) {
    this.encryptionUtil = encryptionUtil;
  }

  @Override
  public String convertToDatabaseColumn(String s) {
    return encryptionUtil.encrypt(s);
  }

  @Override
  public String convertToEntityAttribute(String s) {
    return encryptionUtil.decrypt(s);
  }
}
