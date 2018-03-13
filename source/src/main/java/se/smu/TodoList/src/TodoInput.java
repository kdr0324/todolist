import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class TodoInput extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private WhatTodo whatTodo;
	private int selectedSubject;
	private JComboBox comboSubject;
	private Calendar eDate;
	private Calendar rDate;
	private JLabel subjectLabel;
	private JLabel dateLabel;
	private JLabel rDateLabel;
	private JLabel toDoLabel;
	private JLabel label;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JCheckBox ChkImportant;
	private JCheckBox chkFinish;
	private JButton okButton;
	private JLabel lblNewLabel_1;
	private JSpinner spinner_2;
	private JLabel label_1;
	private JSpinner spinner_3;
	private JLabel label_2;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JSpinner spinner_4;
	private JSpinner eDateChooser;
	private JSpinner rDateChooser;
	private SpinnerDateModel dateModel;
	private SpinnerDateModel rdateModel;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public TodoInput(String[] subject) {

		// �����ӻ���
		setModal(true); // JDialog â ������ �ٸ� â ����
		setTitle("To do Input - fofo");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // ���� ó�� dispose()
		setBounds(100, 100, 640, 360);
		getContentPane().setLayout(new BorderLayout()); // ���� ���̾ƿ�
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		///////////////////////////////////////// ��

		subjectLabel = new JLabel("����");
		subjectLabel.setFont(new Font("����", Font.BOLD, 18));
		subjectLabel.setBounds(17, 24, 78, 21);
		contentPanel.add(subjectLabel);

		toDoLabel = new JLabel("To do");
		toDoLabel.setFont(new Font("����", Font.BOLD, 18));
		toDoLabel.setBounds(17, 161, 78, 21);
		contentPanel.add(toDoLabel);

		dateLabel = new JLabel("�����Ⱓ");
		dateLabel.setFont(new Font("����", Font.BOLD, 18));
		dateLabel.setBounds(17, 67, 116, 21);
		contentPanel.add(dateLabel);

		rDateLabel = new JLabel("����������");
		rDateLabel.setFont(new Font("����", Font.BOLD, 18));
		rDateLabel.setBounds(17, 113, 95, 21);
		contentPanel.add(rDateLabel);

		JLabel lblNewLabel = new JLabel("��");
		lblNewLabel.setBounds(300, 74, 18, 21);
		contentPanel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("��");
		lblNewLabel_1.setBounds(354, 75, 78, 21);
		contentPanel.add(lblNewLabel_1);

		label_1 = new JLabel("��");
		label_1.setBounds(300, 115, 18, 21);
		contentPanel.add(label_1);

		label_2 = new JLabel("��");
		label_2.setBounds(354, 116, 78, 21);
		contentPanel.add(label_2);

		////////////////////////////////////////// �� ��

		// ComboBox
		comboSubject = new JComboBox(subject); // ���� �� ComboBox
		comboSubject.setBounds(132, 18, 171, 27);
		comboSubject.setFont(new Font("����", Font.PLAIN, 12));
		contentPanel.add(comboSubject);

		// �����Ⱓ
		dateModel = new SpinnerDateModel();
		eDateChooser = new JSpinner(dateModel);
		JSpinner.DateEditor edt = new JSpinner.DateEditor(eDateChooser,"yyyy-MM-dd");
		eDateChooser.setEditor(edt);
		eDateChooser.setBounds(132, 67, 129, 27);
		contentPanel.add(eDateChooser);
		// ���� �����Ⱓ
		rdateModel = new SpinnerDateModel();
		rDateChooser = new JSpinner(rdateModel);
		JSpinner.DateEditor rdt = new JSpinner.DateEditor(rDateChooser,"yyyy-MM-dd");
		rDateChooser.setEditor(rdt);
		rDateChooser.setBounds(132, 109, 129, 27);
		contentPanel.add(rDateChooser);

		// �ð� �Է�
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner.setBounds(265, 67, 33, 28);
		spinner.setFont(new Font("����", Font.PLAIN, 12));
		contentPanel.add(spinner);

		// �� �Է�
		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_1.setBounds(321, 68, 33, 28);
		spinner_1.setFont(new Font("����", Font.PLAIN, 12));
		contentPanel.add(spinner_1);

		// �ð��Է� (��������)
		spinner_2 = new JSpinner();
		spinner_2.setBounds(265, 108, 33, 28);
		spinner_2.setFont(new Font("����", Font.PLAIN, 12));
		contentPanel.add(spinner_2);

		// �� �Է�(��������)
		spinner_3 = new JSpinner();
		spinner_3.setBounds(321, 108, 33, 28);
		spinner_3.setFont(new Font("����", Font.PLAIN, 12));
		contentPanel.add(spinner_3);

		// To do ScrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBounds(132, 151, 476, 100);
		contentPanel.add(scrollPane);

		// To do TextArea
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		// ��ư�г�
		{
			//�۾�ü ��Ʈ
			// ��ư�г� , �÷ο췹�̾ƿ�
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			// �߿�üũ
			ChkImportant = new JCheckBox("��");
			ChkImportant.setBackground(Color.WHITE);
			ChkImportant.setFont(new Font("����", Font.BOLD, 18));
			buttonPane.add(ChkImportant);
			{
				// �Ϸ�üũ
				chkFinish = new JCheckBox("�Ϸ�");
				chkFinish.setBackground(Color.WHITE);
				chkFinish.setFont(new Font("����", Font.BOLD, 18));
				buttonPane.add(chkFinish);

				// �Է¹�ư
				{
					okButton = new JButton("�Է�");
					okButton.setFont(new Font("����", Font.BOLD, 18));
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (textArea.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "��ĭ�� �Է����ּ���");
							} else {
								Calendar eCal = Calendar.getInstance();
								Calendar rCal = Calendar.getInstance();
								eCal.setTime(dateModel.getDate());
								rCal.setTime(rdateModel.getDate());
								seteDate(eCal, Integer.parseInt(spinner.getValue().toString()),
										Integer.parseInt(spinner_1.getValue().toString()));
								if (rCal != null) {
									setrDate(rCal,
											Integer.parseInt(spinner_2.getValue().toString()),
											Integer.parseInt(spinner_3.getValue().toString()));
								} else {
									rDate = geteDate();
								}
								selectedSubject = comboSubject.getSelectedIndex();
								whatTodo = new WhatTodo(comboSubject.getSelectedItem().toString(),
										/* sDateChooser.getCalendar(), */ eDate, rDate, ChkImportant.isSelected(),
										chkFinish.isSelected(), textArea.getText());
								dispose();
							}

							;
						}
					});
					//okButton.setActionCommand("�Է�");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
			}
		}
	}

	public WhatTodo getWhatTodo() {
		return whatTodo;
	}

	public void setWhatTodo(WhatTodo whatTodo) {
		this.whatTodo = whatTodo;
	}

	public int getSelectedSubject() {
		return selectedSubject;
	}

	public void setSelectedSubject(int selectedSubject) {
		this.selectedSubject = selectedSubject;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JComboBox getComboSubject() {
		return comboSubject;
	}

	public void setComboSubject(String subj) {
		for (int i = 0; i < comboSubject.getItemCount(); i++)
			if (subj == comboSubject.getItemAt(i))
				comboSubject.setSelectedIndex(i);
	}



	public void seteDateChooser(Calendar cal) {
		dateModel.setValue(cal.getTime());
	}

	public void setrDateChooser(Calendar cal) {
		rdateModel.setValue(cal.getTime());
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(String todo) {
		textArea.setText(todo);
	}

	public JCheckBox getChkImportant() {
		return ChkImportant;
	}

	public void setChkImportant(boolean bool) {
		ChkImportant.setSelected(bool);
		;
	}

	public JCheckBox getChkFinish() {
		return chkFinish;
	}

	public void setChkFinish(boolean bool) {
		chkFinish.setSelected(bool);
	}

	public Calendar geteDate() {
		return eDate;
	}

	public void seteDate(Calendar eDate, int ehour, int eminute) {
		this.eDate = eDate;
		this.eDate.set(Calendar.HOUR_OF_DAY, ehour);
		this.eDate.set(Calendar.MINUTE, eminute);
	}

	public Calendar getrDate() {
		return rDate;
	}

	public void setrDate(Calendar rDate, int rhour, int rminute) {
		this.rDate = rDate;
		this.rDate.set(Calendar.HOUR_OF_DAY, rhour);
		this.rDate.set(Calendar.MINUTE, rminute);
	}

	public void seteHour(Calendar cal) {
		this.spinner.setValue(cal.get(Calendar.HOUR_OF_DAY));
		this.spinner_1.setValue(cal.get(Calendar.MINUTE));
	}


	public void setrHour(Calendar cal) {
		this.spinner_2.setValue(cal.get(Calendar.HOUR_OF_DAY));
		this.spinner_3.setValue(cal.get(Calendar.MINUTE));
	}


	

}
