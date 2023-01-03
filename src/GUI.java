import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class GUI {
    private JTextField textNamaMahasiswa;
    private JTextField textBesaranUKT;
    private JComboBox comboTanggal;
    private JComboBox comboBulan;
    private JComboBox comboTahun;
    private JButton simpanDataButton;
    private JTable tableOutputData;
    private JPanel RootPanel;
    private DefaultTableModel tableModel;
    private Mahasiswa mahasiswa;
    private NumberFormat numberFormat;

    //assign variable with 0 value for table numbering
    private int tableNumber = 1;
    public GUI() {
        //calling displaying all of table content method
        initTableContent();

        //initiate an oop class
        mahasiswa = new Mahasiswa();
        numberFormat = NumberFormat.getInstance(Locale.US);
        simpanDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //canceling operation when there was a blank field
                if (textNamaMahasiswa.getText().isBlank() || comboTanggal.getSelectedIndex() == 0 || comboBulan.getSelectedIndex() == 0 || comboTahun.getSelectedIndex() == 0 || textBesaranUKT.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Pastikan Data Anda Lengkap!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //getting nama mahasiswa value from text field
                String getNamaMahasiswa = textNamaMahasiswa.getText();

                //converting from an combobox format to localdate data type
                int getTGL, getBLN, getTHN;
                getTGL = Integer.parseInt(comboTanggal.getSelectedItem().toString());
                getBLN = Integer.parseInt(comboBulan.getSelectedItem().toString());
                getTHN = Integer.parseInt(comboTahun.getSelectedItem().toString());
                LocalDate getTglBlnThn = LocalDate.of(getTHN, getBLN, getTGL);

                //getting besaran ukt value from text field and make exception when it wasn't long type data value
                long getBesaranUKT;
                try {
                    getBesaranUKT = Long.parseLong(textBesaranUKT.getText().toString());
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Besaran UKT Hanya Menilai Input Berupa Angka!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //assign value to an oop with its setter
                mahasiswa.setNamaMahasiswa(getNamaMahasiswa);
                mahasiswa.setTanggalBulanTahunLahir(getTglBlnThn);
                mahasiswa.setBesaranUKT(getBesaranUKT);

                //displaying succesfull message
                JOptionPane.showMessageDialog(null, "Yeay, Data Berhasil Disimpan!", "Information", JOptionPane.INFORMATION_MESSAGE);

                //assigning new row value with getter from oop class
                Object[] rowValue = {tableNumber++ + ".",
                                    mahasiswa.getNamaMahasiswa(),
                                    mahasiswa.getTanggalBulanTahunLahir(),
                                    mahasiswa.getUsiaMahasiswa(),
                                    "Rp."+ numberFormat.format(mahasiswa.getBesaranUKT()),
                                    mahasiswa.getKategoriUKT()
                };

                tableModel.addRow(rowValue);

                //clearing all field value
                textNamaMahasiswa.setText("");
                JComboBox[] comboTglBlnThn = {comboTanggal, comboBulan, comboTahun};
                for(JComboBox ret: comboTglBlnThn){
                    ret.setSelectedIndex(0);
                }
                textBesaranUKT.setText("");
            }
        });
    }

    //getter for main screen panel
    public JPanel getRootPanel() {
        return RootPanel;
    }

    //method for displaying all of table content, including column name, and row content
    private void initTableContent(){

        //declaring an variable that assign column name, and row content
        Object[] Kolom = {"No.", "Nama Mahasiswa", "Tgl/Bln/Thn Lahir", "Usia Mahasiswa", "Besaran UKT (Rp)", "Kategori UKT"};
        Object[][] Baris = {};

        tableModel = new DefaultTableModel(Baris, Kolom);
        tableOutputData.setModel(tableModel);
        tableOutputData.setAutoCreateRowSorter(true);

        //centering all row content
        for(byte i = 0;i < Kolom.length; i++) {
            TableColumn col = tableOutputData.getColumnModel().getColumn(i);
            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
            dtcr.setHorizontalAlignment(SwingConstants.CENTER);
            col.setCellRenderer(dtcr);
        }

        //set number column width
        tableOutputData.getColumnModel().getColumn(0).setPreferredWidth(1);
        tableOutputData.getColumnModel().getColumn(3).setPreferredWidth(150);
    }

}
