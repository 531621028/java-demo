import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Set;

/**
 * @author kang
 * @since 2021/2/3
 */
public class GenericTypeTest<T> {

    private List<String>[] genericArrayType;
    private Set<T> typeVariableSet;
    private Set<String> parameterizedTypeSet;
    private Set<? extends String> wildcardTypeSet;

    public static void main(String[] args) throws Exception {
        Field f;
        // 拿到所有的字段
        Field[] fields = GenericTypeTest.class.getDeclaredFields();
        for (Field field : fields) {
            f = field;
            System.out.println(f.getName() + "：");
            printParameterizedType(f.getGenericType());
            printGenericArrayType(f.getGenericType());
        }
    }

    private static void printGenericArrayType(Type type) {
        if (type instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type;
            System.out.println("\t GenericComponentType:" + genericArrayType);
            printParameterizedType(genericArrayType.getGenericComponentType());
        }
    }

    private static void printParameterizedType(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.println("\t ParameterizedType:" + parameterizedType);
            for (Type actualTypeArguments : parameterizedType.getActualTypeArguments()) {
                printWildcardType(actualTypeArguments);
                printTypeVariable(actualTypeArguments);
            }
            System.out.println("\t getRawType:" + parameterizedType.getRawType());
            System.out.println("\t getOwnerType:" + parameterizedType.getOwnerType());
        }
    }

    private static void printWildcardType(Type type) {
        if (type instanceof WildcardType) {
            System.out.println("\t WildcardType:" + type);
        }
    }

    private static void printTypeVariable(Type type) {
        if (type instanceof TypeVariable) {
            System.out.println("\t TypeVariable:" + type);
        }
    }

}
