package temkarus0070.firstTask.di;

import temkarus0070.firstTask.exceptions.DiException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Configuration(packages = {"temkarus0070.firstTask.sort","temkarus0070.firstTask.validation.validators"})
public class Injector {
    public static  <T> T inject(T object) throws IOException, URISyntaxException, InvocationTargetException, InstantiationException, IllegalAccessException, DiException {
        final Configuration declaredAnnotation = Injector.class.getDeclaredAnnotation(Configuration.class);
        final String[] packages = declaredAnnotation.packages();

        Class tClass=object.getClass();
        final Field[] fields = tClass.getFields();
        List<Class> classList=new ArrayList<>();
        for (Field field:fields){
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                int classesCount=0;
                Class type=field.getType();
                if (classList.size()==0){
                    classList=getClasses(packages);
                }
                for (Class aClass : classList) {
                    if(aClass.isInstance(type)){
                        field.setAccessible(true);
                        field.set(object,aClass.getEnclosingConstructor().newInstance());
                        classesCount++;
                    }
                }
                if (classesCount>1){
                    throw new DiException(String.format("More than 1 class of type %s was found",type.getName()));
                }
            }
        }
        return null;
    }
    public static List<Class> getClasses(String[] packagesName) throws IOException, URISyntaxException {
        List<Class> classes=new ArrayList<>();
        for (String packageName : packagesName) {
            packageName=packageName.replace(".","/");
            ClassLoader classLoader=ClassLoader.getSystemClassLoader();
            URL resource = classLoader.getResource(packageName);
            packageName=packageName.replace("/",".");
            getClasses(resource, classes, packageName);
        }

        return classes;
    }

    public static List<Class> getClasses(URL resource,List<Class> classes,String packageName) throws IOException, URISyntaxException {


        String finalPackageName = packageName;
        java.nio.file.Files.list(Path.of(resource.toURI())).forEach(
                file -> {
                    if (Files.isDirectory(file)) {
                        try {
                            getClasses(file.toUri().toURL(),classes,packageName+"."+file.toFile().getName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        String className = file.toFile().getName();
                        className=className.replaceFirst("[.]+.+","");
                        className=finalPackageName +"."+className;
                        System.out.println(className);
                        try {
                            final Class<?> aClass = Class.forName(className);
                            if (!aClass.isInterface()) {
                                classes.add(aClass);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        return classes;
    }

    public static void main(String[] args) {
        try {
            getClasses("temkarus0070.firstTask");
        }
        catch (Exception exception){

        }
    }
}
