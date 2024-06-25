package ar.um.edu.microblogging.services;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

@Service
public abstract class DtoMapper {

  protected void copyNonNullProperties(Object src, Object target) {
    BeanWrapperImpl srcWrap = new BeanWrapperImpl(src);
    BeanWrapperImpl targetWrap = new BeanWrapperImpl(target);

    for (java.beans.PropertyDescriptor pd : srcWrap.getPropertyDescriptors()) {
      String propertyName = pd.getName();
      if ("class".equals(propertyName)) {
        continue; // Skip the 'class' property
      }
      Object srcValue = srcWrap.getPropertyValue(propertyName);
      if (srcValue != null) {
        targetWrap.setPropertyValue(propertyName, srcValue);
      }
    }
  }
}
