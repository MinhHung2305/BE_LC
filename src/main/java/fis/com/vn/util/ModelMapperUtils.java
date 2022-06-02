package fis.com.vn.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperUtils {
    private static ModelMapper modelMapper;
    public ModelMapperUtils() {
    }

    public static <T> T mapper(Object sourceData, Class<T> destinationClassType) {
        return getInstance().map(sourceData, destinationClassType);
    }

    public static <T> T mapper(Object sourceData, T targetData) {
        getInstance().map(sourceData, targetData);
        return targetData;
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return (List)source.stream().map((e) -> {
            return getInstance().map(e, targetClass);
        }).collect(Collectors.toList());
    }

    private static ModelMapper getInstance() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
            modelMapper.getConfiguration().setDestinationNameTokenizer(NameTokenizers.UNDERSCORE);
        }

        return modelMapper;
    }
}
