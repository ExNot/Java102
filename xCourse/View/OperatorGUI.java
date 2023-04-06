package xCourse.View;

import xCourse.Helper.Config;
import xCourse.Helper.Helper;
import xCourse.Model.Operator;
import xCourse.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {
    private JPanel wraper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_Exit;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JTextField fld_name;
    private JTextField fld_user_name;
    private JComboBox cmb_account_type;
    private JPasswordField fld_password;
    private JButton add_to_DB;
    private JTextField fld_delete_byId;
    private JButton btn_delete_byID;
    private JTextField fld_search_name;
    private JTextField fld_sch_username;
    private JPanel fld_search_username;
    private JComboBox cmb_search_accountType;
    private JButton btn_user_search;
    private JPanel fld_paths;
    private JScrollPane scrl_paths_pane;
    private JTable tbl_paths_list;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;

    private final Operator operator;

    public OperatorGUI(Operator operator) {
        this.operator = operator;


        add(wraper);
        setSize(1000, 500);
        int x = Helper.screenCenterLoc("x", getSize());
        int y = Helper.screenCenterLoc("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Welcome " + operator.getName());

        //MODEL USER LIST//
        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID", "Name", "User Name", "Password", "Account Type"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        row_user_list = new Object[col_user_list.length];


        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        loadUser();

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {

            try {
                String selected_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                fld_delete_byId.setText(selected_user_id);
            } catch (Exception exception) {

            }

        });

        tbl_user_list.getModel().addTableModelListener(e -> {

            if (e.getType() == TableModelEvent.UPDATE) {

                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString());
                String name_surname = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 1).toString();
                String user_name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 2).toString();
                String password = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 3).toString();
                String type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 4).toString();


                if (User.updateById(user_id, name_surname, user_name, password, type)) {
                    Helper.showMsg("done");
                    loadUser();
                }
                loadUser();
            }

        });


        add_to_DB.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_password)) {
                Helper.showMsg("fill");
            } else {
                String uname = fld_user_name.getText().toString();
                String name = fld_name.getText().toString();
                String accountType = cmb_account_type.getSelectedItem().toString();
                String pass = fld_password.getText().toString();

                if (User.add(name, uname, pass, accountType)) {
                    Helper.showMsg("done");
                    loadUser();

                    fld_password.setText(null);
                    fld_name.setText(null);
                    fld_user_name.setText(null);
                }


            }
        });
        btn_delete_byID.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_delete_byId)) {

                Helper.showMsg("fill");
            } else {
                int deleteById = Integer.parseInt(fld_delete_byId.getText());
                if (User.deleteById(deleteById)) {

                    Helper.showMsg("done");
                    loadUser();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        btn_user_search.addActionListener(e -> {
            String name = fld_search_name.getText().toString();
            String userName = fld_sch_username.getText().toString();
            String type = cmb_search_accountType.getSelectedItem().toString();
            String query = User.searchQuery(name, userName, type);
            loadUser(User.searchUserList(query));
        });
        btn_Exit.addActionListener(e -> {
            dispose();
        });
    }


    public void loadUser() {

        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for (User obj : User.getList()) {

            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUserName();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);

        }
    }

    public void loadUser(ArrayList<User> searchingUsers) {

        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for (User obj : searchingUsers) {

            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUserName();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);

        }
    }


    public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setId(1);
        op.setName("ee BB");
        op.setPassword("1234");
        op.setType("Operator");
        op.setUserName("BB ee");


        OperatorGUI opGui = new OperatorGUI(op);


    }

}
