import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@SaveTo(path = "./saveTest.txt")
public class TextContainer {
    private String text;

    public TextContainer(String text) {
        this.text = text;
    }

    public TextContainer() {}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Saver
    public void save( ) {

        Class<?> clazz = this.getClass();
        for( Annotation annotation: clazz.getAnnotations() ) {

            if( annotation instanceof SaveTo path ) {

                String direction = path.path();

//                int lengthOfArrayOfPath = path.path().split("/").length;
//
//                String fileName = path.path().split("/")[lengthOfArrayOfPath - 1];

                for(Method method : clazz.getMethods()) {

                    if(method.getName().equals("save") ) {

                        Annotation[] annotations = method.getAnnotations();
                        for( Annotation ann : annotations ) {

                            if(ann.annotationType().equals(Saver.class)) {

                                try( FileWriter fw = new FileWriter(direction) ) {

                                    fw.write( text );
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }
            }
        }



    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface SaveTo{
    String path();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Saver {}
