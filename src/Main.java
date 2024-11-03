import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        TASK1
        SumTest testClass = new SumTest();
        Class<?> some = testClass.getClass();
        for(int i = 0; i < some.getMethods().length; i++){
            if(some.getMethods()[i].getName().equals("test")){
                Annotation[] annotations = some.getMethods()[i].getAnnotations();
                    for( Annotation annotation : annotations ) {
                        if( annotation instanceof Test test ) {
                            testClass.test( test.a(), test.b() );
                        }

                    }

            }
        }

//        TASK2
        TextContainer container = new TextContainer("Swing text components display text and optionally allow the user to edit the text. Programs need text components for tasks ranging from the straightforward (enter a word and press Enter) to the complex (display and edit styled text with embedded images in an Asian language).");

        container.save();


//        TASK3
        Car savedZaporozhec = new Car("ZAZ", "ZAZ-968", "1990", "red", "2000");
        StringBuilder textToFile = new StringBuilder();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cars.txt"))) {
            Class<?> carClass = savedZaporozhec.getClass();
            for(Field field : carClass.getDeclaredFields()) {

                for( Annotation annotation : field.getAnnotations() ) {
                    if( annotation.annotationType().equals(Save.class) ) {
                        field.setAccessible(true);
                        textToFile.append((String) field.get(savedZaporozhec)).append(";");
                    }
                }
            }

            Car zap = new Car(textToFile.toString().split(";")[0], textToFile.toString().split(";")[1], textToFile.toString().split(";")[2]);

            oos.writeObject(zap);
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }



        FileInputStream fileInputStream = new FileInputStream("./testCar.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Car buga = (Car) objectInputStream.readObject();

        System.out.println(buga);



    }
}


class SumTest {

  SumTest() {

  }

  @Test(a=2,b=5)
  public void test(int a, int b) {
      System.out.println(a + b);
  }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Test {
    int a();
    int b();
}

