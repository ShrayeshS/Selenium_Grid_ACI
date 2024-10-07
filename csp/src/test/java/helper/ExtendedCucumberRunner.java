/*package helper;

import com.github.mkolisnyk.cucumber.runner.AfterSuite;
import com.github.mkolisnyk.cucumber.runner.BeforeSuite;
import cucumber.api.junit.Cucumber;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;

public class ExtendedCucumberRunner extends ExtendedCucumber {
	
	private static final String CLASS_NAME = "com.github.mkolisnyk.cucumber.runner.ExtendedCucumber";
	private Class clazz;
    private Cucumber cucumber;
    
    public ExtendedCucumberRunner(Class clazzValue) throws Exception {
    	super(clazzValue);
        clazz = clazzValue;
        cucumber = new Cucumber(clazzValue);
    }

    @Override
    public Description getDescription() {
        return cucumber.getDescription();
    }

    private void runPredefinedMethods(Class annotation) throws Exception {
        if (!annotation.isAnnotation()) {
            return;
        }
        Method[] methodList = this.clazz.getMethods();
        for (Method method : methodList) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation item : annotations) {
                if (item.annotationType().equals(annotation)) {
                    method.invoke(null);
                    break;
                }
            }
        }
    }
    
    @Override
    public void run(RunNotifier notifier) {
        try {
            runPredefinedMethods(BeforeSuite.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.run(notifier);
        try {
            runPredefinedMethods(AfterSuite.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isNotCalledByBase(StackTraceElement[] stackTraceElements) {
    	boolean isCalledByBase = false; 
    	for (StackTraceElement stackTraceElement : stackTraceElements) {
			if(stackTraceElement.getClassName().equals(CLASS_NAME))
				isCalledByBase = true;
		}
    	
    	return !isCalledByBase;
    }

}
*/