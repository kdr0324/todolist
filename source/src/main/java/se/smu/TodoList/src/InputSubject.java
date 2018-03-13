import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class InputSubject extends JDialog {

	private JDialog contentPane;
	private JTextField textField;
	private JTextField textField_1;

	private Subject subject;

	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_3;
	JLabel lblNewLabel_4;
	JSpinner yearChooser;
	JSpinner spinner;
	JSpinner spinner_1;
	JSpinner spinner_2;
	JSpinner spinner_3;
	JScrollPane scrollPane;
	JTextArea remark;
	JButton btnNewButton;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public InputSubject() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // ���� dispose();
		setTitle("Input Subject - fofo");
		setBounds(100, 100, 600, 430);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		///////////////////////////////////////////////////////// �󺧺κ�

		lblNewLabel = new JLabel("����");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel.setBounds(27, 41, 78, 21);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("������");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_1.setBounds(25, 83, 78, 21);
		getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("�⵵/�б�");
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_2.setBounds(27, 122, 88, 21);
		getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("����/�ð�");
		lblNewLabel_3.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_3.setBounds(27, 163, 88, 21);
		getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("���");
		lblNewLabel_4.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_4.setBounds(27, 199, 78, 21);
		getContentPane().add(lblNewLabel_4);

		////////////////////////////////////////////////////////////////// �� ��

		// ���� �ؽ�Ʈ�ʵ�
		textField = new JTextField();
		textField.setBounds(120, 38, 230, 27);
		getContentPane().add(textField);
		textField.setColumns(10);

		// ������ �ؽ�Ʈ�ʵ�
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(120, 80, 230, 27);
		getContentPane().add(textField_1);

		// �⵵ ���ǳ�
		yearChooser = new JSpinner();
		yearChooser.setModel(new SpinnerNumberModel(2016, 1901, 2099, 1));
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(yearChooser, "#");
		yearChooser.setEditor(editor);
		yearChooser.setBounds(120, 120, 63, 27);
		getContentPane().add(yearChooser);

		// �б� ���ǳ�
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 2, 1));
		spinner.setBounds(200, 120, 33, 28);
		spinner.setFont(new Font("����", Font.PLAIN, 12));
		getContentPane().add(spinner);

		// ���� ���ǳ�
		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerListModel(new String[] { "������", "ȭ����", "������", "�����", "�ݿ���", "�����", "�Ͽ���" }));
		spinner_1.setBounds(120, 160, 113, 28);
		spinner_1.setFont(new Font("����", Font.PLAIN, 12));
		getContentPane().add(spinner_1);

		// �ð� ���ǳ�
		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner_2.setBounds(250, 160, 50, 28);
		spinner_2.setFont(new Font("����", Font.PLAIN, 12));
		getContentPane().add(spinner_2);

		// �� ���ǳ�
		spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner_3.setBounds(318, 160, 50, 28);
		spinner_3.setFont(new Font("����", Font.PLAIN, 12));
		getContentPane().add(spinner_3);

		// ���ĭ ��ũ���г�
		scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 199, 441, 119);
		getContentPane().add(scrollPane);

		// ���ĭ �ؽ�Ʈ������
		remark = new JTextArea();
		remark.setLineWrap(true);
		remark.setDocument(new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str == null || remark.getText().length() + str.length() >= 257) {
					return;
				}

				super.insertString(offs, str, a);
			}
		});
		scrollPane.setViewportView(remark);

		// �Է� ��ư
		btnNewButton = new JButton("�Է�");
		btnNewButton.setFont(new Font("����", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().isEmpty() || textField_1.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "��ĭ�� �Է����ּ���");
				else {
					subject = new Subject(textField.getText(), textField_1.getText(), spinner_1.getValue().toString(),
							Integer.parseInt(spinner_2.getValue().toString()),
							Integer.parseInt(spinner_3.getValue().toString()),
							Integer.parseInt(yearChooser.getValue().toString()),
							Integer.parseInt(spinner.getValue().toString()), remark.getText());
					dispose();
				}
			}
		});
		btnNewButton.setBounds(436, 330, 125, 29);
		getContentPane().add(btnNewButton);

	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
