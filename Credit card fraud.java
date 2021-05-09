import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Creditcardfraud extends Applet implements ActionListener {
TextField brows,dname,dpath,key;
TextArea db,result,con;
Button browse,find,exit,clear;
int done;
Label browses,concl;
String us;
String strline = null;
String[] temp;
String[][] data = new String[50][50];
public void init(){
setBackground(Color.cyan);
setForeground(Color.magenta);
Label head=new Label(" CREDIT 
CARD FRAUD DETECTION SYSTEM 
",Label.CENTER);
Font font = new Font("Serif", Font.ITALIC, 30);
head.setFont(font);
Label dataset=new Label(" DATASET SELECTED 
",Label.CENTER);
Label res=new Label(" FRAUD DETECTED 
",Label.CENTER);
browses = new Label(" Browse DataSet: ", Label.LEFT);
concl = new Label(" FRAUD TRANSACTIONS 
", Label.LEFT);
brows = new TextField(50);
db = new TextArea(20,70);
result = new TextArea(20,70);
con = new TextArea(10,100);
browse = new Button(" Browse ");
find = new Button(" Find ");
exit = new Button(" Exit ");
clear = new Button(" Clear ");
brows.disable();
resize(1200,700);
Label l1 =new Label(" 
 
");
Label l2 =new Label(" 
 
 ");
Label l3 =new Label(" ");
Label l4 =new Label(" 
");
add(head);
setForeground(Color.BLUE);
add(l2);
add(browses);
add(brows);
setForeground(Color.BLACK);
add(browse);
add(find);
add(clear);
add(exit);
// add(l1);
add(l4);
add(dataset);
add(l3);
add(res);
add(l3);
add(db);
add(result);
add(concl);
add(l3);
add(con);
// register to receive action events
browse.addActionListener(this);
find.addActionListener(this);
exit.addActionListener(this);
}
public void actionPerformed(ActionEvent ae) {
String str = ae.getActionCommand();
if(str.equals(" Browse ")) {
try
{
FileDialog fd = new FileDialog(new Frame(), "Please choose a file:", FileDialog.LOAD);
fd.show();
if (fd.getFile() != null) {
File f = new File(fd.getDirectory(), fd.getFile());
String path=f.getPath();
brows.setText(path);
FileInputStream fstream =new FileInputStream(path);
DataInputStream in =new DataInputStream(fstream);
BufferedReader br=new BufferedReader(new InputStreamReader(in));
int k=0;
for(int i=0;i<=20;i++)
{
strline=br.readLine();
temp =strline.split(",");
// System.out.println(temp[0] +" "+temp[1] +" "+temp[2] +" "+temp[3] +" "+temp[4]);
// System.out.println(" 1");
for(int j=0;j<=11;j++)
{
data[i]=temp;
}
}
for(int i=0;i<=20;i++)
{
for(int j=0;j<=11;j++)
{
db.append(data[i][j]);System.out.print(data[i][j]);
db.append("\t");System.out.print("\t");
}
if(i==0)
{
db.append("\n");
db.append("__\t__\t___\t\t__\t\t_\t_\t_\t_\t__\t_");
db.append("\n");
}
db.append("\n"); System.out.println(" ");
}
}
}
catch(Exception e)
{
System.out.println(e.toString());
}
}
else if(str.equals(" Exit ")) {
System.exit(0);
System.out.println("\n cancel ");
// repaint();
}
else if(str.equals(" Clear ")) {
db.setText(" ");
result.setText(" ");
brows.setText(" ");
System.out.println("\n Clear ");
// repaint();
}
else if(str.equals(" Find ")) {
float[] res = null;
float[][] fre = new float[6][20];
float[][] loc = new float[6][20];
float[][] od = new float[6][20];
float[][] bb = new float[6][20];
float[][] ds = new float[6][20];
float[][] initPop = new float[21][5];
float[][] curPop = new float[21][5];
float[][] nexPop = new float[21][5];
float[][] finalPop = new float[21][5];
float[] resValue =new float[21];
Detection dt=new Detection();
Evaluate ev= new Evaluate();
NextGen ng= new NextGen();
/* CC usage Fequency */
int l=0,m=0;
result.append("Based on CC usage Fequency \n");
result.append("------------------------------ \n");
for(int i=1;i<=20;i++)
{
res= dt.ccfreq(data[i]);
if(res[0]>=1)
{
fre[l][m]=Float.valueOf(data[i][0]);m++;
fre[l][m]=res[1];
result.append("In CC ID: "+data[i][0]+" - Usage Freq. Fraud is found with value - "+res[1]);
result.append("\n");
l++;m=0;
}
initPop[i][0]=res[1];
}
/* CC usage Location */
l=0;m=0;
result.append("\n");
result.append("Based on CC usage Location \n");
result.append("------------------------------ \n");
for(int i=1;i<=20;i++)
{
res= dt.ccloc(data[i]);
if(res[0]>=1)
{
loc[l][m]=Float.valueOf(data[i][0]);m++;
loc[l][m]=res[1];
result.append("In CC ID: "+data[i][0]+" - Usage Location Fraud is found with value -
"+res[1]);
result.append("\n");
l++;m=0;
}
initPop[i][1]=res[1];
}
/* CC OverDraft */
l=0;m=0;
result.append("\n");
result.append("Based on CC OverDraft \n");
result.append("------------------------------ \n");
for(int i=1;i<=20;i++)
{
res= dt.ccod(data[i]);
if(res[0]>=1)
{
od[l][m]=Float.valueOf(data[i][0]);m++;
od[l][m]=res[1];
result.append("In CC ID: "+data[i][0]+" - CC OverDraft Fraud is found with value -
"+res[1]);
result.append("\n");
l++;m=0;
}
initPop[i][2]=res[1];
}
/* Current Book Balance */
l=0;m=0;
result.append("\n");
result.append("Based on CC Book Balance \n");
result.append("------------------------------ \n");
for(int i=1;i<=20;i++)
{
res= dt.ccbb(data[i]);
if(res[0]>=1)
{
bb[l][m]=Float.valueOf(data[i][0]);m++;
bb[l][m]=res[1];
result.append("In CC ID: "+data[i][0]+" - CC Book Balance Fraud is found with value -
"+res[1]);
result.append("\n");
l++;m=0;
}
initPop[i][3]=res[1];
}
/* Average Daily Spending */
l=0;m=0;
result.append("\n");
result.append("Based on CC Average Daily Spending \n");
result.append("------------------------------------ \n");
for(int i=1;i<=20;i++)
{
res= dt.ccds(data[i]);
if(res[0]>=1)
{
ds[l][m]=Float.valueOf(data[i][0]);m++;
ds[l][m]=res[1];
result.append("In CC ID: "+data[i][0]+" - CC Daily Spending Fraud is found with value -
"+res[1]);
result.append("\n");
l++;m=0;
}
initPop[i][4]=res[1];
}
// float[][] finalresult = dt.organize(fre,loc,od,bb,ds);
for(int i=1;i<=20;i++)
{
for(int j=0;j<=4;j++)
{
System.out.print(initPop[i][j]);
System.out.print("\t ");
}
System.out.println(""); }
System.out.println("*** end of INIT Population ");
curPop=initPop;
for(int q=1;q<=20;q++)
{
nexPop=ng.getNextGen(curPop);
System.out.println(" \n");
System.out.println(" Current Popoulation - Generation - "+q);
System.out.println("_______________ \n");
for(int i=1;i<=20;i++)
{
for(int j=0;j<=4;j++)
{
System.out.print(nexPop[i][j]);
System.out.print("\t ");
}
System.out.println(" ");
}
curPop=nexPop;
System.out.println(" \n\n Critical Values Found after Limited number of Generations (sorted
order)");
resValue = dt.resValue(curPop);
Arrays.sort(resValue);
for(int i=1;i<=20;i++)
{
System.out.println(resValue[i]);
}
}
float criti=resValue[15];
float monit=resValue[10];
float ordin=resValue[5];
[3:03 PM, 5/8/2021] ganesh p cvr clg: System.out.println("\n\n Critical Values of each transaction of given DataSet");
System.out.println(" ----------------------------------------------------------- ");
float[][] finalresult = dt.organize(fre,loc,od,bb,ds);
System.out.println("\n\n Value of Critic, Monitor and Ordinary Faruds");
System.out.println("\n\n "+criti+" "+monit+" "+ordin);
System.out.println(" \n\n Fraud Detected used Genetic Algorithm: ");
System.out.println("--------------------------------------------- ");
con.append(" Critical Fraud Detected: ");con.append("\n");
con.append("------------------------------------- ");
System.out.println("Critical Fraud Detected: ");
System.out.println("------------------------------------- ");
for(int i=0;i<=19;i++)
{
if((finalresult[i][2])>= criti)
{
con.append("\n");
con.append(" Credit Card with ID "+finalresult[i][0]+" is detected as fraud with
"+finalresult[i][1]+" occurance and its crical value is "+ finalresult[i][2]);
System.out.println(" Credit Card with ID "+finalresult[i][0]+" is detected as fraud with
"+finalresult[i][1]+" occurance and its crical value is "+ finalresult[i][2]);
[3:04 PM, 5/8/2021] ganesh p cvr clg: System.out.println(" ");
con.append("\n");
}
}
con.append(" \n Monitorable Fraud Detected: ");con.append("\n");
con.append("----------------------------------------- ");
System.out.println("Monitorable Fraud Detected: ");
System.out.println("-------------------------------- ");
for(int i=0;i<=19;i++)
{
if(((finalresult[i][2])>= monit) && ((finalresult[i][2])< criti))
{
con.append("\n");
con.append(" Credit Card with ID "+finalresult[i][0]+" is detected as fraud with
"+finalresult[i][1]+" occurance and its crical value is "+ finalresult[i][2]);
System.out.println("Credit Card with ID "+finalresult[i][0]+" is detected as fraud with
"+finalresult[i][1]+" occurance and its crical value is "+ finalresult[i][2]);
System.out.println(" ");
con.append("\n");
}
}
con.append(" \n Ordinary Fraud Detected: ");con.append("\n");
con.append("--------------------------------------- ");
System.out.println("Ordinary Fraud Detected: ");
System.out.println("--------------------------------------- ");
for(int i=0;i<=19;i++)
{
if(((finalresult[i][2])>= ordin) && ((finalresult[i][2])< monit))
{
con.append("\n");
con.append(" Credit Card with ID "+finalresult[i][0]+" is detected as fraud with
"+finalresult[i][1]+" occurance and its crical value is "+ finalresult[i][2]);
System.out.println("Credit Card with ID "+finalresult[i][0]+" is detected as fraud with
"+finalresult[i][1]+" occurance and its crical value is "+ finalresult[i][2]);
con.append("\n");
}
}
repaint();
}
}
public void paint(Graphics g) {
System.out.println(done);
if(done==1)
{
setForeground(Color.BLUE);
g.drawString("SUCCESS", 10, 190);
String msg="The File is Encrypted Successfully";
g.drawString(msg, 20, 205);
}
if(done==2)
{
setForeground(Color.RED);
g.drawString("ERROR", 10, 190);
String msg="The File is not Encrypted Successfully \n";
g.drawString(msg, 20, 205);
/* if(!errmsg1.equals(null))
{
g.drawString(errmsg1, 20, 220);
}
if(!errmsg2.equals(null))
{
g.drawString(errmsg2,20, 235);
}*/
System.out.println("\n paint ");
}
}
}