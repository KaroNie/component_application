package com.karo.router_annotation_compiler;

import com.google.auto.service.AutoService;
import com.karo.router_annotation.Route;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * 找到没Route注解的类，获取注解里面的数据及被标记的类，生成工具类
 */
@AutoService(Processor.class)//注册当前的类为注解处理器
public class AnnotationCompiler extends AbstractProcessor {

    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.WARNING, "AnnotationCompiler:初始化");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>(1);
        types.add(Route.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Route.class);

        messager.printMessage(Diagnostic.Kind.WARNING, "AnnotationCompiler:" + (elements.size() == annotations.size()));

        Map<String, String> map = new HashMap<>();
        for (Element element : elements) {
            TypeElement typeElement = (TypeElement) element;
            String activityName = typeElement.getQualifiedName().toString();
            String key = typeElement.getAnnotation(Route.class).path();
            map.put(key, activityName);
        }
        messager.printMessage(Diagnostic.Kind.WARNING, "AnnotationCompiler:" + map.size());
        if (map.size() > 0) {
            Writer writer = null;
            String className = "RouteUtil" + System.currentTimeMillis();
            try {
                JavaFileObject sourceFile =
                        filer.createSourceFile("com.component.util." + className);
                writer = sourceFile.openWriter();

                StringBuilder sb = new StringBuilder();
                sb.append("package com.component.util;\n");
                sb.append("import com.karo.arouter.ARouter;\n");
                sb.append("import com.karo.arouter.IRouter;\n");
                sb.append("public class ");
                sb.append(className);
                sb.append(" implements IRouter {\n");
                sb.append("@Override\n");
                sb.append("public void putActivity() {\n");

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String activityName = entry.getValue();
                    //ARouter.getInstance().putActivity("task/task", TaskMainActivity::class.java)
                    sb.append("ARouter.INSTANCE.putActivity(\"")
                            .append(key).append("\", ")
                            .append(activityName)
                            .append(".class);\n");
                }

                sb.append("}\n}");

                writer.write(sb.toString());

            } catch (Exception e) {
                e.printStackTrace();
                messager.printMessage(Diagnostic.Kind.WARNING, "AnnotationCompiler:" + e.getMessage());
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        messager.printMessage(Diagnostic.Kind.WARNING, "AnnotationCompiler:" + e.getMessage());
                    }
                }
                messager.printMessage(Diagnostic.Kind.WARNING, "AnnotationCompiler:over");
            }
        } else {
            messager.printMessage(Diagnostic.Kind.WARNING, "AnnotationCompiler:empty");
        }

        return false;
    }
}
