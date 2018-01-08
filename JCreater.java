//package JCreater;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.swing.text.*;
import javax.swing.undo.*;
import javax.swing.filechooser.*;

class JCreater  extends WindowAdapter implements ActionListener,InternalFrameListener
{
	JFrame f;
	JMenuBar mb;
	JMenu file,edit,windows,format,execute,skins;
	JMenuItem neu,open,save,saveas,print,exit,find,replace,colorchoser,cut,copy,paste,selectall,delete,undo,redo,compile,run,nimbus,metal,liquid,sea,tattoo;
	JCheckBoxMenuItem bold,italic,linewrap;
	KeyStroke ks;
	JPanel switchwindow;
	JDesktopPane jdp;
	JInternalFrame jif[]=new JInternalFrame[100];
	JTextArea ta[]=new JTextArea[100];
	int count=0;
	JToolBar tb=new JToolBar();
	JButton b[]=new JButton[10];
	String cuttedstring;
	boolean cutclicked;
	JMenuItem jmi[]=new JMenuItem[100];
	JTextField tf1,tf2;
	JButton dfind,dreplace,dreplaceall;
	int startindex=0;
	boolean change[]=new boolean[100];
	Dialog d;
	String namesoficons[]={"New","Open","Save","SaveAs","Print","Cut","Copy","Paste","Find","Replace"};
	ImageIcon ii;
	java.net.URL ur;
	UndoManager um[]=new UndoManager[100];
	boolean undostate[]=new boolean[100];
	boolean redostate[]=new boolean[100];
	JTextArea executewindow;
	JScrollPane sp1;
	
