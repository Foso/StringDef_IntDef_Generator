import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;


public class IntDefAction extends AnAction {


    @Override
    public void actionPerformed(final AnActionEvent anActionEvent) {

        PsiFile file = anActionEvent.getRequiredData(CommonDataKeys.PSI_FILE);
        Document document = anActionEvent.getRequiredData(CommonDataKeys.EDITOR).getDocument();
        Project requiredData1 = anActionEvent.getRequiredData(CommonDataKeys.PROJECT);
        int pos = document.getText().indexOf("{");

        PsiClass[] clazzes = ((PsiJavaFile) file).getClasses();
        WriteCommandAction.runWriteCommandAction(requiredData1, () -> document.replaceString(pos, pos + 1, DefUtls.getDefString(clazzes, file, "int")));
    }


}