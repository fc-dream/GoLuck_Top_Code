package top.goluck.compiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

import top.goluck.annotation.Record;


@AutoService(Processor.class)
public class RecordProcessor extends AbstractProcessor {

    private static final String ANNOTATION = "@" + Record.class.getSimpleName();
    private static final String CLASS_NAME = "ClasssUtil";
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Record.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return Sample1(roundEnvironment);
//        return Sample2(roundEnvironment);
    }

    private boolean Sample2(RoundEnvironment roundEnvironment) {
        List<Records> datas = new ArrayList<>();
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Record.class)) {
            TypeElement typeElement = (TypeElement) element;
            if (!isValidClass(typeElement)) {
                return true;
            }
            try {
                datas.add(buildAnnotatedClass(typeElement));
            } catch (IOException e) {
                String message = String.format("Couldn't process class %s: %s", typeElement,
                        e.getMessage());
                messager.printMessage(Diagnostic.Kind.ERROR, message, element);
                e.printStackTrace();
            }
        }
        try {
            generate(datas);
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Couldn't generate class");
        }
        return false;
    }

    private boolean Sample1(RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Record.class);
        TypeSpec.Builder builder = TypeSpec.classBuilder("ClasssUtil").addModifiers(Modifier.PUBLIC);
        if(elements!=null && elements.size()>0) {
            for (Element element : elements) {
                if (element.getModifiers().contains(Modifier.PUBLIC)  &&  !(element.getModifiers().contains(Modifier.ABSTRACT))) {//不支持非public的class
                    Record router = element.getAnnotation(Record.class);
                    StringBuilder sb = new StringBuilder();
                    sb.append("return \"author:" + router.author()+"\\n\"+\n        \"class:"+ element.getSimpleName().toString() +"\\n\"+\n          \"packageName:"+ (element==null?null:element.asType())+ "\"");
                    MethodSpec methodSpec = MethodSpec.methodBuilder("to" + element.getSimpleName().toString())
                            .addJavadoc("to" + element.getSimpleName().toString() + "\n")
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .addStatement(sb.toString())
                            .returns(String.class)
                            .build();
                    builder.addMethod(methodSpec);
                }
            }
            try {
                TypeSpec generateClass = builder.build();
                JavaFile javaFile = JavaFile.builder("top.goluck.annotations_2017_4_10", generateClass).build();
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Couldn't generate class");
            }
            return true;
        }
        return false;
    }

    //检查这个class是否是一个正常的class
    private boolean isValidClass(TypeElement element) {
        if (!element.getModifiers().contains(Modifier.PUBLIC)) {//不支持非public的class
            String message = String.format("Classes annotated with %s must be public.",
                    ANNOTATION);
            messager.printMessage(Diagnostic.Kind.ERROR, message, element);
            return false;
        }
        if (element.getModifiers().contains(Modifier.ABSTRACT)) {//不支持抽象的class
            String message = String.format("Classes annotated with %s must not be abstract.",
                    ANNOTATION);
            messager.printMessage(Diagnostic.Kind.ERROR, message, element);
            return false;
        }
        return true;
    }

    //转换成解析后的数据
    private Records buildAnnotatedClass(TypeElement typeElement) throws IOException {
        Records data = new Records();
        Record router = typeElement.getAnnotation(Record.class);
        data.args = router.value();
        data.author = router.author();
        data.annotatedClassName = typeElement.getSimpleName().toString();
        data.typeElement = typeElement;

        ArrayList<String> variableNames = new ArrayList<>();
        for (Element element : typeElement.getEnclosedElements()) {
            if (!(element instanceof VariableElement)) {
                continue;
            }
            VariableElement variableElement = (VariableElement) element;
            variableNames.add(variableElement.getSimpleName().toString());
        }
        data.variableNames = variableNames;
        return data;
    }

    // 生成java源代码
    private void generate(List<Records> datas) throws IOException {
        if (null == datas || datas.size() == 0) {
            return;
        }

        String packageName = "";
        PackageElement pkg = processingEnv.getElementUtils().getPackageOf(datas.get(0).typeElement);
        if (!pkg.isUnnamed()) {
            packageName = pkg.getQualifiedName().toString();
        }

        TypeSpec generateClass = generateClass(datas);

        JavaFile javaFile = JavaFile.builder(packageName, generateClass).build();
        javaFile.writeTo(processingEnv.getFiler());
    }

    private TypeSpec generateClass(List<Records> datas) {
        TypeSpec.Builder builder = TypeSpec.classBuilder(CLASS_NAME).addModifiers(Modifier.PUBLIC);
        for (Records anno : datas) {
            builder.addMethod(makeCreateStringMethod4(anno.typeElement));
        }
        return builder.build();
    }

    // 生成返回 annotatedClass.annotatedClassName类的所有字段打印String数据的方法地源码
    private MethodSpec makeCreateStringMethod1(Records annotatedClass) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("return \"%s{\" + ", annotatedClass.annotatedClassName));
        for (String variableName : annotatedClass.variableNames) {
            builder.append(String.format(" \"%s='\" + String.valueOf(instance.%s) + \"',\" + ",
                    variableName, variableName));
        }
        builder.append("\"}\"");
        return MethodSpec.methodBuilder("createString")
                .addJavadoc("@return string suitable for {@param instance}'s toString()")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ClassName.get(annotatedClass.getType()), "instance")
                .addStatement(builder.toString())
                .returns(String.class)
                .build();
    }

    // 生成返回 一个num1 + num2 的方法地源码
    private MethodSpec makeCreateStringMethod2(Records annotatedClass) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("return num1 + num2"));
        return MethodSpec.methodBuilder("get" + annotatedClass.annotatedClassName + "Nums")
                .addJavadoc(annotatedClass.annotatedClassName + "num1 + num2\n")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ClassName.INT, "num1")
                .addParameter(ClassName.INT, "num2")
                .addStatement(builder.toString())
                .returns(ClassName.INT)
                .build();
    }

    // 生成返回所有注解相关信息的方法地源码
    private MethodSpec makeCreateStringMethod3(Records annotatedClass) {
        StringBuilder builder = new StringBuilder();
        String tostring = annotatedClass.toString();
        builder.append("return \"" + tostring + "\"");
        return MethodSpec.methodBuilder("to" + annotatedClass.annotatedClassName)
                .addJavadoc("to" + annotatedClass.annotatedClassName + "\n")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addStatement(builder.toString())
                .returns(String.class)
                .build();
    }

    private MethodSpec makeCreateStringMethod4(Element element) {
        Record router = element.getAnnotation(Record.class);
        StringBuilder sb = new StringBuilder();
        sb.append("return \""+element.getAnnotation(Record.class).author()+"\"");
        return MethodSpec.methodBuilder("to" + element.getSimpleName().toString())
                .addJavadoc("to" + element.getSimpleName().toString() + "\n")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addStatement(sb.toString())
                .returns(String.class)
                .build();
    }

}