	JCreater()
	{
		f=new JFrame();
		f.setSize(1000,1000);
		f.setLocation(10,10);
		
		for(int i=0;i<10;i++)
		{
			//java.net.URL ur;
			b[i]=new JButton();
			ur=this.getClass().getResource("/Icons/"+i+".png");
			ii=new ImageIcon(ur);
			b[i].setIcon(ii);
			b[i].setToolTipText(namesoficons[i]);
			tb.add(b[i]);
			b[i].addActionListener(this);
			
			if(i!=0&&i!=1)
			{
				b[i].setEnabled(false);
			}
		}
		
		f.add(tb,"North");
		mb=new JMenuBar();
		
		file=new JMenu("File");
		edit=new JMenu("Edit");
		format=new JMenu("Format");
		windows=new JMenu("Windows");
		execute=new JMenu("Execute");
		skins=new JMenu("Skins");
		
		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		format.setMnemonic(KeyEvent.VK_O);
		windows.setMnemonic(KeyEvent.VK_W);
		
		ur=this.getClass().getResource("/Icons/"+0+".png");
		ii=new ImageIcon(ur);
		neu=new JMenuItem("New",ii);
		ur=this.getClass().getResource("/Icons/"+1+".png");
		ii=new ImageIcon(ur);
		open=new JMenuItem("Open",ii);
		ur=this.getClass().getResource("/Icons/"+2+".png");
		ii=new ImageIcon(ur);
		save=new JMenuItem("Save",ii);
		ur=this.getClass().getResource("/Icons/"+3+".png");
		ii=new ImageIcon(ur);
		saveas=new JMenuItem("Saveas",ii);
		exit=new JMenuItem("Exit");
		ur=this.getClass().getResource("/Icons/"+5+".png");
		ii=new ImageIcon(ur);
		cut=new JMenuItem("Cut",ii);
		ur=this.getClass().getResource("/Icons/"+6+".png");
		ii=new ImageIcon(ur);
		copy=new JMenuItem("Copy",ii);
		ur=this.getClass().getResource("/Icons/"+7+".png");
		ii=new ImageIcon(ur);
		paste=new JMenuItem("Paste",ii);
		paste.setEnabled(false);
		b[7].setEnabled(false);
		ur=this.getClass().getResource("/Icons/"+4+".png");
		ii=new ImageIcon(ur);
		print=new JMenuItem("Print",ii);
		ur=this.getClass().getResource("/Icons/"+8+".png");
		ii=new ImageIcon(ur);
		find=new JMenuItem("Find",ii);
		ur=this.getClass().getResource("/Icons/"+9+".png");
		ii=new ImageIcon(ur);
		replace=new JMenuItem("Replace",ii);
		colorchoser=new JMenuItem("Color Choser");
		bold=new JCheckBoxMenuItem("BOLD");
		italic=new JCheckBoxMenuItem("ITALIC");
		linewrap=new JCheckBoxMenuItem("LINE WRAP");
		selectall=new JMenuItem("Select All");
		delete=new JMenuItem("Delete");
		undo=new JMenuItem("Undo");
		redo=new JMenuItem("Redo");
		compile=new JMenuItem("Compile");
		run=new JMenuItem("Run");
		metal=new JMenuItem("Metal");
		nimbus=new JMenuItem("Nimbus");
		sea=new JMenuItem("Sea glass");
		liquid=new JMenuItem("Liquidfied");
		tattoo=new JMenuItem("Tattoo");
		
		neu.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		saveas.addActionListener(this);
		print.addActionListener(this);
		exit.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		find.addActionListener(this);
		replace.addActionListener(this);
		bold.addActionListener(this);
		italic.addActionListener(this);
		colorchoser.addActionListener(this);
		linewrap.addActionListener(this);
		selectall.addActionListener(this);
		delete.addActionListener(this);
		undo.addActionListener(this);
		redo.addActionListener(this);
		compile.addActionListener(this);
		run.addActionListener(this);
		metal.addActionListener(this);
		nimbus.addActionListener(this);
		sea.addActionListener(this);
		liquid.addActionListener(this);
		tattoo.addActionListener(this);

		ks=KeyStroke.getKeyStroke('N',InputEvent.CTRL_DOWN_MASK);
		neu.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('O',InputEvent.CTRL_DOWN_MASK);
		open.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('S',InputEvent.CTRL_DOWN_MASK);
		save.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('S',InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK);
		saveas.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('P',InputEvent.CTRL_DOWN_MASK);
		print.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('X',InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK);
		exit.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('X',InputEvent.CTRL_DOWN_MASK);
		cut.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('C',InputEvent.CTRL_DOWN_MASK);
		copy.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('V',InputEvent.CTRL_DOWN_MASK);
		paste.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('F',InputEvent.CTRL_DOWN_MASK);
		find.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('H',InputEvent.CTRL_DOWN_MASK);
		replace.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('A',InputEvent.CTRL_DOWN_MASK);
		selectall.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('D',InputEvent.CTRL_DOWN_MASK);
		delete.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('Z',InputEvent.CTRL_DOWN_MASK);
		undo.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke('Y',InputEvent.CTRL_DOWN_MASK);
		redo.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke(KeyEvent.VK_F7,0);
		compile.setAccelerator(ks);
		ks=KeyStroke.getKeyStroke(KeyEvent.VK_F8,0);
		run.setAccelerator(ks);
		
		file.add(neu);
		file.addSeparator();
		file.add(open);
		file.addSeparator();
		file.add(save);
		file.add(saveas);
		file.addSeparator();
		file.add(print);
		file.addSeparator();
		file.add(exit);
		
		edit.add(undo);
		edit.add(redo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.addSeparator();
		edit.add(delete);
		edit.add(selectall);
		edit.addSeparator();
		edit.add(find);
		edit.add(replace);
		
		
		format.add(bold);
		format.add(italic);
		format.add(linewrap);
		format.addSeparator();
		format.add(colorchoser);
		
		execute.add(compile);
		execute.add(run);
		
		skins.add(metal);
		skins.add(nimbus);
		skins.add(liquid);
		skins.add(sea);
		skins.add(tattoo);
		
		edit.setEnabled(false);
		format.setEnabled(false);
		save.setEnabled(false);
		saveas.setEnabled(false);
		print.setEnabled(false);
		execute.setEnabled(false);
		//exit.setEnabled(false);
		
		mb.add(file);
		mb.add(edit);
		mb.add(format);
		mb.add(windows);
		mb.add(execute);
		mb.add(skins);
		
		jdp=new JDesktopPane();
		f.add(jdp);
		switchwindow=new JPanel();
		//jdp.setLayout(new BorderLayout());
		//jdp.add(switchwindow,"South");
		f.setJMenuBar(mb);
		try
		{
		//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		//javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme(new javax.swing.plaf.metal.DefaultMetalTheme());
		//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		
		SwingUtilities.updateComponentTreeUI(f);
		}
		catch(Exception ex)
		{
			ex.getMessage();
			System.out.println("Exception in look and Feel");
		}
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		f.addWindowListener(this);
	}
	
	public static void main(String arg[])
	{
			JCreater obj=new JCreater();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==neu||e.getSource()==b[0])
		{
			setExecuteWindow(false);
			try
			{ 
			if(jif[count]!=null && count!=0)
			{
				jif[count].hide();
				count++;
			}
			
			else
			{
						save.setEnabled(true);
						saveas.setEnabled(true);
						print.setEnabled(true);
					for(int i=2;i<10;i++)
					{
						b[i].setEnabled(true);
					}
					b[7].setEnabled(false);
					edit.setEnabled(true);
					format.setEnabled(true);
					//exit.setEnabled(true);
					count++;
			}
			
			undostate[count]=false;
			redostate[count]=false;
			undo.setEnabled(false);
			redo.setEnabled(false);
			
			jif[count]=new JInternalFrame("Untitled",true,true,true,true);
			ta[count]=new JTextArea();
			jif[count].setSize(1000,1000);
			jif[count].setLocation(0,0);
			jif[count].setVisible(true);
			jif[count].setLayout(new BorderLayout());
			JScrollPane sc=new JScrollPane(ta[count]);
			jif[count].add(sc);
			jdp.add(jif[count]);
			jif[count].setMaximum(true);
			}
			catch(Exception ex)
			{
					System.out.println(ex.getMessage());
			}
			//System.out.println("I WAS HRERE");
			jmi[count]=new JMenuItem(jif[count].getTitle());
			
			jmi[count].addActionListener(new ActionListener()
										{
												public void actionPerformed(ActionEvent ae)
												{
													for(int i=0;i<=count;i++)
													{
														if(ae.getSource()==jmi[i])
														{
															 jif[getSelectedFrameNumber()].hide();
															jif[i].show();
															System.out.println("I AM HERE  "+i);
															jdp.setSelectedFrame(jif[i]);
															
															redo.setEnabled(redostate[i]);
															undo.setEnabled(undostate[i]);
															setExcute(new File(jif[i].getTitle()));
															setExecuteWindow(false);
														}
													}
												}
										}
										);
										
			windows.add(jmi[count]);
			jif[count].setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			jif[count].addInternalFrameListener(this);
			
			Document doc;
			doc=ta[count].getDocument();
			um[count]=new UndoManager();
			doc.addUndoableEditListener(um[count]);
			
			doc.addDocumentListener(new DocumentListener()
										{
											public void insertUpdate(DocumentEvent e)
											{
												change[count]=true;
												undostate[count]=true;
												undo.setEnabled(true);
											}
											
											public void changedUpdate(DocumentEvent e)
											{
												change[count]=true;
												undostate[count]=true;
												undo.setEnabled(true);
											}
											
											public void removeUpdate(DocumentEvent e)
											{
												change[count]=true;
												undostate[count]=true;
												undo.setEnabled(true);
											}
													
										});
										
										//System.out.println("I WAS HRERE!!!!!!!!!!!!!!!!!");
		}
		else if(e.getSource()==open||e.getSource()==b[1])
		{
			try
			{
				int r;
			JFileChooser jfc=new JFileChooser();
			FileNameExtensionFilter fn=new FileNameExtensionFilter("Java Files Only","java");
			jfc.setFileFilter(fn);
			r=jfc.showOpenDialog(f);
			
			if(r!=JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			
			actionPerformed(new ActionEvent(neu,0,"New"));
			File pointer=jfc.getSelectedFile();
			setExcute(pointer);
			
			FileInputStream fis=new FileInputStream(pointer);
			//jdp.
			int presi=getSelectedFrameNumber();
			jif[presi].setTitle(pointer.getPath());
			jmi[presi].setText(pointer.getName());
		
			int i=0;
			while((i=fis.read())!=-1)
			{
					ta[presi].append(""+(char)i);
			}
			}
			catch (Exception ex)
			{
				System.out.println("Exception");
			}
			int presi=getSelectedFrameNumber();
			undostate[presi]=false;
			undo.setEnabled(false);
			redostate[presi]=false;
			redo.setEnabled(false);
			
			change[presi]=false;
		}
		else if(e.getSource()==save||e.getSource()==b[2])
		{
			int presindex=getSelectedFrameNumber();
			
			if(jif[presindex].getTitle()=="Untitled")
			{
				actionPerformed(new ActionEvent(saveas,0,"Saveas"));
			}
			
			undostate[presindex]=false;
			undo.setEnabled(false);
			redostate[presindex]=false;
			redo.setEnabled(false);
	
			String location=jif[presindex].getTitle();
			ta[presindex].selectAll();
			String data=ta[presindex].getSelectedText();
			change[presindex]=false;
			ta[presindex].select(0,0);

			try
			{
				FileOutputStream fos=new FileOutputStream(location);
				
				for(int i=0;i<data.length();i++)
				{
					fos.write(data.charAt(i));
				}
				fos.close();
			}
			catch(Exception ex)
			{
				System.out.println("Excption");
			}
		}
		else if(e.getSource()==saveas||e.getSource()==b[3])
		{
			JFileChooser fd=new JFileChooser();
			int r=fd.showSaveDialog(f);
			
			if(r!=JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			
			int presindex=getSelectedFrameNumber();
			
			File select=fd.getSelectedFile();
			
			ta[presindex].selectAll();
			String data=ta[presindex].getSelectedText();

			try
			{
				FileOutputStream fos=new FileOutputStream(select);
				
				for(int i=0;i<data.length();i++)
				{
					fos.write(data.charAt(i));
				}
				fos.close();
			}
			catch(Exception ex)
			{
				System.out.println("Excption");
			}
			
			change[presindex]=false;
			String path=select.getPath();
			jif[presindex].setTitle(path);
			jmi[presindex].setText(select.getName());
			undostate[presindex]=false;
			undo.setEnabled(false);
			redostate[presindex]=false;
			redo.setEnabled(false);
			
		}
		
		else if(e.getSource()==print||e.getSource()==b[4])
		{
			int index=getSelectedFrameNumber();
			try
			{
				ta[index].print();
			}
			catch(Exception ex)
			{
				System.out.println("Print Excption");
			}
		}
		
		else if(e.getSource()==selectall)
		{
			ta[getSelectedFrameNumber()].selectAll();
		}
		
		else if(e.getSource()==delete)
		{
			int index=getSelectedFrameNumber();
			int i=ta[index].getSelectionStart();
			int j=ta[index].getSelectionEnd();
			
			ta[index].replaceRange("",i,j);
		}
		
		else if(e.getSource()==undo)
		{
			try
			{
				um[getSelectedFrameNumber()].undo();
				redostate[getSelectedFrameNumber()]=true;
				redo.setEnabled(true);
			}
			catch(Exception ex)
			{
				undostate[getSelectedFrameNumber()]=false;
				undo.setEnabled(false);
			}
		}
		
		else if(e.getSource()==redo)
		{
			try
			{
				um[getSelectedFrameNumber()].redo();
			}
			catch(Exception ex)
			{
				redostate[getSelectedFrameNumber()]=false;
				redo.setEnabled(false);
			}
		}
		
		/*else if(e.getSource()==exit)
		{
			try
			{
			int current=getSelectedFrameNumber();
			if(jif[current]!=null)
			{
				jif[current].setClosed(true);
				jdp.selectFrame(true);
			}
			}
			catch(Exception ex)
			{
				System.out.println("Exception");
			}
		}*/
		
		else if(e.getSource()==bold)
		{
			setText();
		}
		else if(e.getSource()==italic)
		{
			setText();
		}
		else if(e.getSource()==colorchoser)
		{
			JColorChooser jcc=new JColorChooser();
			Color choosen=jcc.showDialog(f,"Choose your Color",Color.RED);
			int index=getSelectedFrameNumber();
			System.out.println(index);
			ta[index].setBackground(choosen);
			ta[index].setVisible(true);
		}
		
		else if(e.getSource()==cut||e.getSource()==b[5])
		{
			int index=getSelectedFrameNumber();
			cuttedstring=ta[index].getSelectedText();
			int i=ta[index].getSelectionStart();
			int j=ta[index].getSelectionEnd();
			
			ta[index].replaceRange("",i,j);
			paste.setEnabled(true);
			b[7].setEnabled(true);
			cutclicked=true;
		}
		
		else if(e.getSource()==copy||e.getSource()==b[6])
		{
			int index=getSelectedFrameNumber();
			cuttedstring=ta[index].getSelectedText();
			
			paste.setEnabled(true);
			b[7].setEnabled(true);
			cutclicked=false;
		}
		
		else if(e.getSource()==paste||e.getSource()==b[7])
		{
			int index=getSelectedFrameNumber();
			int i=ta[index].getSelectionStart();
			ta[index].replaceRange(cuttedstring,i,i);
			
			if(cutclicked==true)
			{
				paste.setEnabled(false);
				b[7].setEnabled(false);
			}
		}
		
		else if(e.getSource()==find||e.getSource()==b[8])
		{
			findDialogBox(true);
		}
		
		else if(e.getSource()==replace||e.getSource()==b[9])
		{
			findDialogBox(false);
		}
		else if(e.getSource()==dfind || e.getSource()==dreplace)
		{
			int index=getSelectedFrameNumber();
			ta[index].selectAll();
			String text=ta[index].getSelectedText();
			
			int start=text.indexOf(tf1.getText(),startindex);
			int length=tf1.getText().length();
			if(start!=-1)
			{
				if(e.getSource()==dreplace)
				{
					ta[index].replaceRange(tf2.getText(),start,start+length);
				}
				
				ta[index].setSelectionStart(start);
				ta[index].setSelectionEnd(start+length);
			}
			else
			{
				  JOptionPane.showMessageDialog(f,"Cannot find the Text");
				  return;
			}
			startindex=start + length;
		}
		else if(e.getSource()==dreplaceall)
		{
			int index=getSelectedFrameNumber();
			ta[index].selectAll();
			int start=0;
			String text=ta[index].getSelectedText();
			int i=0;
			int length=tf1.getText().length();
			int count=0;
			start=text.indexOf(tf1.getText(),i);
			while(start!=-1)
			{
				count++;
				ta[index].replaceRange(tf2.getText(),start,(start+length));
				i=start+length;
				start=text.indexOf(tf1.getText(),i);
			}
			if(count==0)
				JOptionPane.showMessageDialog(f,"Cannot find the Text");
		}
		
		else if(e.getSource()==linewrap)
		{
			if(linewrap.getState())
			{
				for(int i=0;i<=count;i++)
				{
					if(ta[i]!=null)
					{
						ta[i].setLineWrap(true);
					}
				}
			}
			else
			{
				for(int i=0;i<=count;i++)
				{
					if(ta[i]!=null)
					{
						ta[i].setLineWrap(false);
					}
				}
			}
		}
		
		else if(e.getSource()==exit)
		{
			jif[getSelectedFrameNumber()].hide();
			for(int i=0;i<100;i++)
					{
						if(jif[i]!=null)
						{
						if(!jif[i].isClosed())
							System.out.println(i);
							jif[i].show();
							internalFrameClosing(new InternalFrameEvent(jif[i],0));
						}
					}
					
					f.dispose();
					System.exit(1);
		}
		
		else if(e.getSource()==compile)
		{
			setExecuteWindow(true);
			executewindow.setText("COMPILING YOUR CODE\n\n");
			
			try
			{
			Process p;
			InputStream is;
			BufferedReader br;
			String line;
			int i=0;
			File pointer=new File(jif[getSelectedFrameNumber()].getTitle());
			p=Runtime.getRuntime().exec("javac "+pointer.getName());
			is=p.getErrorStream();
			br=new BufferedReader(new InputStreamReader(is));
			
			while((line=br.readLine())!=null)
			{
				executewindow.append(line);
				i++;
			}
			br.close();
			is.close();
			
			if(i!=0)
			{
				executewindow.append("\n\n!!!!!!!!!!!\n\n PLEASE CHECK AND CORRECT THE ERRORS\n\n!!!!");
			}
			else
			{
				executewindow.append("\n\n!!!!!!!!!!!\n\n YOUR CODE HAS BEEN SUCSESSFULLY COMPILED\n\n!!!!");
			}
			
			
			}
			catch(Exception ex)
			{
				System.out.println("Exception in compile");
			}
			f.setVisible(true);
		}
		
		else if(e.getSource()==run)
		{	
	
			executewindow.append("\n\n!!!!!!!!\n\nRUNNING YOUR CODE\n\n!!!!");
			Process p;
			InputStream is;
			BufferedReader br;
			
			try
			{
			File pointer=new File(jif[getSelectedFrameNumber()].getTitle());
			String line;
			String file=pointer.getName();
			int index=file.indexOf('.');
			String sub=file.substring(0,index);
			int i=0;
			
			System.out.println(sub);
			p=Runtime.getRuntime().exec("java "+sub);
			is=p.getErrorStream();
			br=new BufferedReader(new InputStreamReader(is));
			
			while((line=br.readLine())!=null)
			{
				executewindow.append(line);
				i++;
			}
			br.close();
			is.close();
			
			if(i!=0)
			{
				executewindow.append("\n\n!!!!!!!!!!!\n\n RECOMPILATION MAY FIX THIS\n\n!!!!");
			}
			else
			{
				executewindow.append("!!CODE WAS SUCCESSFUL EXCUTED!! ");
			}
			f.setVisible(true);
			}
			catch(Exception ex)
			{
				System.out.println("Exception in run");
			}
		}
		
		else if(e.getSource()==metal)
		{
			try
			{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				SwingUtilities.updateComponentTreeUI(f);
			}
			catch(Exception ex)
			{
				ex.getMessage();
				System.out.println("Exception in look and Feel");
			}
		}
		
		else if(e.getSource()==nimbus)
		{
			try
			{
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				SwingUtilities.updateComponentTreeUI(f);
			}
			catch(Exception ex)
			{
				ex.getMessage();
				System.out.println("Exception in look and Feel");
			}
		}
		
		else if(e.getSource()==tattoo)
		{
			try
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
				SwingUtilities.updateComponentTreeUI(f);
			}
			catch(Exception ex)
			{
				ex.getMessage();
				System.out.println("Exception in look and Feel");
			}
		}
		
		else if(e.getSource()==liquid)
		{
			try
			{
				UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
				SwingUtilities.updateComponentTreeUI(f);
			}
			catch(Exception ex)
			{
				ex.getMessage();
				System.out.println("Exception in look and Feel");
			}
		}
		
		else if(e.getSource()==sea)
		{
			try
			{
				UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
				SwingUtilities.updateComponentTreeUI(f);
			}
			catch(Exception ex)
			{
				ex.getMessage();
				System.out.println("Exception in look and Feel");
			}
		}
	}
	
	int getSelectedFrameNumber()
	{
		JInternalFrame temp=jdp.getSelectedFrame();
		if(temp==null)
		{
			return -1;
		}
		
		int i=0;
		for(i=0;i<100;i++)
		{
			if(temp==jif[i])
				break;
		}
		
		return i;
	}
	
	void setText()
	{
		int i=0;
		if(bold.getState()==true)
		{
			if(italic.getState()==true)
			{
				for(i=0;i<=count;i++)
				{
					if(ta[i]!=null)
					{
						ta[i].setFont(new Font("Arial",Font.ITALIC|Font.BOLD,16));
					}
				}
			}
			else
			{
				for(i=0;i<=count;i++)
				{
					if(ta[i]!=null)
					{
						ta[i].setFont(new Font("Arial",Font.BOLD,16));
					}
				}
			}
		}
		else
		{
			if(italic.getState()==true)
			{
				for(i=0;i<=count;i++)
				{
					if(ta[i]!=null)
					{
						ta[i].setFont(new Font("Arial",Font.ITALIC,16));
					}
				}
			}
			else
			{
				for(i=0;i<=count;i++)
				{
					if(ta[i]!=null)
					{
						ta[i].setFont(new Font("Arial",Font.PLAIN,16));
					}
				}
			}
		}
	}
	
	void findDialogBox(boolean find)
	{
		JLabel l1,l2;
		
		dfind=new JButton("Find");
		dreplace=new JButton("Replace");
		dreplaceall=new JButton("Replace_All");
		
		dfind.addActionListener(this);
		dreplace.addActionListener(this);
		dreplaceall.addActionListener(this);
		
		JRadioButton rb1,rb2;
		rb1=new JRadioButton("Up");
		rb2=new JRadioButton("Down");
		
		l1=new JLabel("Find");
		l2=new JLabel("Replace With");
		
		tf1=new JTextField();
		tf2=new JTextField();
		
		JPanel lab1,lab2,lab3;
		lab1=new JPanel();
		lab2=new JPanel();
		lab3=new JPanel();
		
		if(find)
		{
			d=new Dialog(f,"Find");
			d.setLayout(new BorderLayout());
			lab1.setLayout(new GridLayout(2,2));
			lab1.add(l1);
			lab1.add(tf1);
			//lab1.add(l2);
			//lab1.add(tb2);
			
			lab2.add(rb1);
			lab2.add(rb2);
			
			lab3.add(dfind);
			
			d.add(lab3,"East");
			d.add(lab2,"South");
			d.add(lab1);
		}
		else
		{
			d=new Dialog(f,"Find");
			d.setLayout(new BorderLayout());
			lab1.setLayout(new GridLayout(2,2));
			lab1.add(l1);
			lab1.add(tf1);
			lab1.add(l2);
			lab1.add(tf2);
			
			lab2.add(rb1);
			lab2.add(rb2);
			
			lab3.setLayout(new GridLayout(3,1));
			
			lab3.add(dfind);
			lab3.add(dreplace);
			lab3.add(dreplaceall);
			
			d.add(lab3,"East");
			d.add(lab2,"South");
			d.add(lab1);
		}
		d.setSize(300,125);
		d.setLocation(400,400);
		d.addWindowListener(this);
		d.setVisible(true);
		
	}
	
	public void windowClosing(WindowEvent e)
	{
		if(e.getSource()==d)
		{
			d.dispose();
			startindex=0;
		}
		else if(e.getSource()==f)
		{
			if(getSelectedFrameNumber()==-1)
			{
				System.exit(1);
			}
			else
			{
				actionPerformed(new ActionEvent(exit,0,"Exit"));
			}
		}
		
	}
	
	void setDefault()
	{
		if(getSelectedFrameNumber()==-1)
		{
			count=0;
			save.setEnabled(false);
			saveas.setEnabled(false);
			print.setEnabled(false);
			edit.setEnabled(false);
			format.setEnabled(false);
			//exit.setEnabled(false);
			
			for(int i=2;i<10;i++)
			{
				b[i].setEnabled(false);
			}
		}
	}
	
	 public void internalFrameClosing(InternalFrameEvent e)
	 {
		 System.out.println("I REach AT beg");
		int index=getSelectedFrameNumber();
		if(index==-1)
		{
			System.out.println("Error found!!!!!index is -1");
			return;
		}
		System.out.println(index);
		//System.out.println(index);
		if(e.getSource()==jif[index])
		{
			int i;
			/*String title=jif[index].getTitle();
			File f=new File(title);
			String pointer=f.getName();
			*/
			windows.remove(jmi[index]);
			
			//JInternalFrame temp;
			
			try
			{
				if(change[index]==true)
				{
					int status=JOptionPane.showConfirmDialog(jif[index],"Do you want to save changes","Exit Message",JOptionPane.YES_NO_CANCEL_OPTION);
					if(status==JOptionPane.YES_OPTION)
					{
						actionPerformed(new ActionEvent(save,0,"Save"));
						jif[index].dispose();
						jif[index].setClosed(true);
					}
					else if(status==JOptionPane.NO_OPTION)
					{
						jif[index].dispose();
						//jif[index].setClosed(true);
					}
					else if(status==JOptionPane.CANCEL_OPTION)
					{
						return;
					}
					//jif[index].setClosed(true);
					//System.out.println("I REACH AT MIDDLE!!!!!!!!!!!!!!!");
					
					int count=0;
					for(i=0;i<100;i++)
					{
						if(jif[i]!=null)
						{count++;
						if(!jif[i].isClosed())
							break;
						}
					}
					//System.out.println(i);
					//System.out.println("I REACH AT End!!!!!!!!!!!"+count);
					//System.out.println(i);
					JInternalFrame temp=jdp.selectFrame(true);
					if(i!=100)
					{
					jif[i].show();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(jif[index],"WIndow is Closing");
					
					jif[index].dispose();
					
					//System.out.println("I REACH AT MIDDLE");
					//jif[index].setClosed(true);
					for(i=0;i<100;i++)
					{
						if(jif[i]!=null)
						{
						if(!jif[i].isClosed())
							break;
						}
					}
					//System.out.println(i);
					//JInternalFrame temp=jdp.selectFrame(true);
					if(i!=100)
					{
					jif[i].show();
					}
					System.out.println("I REACH AT End");
				}
				
			}
			catch(Exception ex)
			{
				System.out.println("Excption in closing the frame");
				
			}
			
			setExecuteWindow(false);
			setDefault();
		}
    }

    public void internalFrameClosed(InternalFrameEvent e) {
            }

    public void internalFrameOpened(InternalFrameEvent e) {
        
    }

    public void internalFrameIconified(InternalFrameEvent e) {
        
    }

    public void internalFrameDeiconified(InternalFrameEvent e) {
        
    }

    public void internalFrameActivated(InternalFrameEvent e) {
       
    }

    public void internalFrameDeactivated(InternalFrameEvent e) {
        
    }
	
	void setExcute(File f)
	{
		String file=f.getName();
		boolean flag=file.endsWith(".java");
		
		if(flag)
		{
			execute.setEnabled(true);
			setExecuteWindow(true);
		}
		else
		{
			execute.setEnabled(false);
			setExecuteWindow(false);
		}
	}	
	
	void setExecuteWindow(boolean flag)
	{
		executewindow=new JTextArea();
		sp1=new JScrollPane(executewindow);
		if(flag)
		{
			f.add(sp1,"South");
			f.setVisible(true);
		}
		else
		{
			f.remove(sp1);
			f.setVisible(true);
			f.validate();
		}
	}
}