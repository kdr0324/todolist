import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ToFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table2;
	private ArrayList<Subject> arrSubject = new ArrayList<Subject>();
	private ArrayList<WhatTodo> WTD = new ArrayList<WhatTodo>(); // 과목별 TodoList
																	// 객체 생성

	private TableRowSorter Tsorter;
	private String columnNames[] = { "과목", "교수님", "요일", "시간", "년도/학기", "비고" };
	private String rowData[][];
	private DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames) {
		public boolean isCellEditable(int i, int c) {
			return false;
		}
	};

	private String columnNames2[] = { "To do", "과목", /* "시작일", */ "마감일", "실제마감일", "★", "완료여부" };
	private String rowData2[][];
	private DefaultTableModel defaultTableModel2 = new DefaultTableModel(rowData2, columnNames2) {
		public boolean isCellEditable(int i, int c) {
			return false;
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToFrame frame = new ToFrame();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ToFrame() {
		setTitle("ToFrame - fofo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 780);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton();
		btnNewButton.setIcon(new ImageIcon("plus.png"));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setToolTipText("과목 추가");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 과목입력 프레임 생성
				InputSubject inSubject = new InputSubject();
				inSubject.setVisible(true);
				// 과목입력 프레임 종료
				// Subject 객체 ArrayList에 삽입
				if (inSubject.getSubject() != null) {
					arrSubject.add(inSubject.getSubject());
					UpdateTable();
				}
			}
		});
		btnNewButton.setBounds(30, 76, 50, 50);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setIcon(new ImageIcon("plus.png"));
		btnNewButton_1.setToolTipText("할일추가");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrSubject.isEmpty() == false) {
					// 과목배열 넘김
					String array[] = new String[arrSubject.size()];
					for (int j = 0; j < arrSubject.size(); j++) {
						array[j] = arrSubject.get(j).getSubjectName();
					}
					// 투두인풋 프레임 생성
					TodoInput toDoSubject = new TodoInput(array);
					toDoSubject.setVisible(true);
					// 과목객체에 투두객체 생성
					//
					if (toDoSubject.getWhatTodo() != null) {
						// arrSubject.get(toDoSubject.getSelectedSubject()).WTD.add(toDoSubject.getWhatTodo());
						// 투두객체 ArrayList에 삽입

						WTD.add(toDoSubject.getWhatTodo());
						WTD.get(WTD.size() - 1).startThread();
					}

					UpdateTable1();
				} else
					JOptionPane.showMessageDialog(null, "과목을 먼저 입력해주세요");
			}
		});
		btnNewButton_1.setBounds(30, 345, 50, 50);
		contentPane.add(btnNewButton_1);

		JButton button = new JButton();
		button.setIcon(new ImageIcon("edit.png"));
		button.setToolTipText("수정");
		button.setBorderPainted(false);
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// 과목이름 넘겨주기
				if (table2.getSelectedRow() != -1) {
					String array[] = new String[arrSubject.size()];
					for (int j = 0; j < arrSubject.size(); j++) {
						array[j] = arrSubject.get(j).getSubjectName();
					}
					// 입력(수정)프레임 생성
					TodoInput toDoSubject = new TodoInput(array);
					toDoSubject.setChkFinish(WTD.get(table2.getSelectedRow()).isSuccess());
					toDoSubject.setChkImportant(
							WTD.get(table2.convertRowIndexToModel(table2.getSelectedRow())).isImportant());
					toDoSubject.seteDateChooser(
							WTD.get(table2.convertRowIndexToModel(table2.getSelectedRow())).geteCalendar());
					// 시간, 분 set
					toDoSubject.seteDateChooser(
							WTD.get(table2.convertRowIndexToModel(table2.getSelectedRow())).geteCalendar());
					// 실제마감 시간, 분 set
					toDoSubject
							.seteHour(WTD.get(table2.convertRowIndexToModel(table2.getSelectedRow())).geteCalendar());

					toDoSubject
							.setrHour(WTD.get(table2.convertRowIndexToModel(table2.getSelectedRow())).getrCalendar());
					toDoSubject
							.setTextArea(WTD.get(table2.convertRowIndexToModel(table2.getSelectedRow())).getContent());
					toDoSubject.setComboSubject(
							WTD.get(table2.convertRowIndexToModel(table2.getSelectedRow())).getsName());
					toDoSubject.setVisible(true);
					// System.out.println(table2.convertRowIndexToModel(table2.getSelectedRow()));
					// 과목객체에 투두객체 생성
					// 선택된 열에 입력한 정보에 맞게 입력(수정) 후 테이블 업데이트
					if (toDoSubject.getWhatTodo() != null) {
						WTD.get(table2.convertRowIndexToModel(table2.getSelectedRow())).killThread();
						WTD.remove(table2.convertRowIndexToModel(table2.getSelectedRow()));
						WTD.add(table2.convertRowIndexToModel(table2.getSelectedRow()), toDoSubject.getWhatTodo());
						WTD.get(table2.convertRowIndexToModel(table2.getSelectedRow())).startThread();
						// WTD.set(table2.convertRowIndexToModel(table2.getSelectedRow()),
						// toDoSubject.getWhatTodo());
						UpdateTable1();
					}
				} else
					JOptionPane.showMessageDialog(null, "수정할 열을 선택해주세요");
			}
		});
		button.setBounds(97, 345, 50, 50);
		contentPane.add(button);

		JButton button_1 = new JButton();
		button_1.setIcon(new ImageIcon("delete.png"));
		button_1.setToolTipText("삭제");
		button_1.setBorderPainted(false);
		button_1.setBackground(Color.WHITE);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 선택된 열 삭제 후 테이블 업데이트
				if (table2.getSelectedRow() != -1) {
					WTD.get(table2.convertRowIndexToModel(table2.getSelectedRow())).killThread();
					WTD.remove(table2.convertRowIndexToModel(table2.getSelectedRow()));
					UpdateTable1();
				} else
					JOptionPane.showMessageDialog(null, "삭제할 행을 선택해주세요");
			}
		});

		button_1.setBounds(164, 345, 50, 50);
		contentPane.add(button_1);

		Image img = Toolkit.getDefaultToolkit().getImage("memo.jpg");
		JScrollPane scrollPane = new JScrollPane() {
			{
				setOpaque(false);
				getViewport().setOpaque(false);
			}
		};
		scrollPane.setBackground(Color.WHITE);

		scrollPane.setBounds(30, 137, 1100, 200);
		contentPane.add(scrollPane);

		// Jtable 생성, 이미지 삽입
		table = new JTable(defaultTableModel);

		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setPreferredSize(new Dimension(100, 30));
		table.getTableHeader().setFont(new Font("굴림", Font.PLAIN, 16));
		table.getTableHeader().setBackground(Color.WHITE);
		table.setBackground(Color.WHITE);
		table.setFont(new Font("굴림", Font.PLAIN, 12));

		table.setRowHeight(25);
		table.setCellSelectionEnabled(true);

		ToolTipManager.sharedInstance().setDismissDelay(60000);
		// table.setToolTipText("눌러주세요");

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 비고 툴팁텍스트 출력
				String strRemark = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
				table.setToolTipText("<html><p width=\"500\">" + strRemark + "</p></html>");
			}
		});
		table.setVisible(true);
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane() {
			{
				setOpaque(false);
				getViewport().setOpaque(false);
			}
		};
		scrollPane_1.setBackground(Color.WHITE);
		scrollPane_1.setBounds(30, 397, 1100, 300);
		contentPane.add(scrollPane_1);
		table2 = new JTable(defaultTableModel2);
		table2.setFont(new Font("굴림", Font.PLAIN, 12));
		table2.setRowHeight(25);
		table2.getTableHeader().setReorderingAllowed(false);
		table2.getTableHeader().setPreferredSize(new Dimension(100, 30));
		table2.getTableHeader().setFont(new Font("굴림", Font.PLAIN, 16));
		table2.getTableHeader().setBackground(Color.WHITE);

		// 오름차순 , 내림차순 정렬
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(defaultTableModel2);
		table2.setRowSorter(sorter);

		table2.setVisible(true);

		// to do 테이블 column 넓이 지정
		TableColumn column = null;
		for (int i = 0; i < table2.getColumnCount(); i++) {
			column = table2.getColumnModel().getColumn(i);
			DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
			tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			if (i == 0) {
				column.setPreferredWidth(500); // to do column is bigger
			} else if (i == 5) {
				column.setPreferredWidth(50);
				column.setCellRenderer(tScheduleCellRenderer);
			} else if (i == 4) {
				column.setPreferredWidth(20);
				column.setCellRenderer(tScheduleCellRenderer);
			} else {
				column.setPreferredWidth(120);
				column.setCellRenderer(tScheduleCellRenderer);
			}
		}

		column = null;
		for (int i = 0; i < table2.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
			tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			if (i == 0) {
				column.setCellRenderer(tScheduleCellRenderer);
				column.setPreferredWidth(150); // to do column is bigger
			} else if (i == 1 || i == 2 || i == 3 || i == 4) {
				column.setCellRenderer(tScheduleCellRenderer);
				column.setPreferredWidth(50);
			} else {
				column.setPreferredWidth(400);
			}
		}
		scrollPane_1.setViewportView(table2);

		JButton btnLoad = new JButton();
		btnLoad.setBorderPainted(false);
		btnLoad.setToolTipText("불러오기");
		btnLoad.setBackground(Color.WHITE);
		btnLoad.setIcon(new ImageIcon("loadImg.png"));
		btnLoad.setFont(new Font("굴림", Font.BOLD, 18));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FileInputStream fis = new FileInputStream("subject.dat");
					if (!fis.equals(null)) {
						ObjectInputStream oos = new ObjectInputStream(fis);
						arrSubject = (ArrayList<Subject>) oos.readObject();
						oos.close();
						fis.close();
						UpdateTable();
					}
				} catch (EOFException e) {
					JOptionPane.showMessageDialog(null, "저장된 파일이 없습니다.");
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "저장된 파일이 없습니다.");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "저장된 파일이 없습니다.");
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "저장된 파일이 없습니다.");
				}

				try {
					FileInputStream fis_todo = new FileInputStream("todo.dat");
					if (!fis_todo.equals(null)) {
						ObjectInputStream oos_todo = new ObjectInputStream(fis_todo);
						for (int i = 0; i < WTD.size(); i++) {
							WTD.get(i).killThread();
						}
						WTD = (ArrayList<WhatTodo>) oos_todo.readObject();
						fis_todo.close();
						oos_todo.close();
						for (int i = 0; i < WTD.size(); i++) {
							WTD.get(i).startThread();
						}
						UpdateTable1();
					}
				} catch (EOFException e) {
					// JOptionPane.showMessageDialog(null, "저장된 파일이 없습니다.");
				} catch (FileNotFoundException e) {
					// JOptionPane.showMessageDialog(null, "저장된 파일이 없습니다.");
				} catch (IOException e) {
					// JOptionPane.showMessageDialog(null, "저장된 파일이 없습니다.");
				} catch (ClassNotFoundException e) {
					// JOptionPane.showMessageDialog(null, "저장된 파일이 없습니다.");
				}

			}
		});
		btnLoad.setBounds(1080, 76, 50, 50);
		contentPane.add(btnLoad);

		JButton btnSave = new JButton(/* "저장하기" */);
		btnSave.setBorderPainted(false);
		btnSave.setToolTipText("저장");
		btnSave.setBackground(Color.WHITE);
		btnSave.setIcon(new ImageIcon("saveImg.png"));
		btnSave.setFont(new Font("굴림", Font.BOLD, 18));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FileOutputStream fos;
				try {
					if (!arrSubject.isEmpty()) {
						int result = 1;
						result = JOptionPane.showConfirmDialog(null, "저장하시겠습니까?", "저장", JOptionPane.YES_NO_OPTION,
								JOptionPane.INFORMATION_MESSAGE);
						if (result == 0) {
							{
								ObjectOutputStream oos_subject = new ObjectOutputStream(
										new FileOutputStream("subject.dat"));
								oos_subject.writeObject(arrSubject);
								oos_subject.close();
							}
							if (!WTD.isEmpty()) {
								ObjectOutputStream oos_todo = new ObjectOutputStream(new FileOutputStream("todo.dat"));
								oos_todo.writeObject(WTD);
								oos_todo.close();// 스트림 닫기
							} else {
								File file = new File("todo.dat");
								if (file.exists()) {
									file.delete();
								}
							}
						}
					} else
						JOptionPane.showMessageDialog(null, "저장할 내용이 없습니다");

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "저장할 내용이 없습니다");
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "저장할 내용이 없습니다");
				}

			}
		});
		btnSave.setBounds(1014, 76, 50, 50);
		contentPane.add(btnSave);

		JLabel headLine = new JLabel("To do List");
		headLine.setFont(new Font("굴림", Font.PLAIN, 50));
		headLine.setBounds(486, 52, 256, 58);
		contentPane.add(headLine);

	}

	public void UpdateTable() {
		try {
			for (int i = defaultTableModel.getRowCount() - 1; i >= 0; i--) {
				defaultTableModel.removeRow(i);

			}
			for (int i = 0; i < arrSubject.size(); i++) {
				defaultTableModel.addRow(arrSubject.get(i).getRowData());

			}
			table.updateUI();

		} catch (ArrayIndexOutOfBoundsException e) {

		}
	}

	public void UpdateTable1() {
		try {
			for (int i = defaultTableModel2.getRowCount() - 1; i >= 0; i--) {
				defaultTableModel2.removeRow(i);
			}
			for (int i = 0; i < WTD.size(); i++) {
				defaultTableModel2.addRow(WTD.get(i).getRowData());
			}
			table2.updateUI();
		} catch (ArrayIndexOutOfBoundsException e) {
			// 예외
		}
	}
}
