package xCourse.View;

import xCourse.Helper.Config;
import xCourse.Helper.Helper;
import xCourse.Helper.Item;
import xCourse.Model.Course;
import xCourse.Model.Lesson;
import xCourse.Model.Quizz;
import xCourse.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EduLessonAddDeleteGui extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_lesson_add;
    private JPanel pnl_lesson_delete;
    private JTable tbl_lesson_list;
    private JTextField fld_addLesson_title;
    private JComboBox cmb_addLesson_course;
    private JTextField fld_lessonAdd_description;
    private JTextField fld_lessonAdd_youtube;
    private JButton btn_lesson_add;
    private JScrollPane scrl_lesson_list;
    private JButton btn_dlt_lesson;
    private JTextField fld_quizz_quest;
    private JComboBox cmb_quizz_lesson;
    private JButton btn_add_quizz;
    private JPanel pnl_quizz;
    private JScrollPane srcl_quizz;
    private JTable tbl_quizz;
    private JButton btn_dlt_quizz;

    private DefaultTableModel mdl_lesson_list;
    private DefaultTableModel mdl_quizz_list;
    private Object[] row_quizz_list;
    private Object[] row_lesson_list;
    private User edu;

    public EduLessonAddDeleteGui(User edu) {

        this.edu = edu;
        add(wrapper);
        setSize(1000,600);
        setLocation(Helper.screenCenterLoc("x", getSize()), Helper.screenCenterLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        //@@@@@@@@@@@@@@@@@@@@@QQ@@ TABLE INITIALIZE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\
        //Lesson List!
        mdl_lesson_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] col_lesson_list = {"Course", "Title", "Description", "Youtube"};
        mdl_lesson_list.setColumnIdentifiers(col_lesson_list);
        row_lesson_list = new Object[col_lesson_list.length];

        load_lesson_model();
        load_cmb_addLesson_course();
        load_cmb_quizz_lesson();

        tbl_lesson_list.getTableHeader().setReorderingAllowed(false);

        tbl_lesson_list.setModel(mdl_lesson_list);
        tbl_lesson_list.getColumnModel().getColumn(0).setMaxWidth(78);
        tbl_lesson_list.getTableHeader().setReorderingAllowed(false);
        //Quizz List!

        mdl_quizz_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] col_quizz_list = {"Lesson Title", "Quizz Question"};
        mdl_quizz_list.setColumnIdentifiers(col_quizz_list);
        row_quizz_list = new Object[col_quizz_list.length];
        load_quizz_model();
        tbl_quizz.getTableHeader().setReorderingAllowed(false);

        tbl_quizz.setModel(mdl_quizz_list);

        tbl_quizz.getTableHeader().setReorderingAllowed(false);



        btn_lesson_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedItemIndex = cmb_addLesson_course.getSelectedIndex();
                Object selectedCourse = cmb_addLesson_course.getItemAt(selectedItemIndex);
                String courseName = selectedCourse.toString();
                int courseId = -1;
                if (!courseName.equals("")){
                    courseId = Course.getFetch(courseName).getId();
                }

                String title = fld_addLesson_title.getText().toString();
                String description = fld_lessonAdd_description.getText().toString();
                String youtube = fld_lessonAdd_youtube.getText().toString();

                if (!title.equals("") && !description.equals("") && !youtube.equals("") && !courseName.equals("")){
                    if (Lesson.add(title, description, youtube, courseId, edu.getId())){
                        Helper.showMsg("done");
                        load_lesson_model();
                        load_cmb_addLesson_course();
                        fld_addLesson_title.setText("");
                        fld_lessonAdd_description.setText("");
                        fld_lessonAdd_youtube.setText("");
                        load_cmb_quizz_lesson();
                    }
                    else
                        Helper.showMsg("error");


                }

                else {
                    Helper.showMsg("error");
                }
            }
        });

        btn_dlt_lesson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = tbl_lesson_list.getValueAt(tbl_lesson_list.getSelectedRow(), 1).toString();
                if (Helper.confirm("If you delete this lesson, also the quizz questions will be deleted under that lesson!")){
                    int lesson_id = Lesson.getFetchByTitle(title).getId();
                    if (Lesson.delete(title)){
                        Helper.showMsg("done");

                        Quizz.delete(lesson_id);

                        load_lesson_model();

                        load_quizz_model();
                    }
                    else Helper.showMsg("error");
                }

            }
        });


        btn_add_quizz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String quizz_question = fld_quizz_quest.getText().toString();
                int lesson_selected_item = cmb_quizz_lesson.getSelectedIndex();
                Object lesson_title = cmb_quizz_lesson.getItemAt(lesson_selected_item);
                Lesson lesson = Lesson.getFetchByTitle(lesson_title.toString());
                if (Quizz.add(quizz_question, lesson.getId(), edu.getId())){
                    Helper.showMsg("done");
                }
                else {
                    Helper.showMsg("error");
                }
                load_quizz_model();
            }

        });


        btn_dlt_quizz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tbl_quizz.getSelectionModel().getSelectedItemsCount() != 0){
                    String selected_quizz_quest = tbl_quizz.getValueAt(tbl_quizz.getSelectedRow(), 1).toString();
                    Quizz.delete(selected_quizz_quest);
                    load_quizz_model();
                    Helper.showMsg("done");
                }else {
                    Helper.showMsg("error");
                }

            }
        });
    }

    private void load_quizz_model() {
        int rowCount = tbl_quizz.getRowCount();
        for (int i = rowCount-1; i>=0; i--){
            tbl_quizz.removeRowSelectionInterval(i,i);
        }
        int i;
        DefaultTableModel clearModel = (DefaultTableModel) tbl_quizz.getModel();
        clearModel.setRowCount(0);
        for (Quizz quizz: Quizz.getList()){

            i =0;
            if (quizz.getEdu_id() == this.edu.getId()){

                row_quizz_list[i++] = Lesson.getFetch(quizz.getLesson_id()).getTitle().toString();

                row_quizz_list[i++] = quizz.getQuizz_quest();

                mdl_quizz_list.addRow(row_quizz_list);
            }

        }

    }

    private void load_cmb_quizz_lesson() {

        cmb_quizz_lesson.removeAllItems();
        cmb_quizz_lesson.addItem(new Item(0, ""));
        for (Lesson lesson: Lesson.getList()){
            if (lesson.getEducator().getId() == this.edu.getId()){
                cmb_quizz_lesson.addItem(new Item(lesson.getId(), lesson.getTitle()));
            }
        }


    }


    private void load_lesson_model() {

        int rowCount = tbl_lesson_list.getRowCount();
        for (int i = rowCount-1; i>=0; i--){
            tbl_lesson_list.removeRowSelectionInterval(i,i);
        }
        int i;
        DefaultTableModel clearModel = (DefaultTableModel) tbl_lesson_list.getModel();
        clearModel.setRowCount(0);

        for (Lesson lesson: Lesson.getList()){
            if (lesson.getEducator().getId() == this.edu.getId()){
                i =0;
                row_lesson_list[i++] = lesson.getCourse().getName().toString();
                row_lesson_list[i++] = lesson.getTitle().toString();
                row_lesson_list[i++] = lesson.getDescription().toString();
                row_lesson_list[i++] = lesson.getYoutube().toString();
                mdl_lesson_list.addRow(row_lesson_list);

            }

        }

    }

    private void load_cmb_addLesson_course() {

        cmb_addLesson_course.removeAllItems();
        cmb_addLesson_course.addItem(new Item(0,""));
        for (Course course: Course.getList(this.edu.getId())){

            cmb_addLesson_course.addItem(new Item(course.getId(), course.getName()));

        }

    }


}
