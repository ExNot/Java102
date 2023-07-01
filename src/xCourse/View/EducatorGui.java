package xCourse.View;

import xCourse.Helper.Config;
import xCourse.Helper.Helper;
import xCourse.Helper.Item;
import xCourse.Model.Course;
import xCourse.Model.Lesson;
import xCourse.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EducatorGui extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tab_edu;
    private JLabel lbl_edu_wellcome;
    private JPanel pnl_course_lsit;
    private JScrollPane scrl_course_lsit;
    private JTable tbl_course_list;
    private JTable tbl_lesson_list;
    private JScrollPane scrl_lesson_list;
    private JPanel pnl_lesson_add;
    private JTextField fld_searchlesson_title;
    private JTextField fld_searchlesson_des;
    private JComboBox cmb_searchlesson_course;
    private JButton search_lesson;
    private JButton addButton;
    private DefaultTableModel mdl_course_list;
    private DefaultTableModel mdl_lesson_list;
    private Object[] row_course_list;
    private Object[] row_lesson_list;
    private User edu;

    public EducatorGui(User educator){
        this.edu = educator;
        add(wrapper);
        setSize(1000,600);
        setLocation(Helper.screenCenterLoc("x", getSize()), Helper.screenCenterLoc("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ START OF MY COURSES @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        mdl_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lbl_edu_wellcome.setText("Welcome" + " " +educator.getName());

        Object[] col_course_list =  {"ID", "Course Name", "Programming Language", "Path", "Educator"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];


        load_course_model();
        load_cmb_searchlesson_course();



        tbl_course_list.getTableHeader().setReorderingAllowed(false);

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(78);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ END OF MY COURSES @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@



        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ START OF MY LESSONS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


        mdl_lesson_list = (DefaultTableModel) new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] col_lesson_list = {"ID", "Title","Educator", "Youtube Link", "Which Course", "Description"};
        mdl_lesson_list.setColumnIdentifiers(col_lesson_list);
        row_lesson_list = new Object[col_lesson_list.length];

        loadLessonModel();
        load_cmb_searchlesson_course();

        tbl_lesson_list.getTableHeader().setReorderingAllowed(false);
        tbl_lesson_list.setModel(mdl_lesson_list);
        tbl_lesson_list.getColumnModel().getColumn(0).setMaxWidth(78);
        tbl_lesson_list.getTableHeader().setReorderingAllowed(false);


        //Search lessons
        search_lesson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search_lesson_des = fld_searchlesson_des.getText().toString();
                String search_lesson_title = fld_searchlesson_title.getText().toString();

                String search_lesson_course_name = null;
                int course_id = -1;

                int selectedItem = cmb_searchlesson_course.getSelectedIndex();

                Object selectedCourse = cmb_searchlesson_course.getSelectedItem();



                if (selectedItem != 0){
                    cmb_searchlesson_course.setSelectedItem(selectedCourse);
                    Item selectedCourseItem = (Item) selectedCourse;

                    search_lesson_course_name = selectedCourseItem.getVal();
                    System.out.println(search_lesson_course_name);
                    Course course = Course.getFetch(search_lesson_course_name);
                    course_id = course.getId();
                }


                boolean description = Helper.isFieldEmpty(fld_searchlesson_des);
                boolean title = Helper.isFieldEmpty(fld_searchlesson_title);
                int courseIndex = cmb_searchlesson_course.getSelectedIndex();
                System.out.println(courseIndex);

                if (description && title && courseIndex == 0){
                    loadLessonModel();
                } else if (!description && !title && courseIndex != 0) {
                    //Pattern is @@@@ 1 = fld_searchlesson_des  2 = fld_searchlesson_title  3 = cmb_searchlesson_course

                    search_lesson_des = "%"+search_lesson_des + "%";
                    search_lesson_title = "%" + search_lesson_title + "%";

                    ArrayList<Lesson> lessons = Lesson.searchBy123(search_lesson_des, search_lesson_title, course_id);
                    loadLessonModel(lessons);
                } else if (description && !title && courseIndex != 0) {
                    //searchBy23
                    search_lesson_title = "%" + search_lesson_title + "%";
                    ArrayList<Lesson> lessons = Lesson.searchBy23(search_lesson_title, course_id);
                    loadLessonModel(lessons);

                } else if (!description && title && courseIndex != 0) {
                    //searchBy13

                    search_lesson_des = "%" + search_lesson_des + "%";
                    ArrayList<Lesson> lessons = Lesson.searchBy13(search_lesson_des, course_id);
                    loadLessonModel(lessons);
                    
                } else if (!description && !title && courseIndex == 0) {
                    //searchBy12
                    search_lesson_des = "%" + search_lesson_des + "%";
                    search_lesson_title = "%" + search_lesson_title + "%";

                    ArrayList<Lesson> lessons = Lesson.searchBy12(search_lesson_des, search_lesson_title);
                    loadLessonModel(lessons);
                    
                } else if (!description && title && courseIndex ==0) {
                    //searchBy1
                    search_lesson_des = "%" + search_lesson_des + "%";
                    ArrayList<Lesson> lessons = Lesson.searchBy1(search_lesson_des);
                    loadLessonModel(lessons);
                } else if (description && !title && courseIndex ==0) {
                    //searchBy2
                    search_lesson_title = "%" + search_lesson_title + "%";
                    ArrayList<Lesson> lessons = Lesson.searchBy2(search_lesson_title);
                    loadLessonModel(lessons);
                } else if (description && title && courseIndex != 0) {
                    String course_name = cmb_searchlesson_course.getItemAt(courseIndex).toString();
                    Course course = Course.getFetch(course_name);
                    int courseId = course.getId();
                    ArrayList<Lesson> lessons = Lesson.searchBy3(courseId);
                    loadLessonModel(lessons);

                }else {
                    Helper.showMsg("ERROR Please feedback to us");
                }


            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EduLessonAddDeleteGui addDelete = new EduLessonAddDeleteGui(edu);
            }
        });
    }









    private void load_cmb_searchlesson_course() {
        cmb_searchlesson_course.removeAllItems();
        cmb_searchlesson_course.addItem(new Item(0, ""));
        for (Course course: Course.getList()){

            if (course.getEducator().getName().equals(this.edu.getName())){
                cmb_searchlesson_course.addItem(new Item(course.getId(), course.getName()) );
            }
        }

    }


    private void loadLessonModel() {
        int rowCount = tbl_lesson_list.getRowCount();
        for (int i = rowCount-1; i>=0;i--){
            tbl_lesson_list.removeRowSelectionInterval(i,i);
        }
        int i;

        DefaultTableModel clearModel = (DefaultTableModel) tbl_lesson_list.getModel();
        clearModel.getDataVector().removeAllElements();
        clearModel.setRowCount(0);

        for (Lesson lesson: Lesson.getList()){

            if (lesson.getCourse().getEducator().getName().equals(this.edu.getName()))
            {

                i =0;
                row_lesson_list[i++] = lesson.getId();
                row_lesson_list[i++] = lesson.getTitle();
                row_lesson_list[i++] = lesson.getEducator().getName();
                row_lesson_list[i++] = lesson.getYoutube();
                row_lesson_list[i++] = lesson.getCourse().getName();
                row_lesson_list[i] = lesson.getDescription();
                mdl_lesson_list.addRow(row_lesson_list);
            }

        }


    }

    private void loadLessonModel(ArrayList<Lesson> lessons) {
        int rowCount = tbl_lesson_list.getRowCount();
        for (int i = rowCount-1; i>=0;i--){
            tbl_lesson_list.removeRowSelectionInterval(i,i);
        }
        int i;

        DefaultTableModel clearModel = (DefaultTableModel) tbl_lesson_list.getModel();
        clearModel.getDataVector().removeAllElements();
        clearModel.setRowCount(0);




        for (Lesson lesson: lessons){
            i =0;
            row_lesson_list[i++] = lesson.getId();
            row_lesson_list[i++] = lesson.getTitle();
            row_lesson_list[i++] = lesson.getEducator().getName();
            row_lesson_list[i++] = lesson.getYoutube();
            row_lesson_list[i++] = lesson.getCourse().getName();
            row_lesson_list[i] = lesson.getDescription();
            mdl_lesson_list.addRow(row_lesson_list);
        }


    }


    private void load_course_model() {

        int rowCount = tbl_course_list.getRowCount();
        for (int i = rowCount-1; i>=0;i--){
            tbl_course_list.removeRowSelectionInterval(i,i);
        }
        int i;

        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        for (Course course: Course.getList(edu.getId())){

            i=0;

            row_course_list[i++] = course.getId();
            row_course_list[i++] = course.getName();
            row_course_list[i++] = course.getLang();
            row_course_list[i++] = course.getPaths().getName();
            row_course_list[i++] = course.getEducator().getName();
            mdl_course_list.addRow(row_course_list);
        }

    }
}
