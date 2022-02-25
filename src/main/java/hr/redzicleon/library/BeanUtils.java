package hr.redzicleon.library;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

public class BeanUtils {
    private BeanUtils() {}
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public static void copyProperties(Object source, Object target, java.lang.String... ignoreProperties) throws BeansException {
        org.springframework.beans.BeanUtils.copyProperties(source, target, concatWithArrayCopy(ignoreProperties, getNullPropertyNames(source)));
    }

    static <T> T[] concatWithArrayCopy(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }


}
