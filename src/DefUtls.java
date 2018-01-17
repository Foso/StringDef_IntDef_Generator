import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.util.PsiTreeUtil;

public class DefUtls {

    public static String getDefString(PsiClass[] classes, PsiFile file, String filterName) {
        String className = classes[0].getNameIdentifier().getText();

        PsiElement[] vars = PsiTreeUtil.collectElements(file, e -> {
            if (e instanceof PsiVariable) {
                if (((PsiVariable) e).getType().getPresentableText().equals(filterName)) {
                    if (((PsiVariable) e).getModifierList().hasModifierProperty("final")) {
                        return true;
                    }
                }


            }
            return false;
        });


        switch (filterName) {
            case "String":
                return getStringDef(className, vars, "String");
            case "int":
                return getStringDef(className, vars, "Int");
            default:
                return "";
        }
    }

    public static String getStringDef(String className, PsiElement[] vars, String defName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n{@Retention(RetentionPolicy.SOURCE)\n@" + defName + "Def({");

        for (int i = 0; i < vars.length; i++) {
            stringBuilder.append(((PsiVariable) vars[i]).getNameIdentifier().getText());
            if (i != vars.length - 1) {
                stringBuilder.append(",");
            }

        }
        stringBuilder.append("}) public @interface " + className + defName + "Def {}");
        return stringBuilder.toString();

    }


}
