package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Main
{
	public static void main(String[] args)
	{
		JFrame frame;
		JTextArea area;
		JButton button;
		
		frame = new JFrame();
		frame.setSize(590, 68);
		frame.setTitle("Name¡DTool¡DRandom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		area = new JTextArea();
		area.setBounds(0, 0, 515, 68);
		area.setLineWrap(true);     
		frame.getContentPane().add(area);
		
		button = new JButton("Enter");
		button.setBounds(515, 0, 70, 40);
		button.addMouseListener(new MouseAdapter()
		{
	        public void mouseClicked(MouseEvent e)
	        {
	        	go(area.getText());
	        }
		});
		frame.getContentPane().add(button);
		frame.setVisible(true);
	}
	
	public static void go(String in)
	{
		int box[], select, temp;
		String destination, subName;
		String[] nameList;
		Random random;
		File file;

		destination = in;
		destination.replace('\\', '/');
		nameList = new File(destination).list();
		box = new int[nameList.length];
		random = new Random();
		
		for(int i=0; i<nameList.length; i++)
		{
			file = new File(destination + "/" + nameList[i]);
			file.renameTo(new File(destination + "/NTR" + nameList[i]));
			box[i] = i;
		}
		nameList = new File(destination).list();
		for(int i=0; i<nameList.length; i++)
		{
			temp = random.nextInt(box.length-i);
			select = box[temp];
			box[temp] = box[box.length-1-i];
			
			file = new File(destination + "/" + nameList[i]);
			if(nameList[i].lastIndexOf(".")>0) subName = nameList[i].substring(nameList[i].lastIndexOf("."));
			else subName = "";
			file.renameTo(new File(destination + "/" + select + subName));
		}
		System.exit(0);
	}
}
