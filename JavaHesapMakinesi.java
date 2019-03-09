import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.NumberFormat;
import javax.swing.border.*;


public class JavaHesapMakinesi extends JFrame {
    
    JButton buton1;
    JLabel label1,label2,label3;
    JTextField textField1,textField2;
    JCheckBox parabirimi,virgul;
    JRadioButton topla,cikar,carp,bol;
    JSlider kackere; double sayi1, sayi2, sonuc;
            
 
    public static void main(String[] args) {
        new JavaHesapMakinesi();
    }
    public JavaHesapMakinesi(){
        
        this.setSize(350,400); //programın pencere boyutu
        this.setLocationRelativeTo(null);//pencerenin ortadan açılması
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//pencereyi kapatınca programın derleyiciden de kapanması
        this.setTitle("JavaHesapMakinesi");//pencere başlığı
        
        JPanel panelim=new JPanel();//panel nesnesi oluşturuldu
        buton1=new JButton("Hesapla");//üzerinde hesapla yazan bir buton oluşturuldu
        
        ListenForButton ButonL=new ListenForButton();//butonu takip edecek metot oluşturuldu
        
        buton1.addActionListener(ButonL);
        panelim.add(buton1);//pencerenin içine buton1 eklendi
        
        label1=new JLabel("Sayı1");//üzerinde sayı1 yazan label nesnesi oluşturuldu
        panelim.add(label1);//pencerenin içine label1 eklendi
        
        textField1=new JTextField("", 5);//textfield1 nesnesi oluşturuldu boyutu 5px içerisinde yazı bulunmuyor
        panelim.add(textField1);//pencerenin içine textfield1 eklendi
        
        label2=new JLabel("Sayı2");//üzerinde sayı2 yazan label nesnesi oluşturuldu
        panelim.add(label2);//pencerenin içine label2 eklendi
        
        textField2=new JTextField("", 5);//textfield2 nesnesi oluşturuldu boyutu 5px içerisinde yazı bulunmuyor
        panelim.add(textField2);//pencerenin içine textfield2 eklendi
        
        parabirimi=new JCheckBox("Para Birimi");
        virgul=new JCheckBox("Virgül");
       
        panelim.add(parabirimi);
        panelim.add(virgul);
        
        topla=new JRadioButton("Toplama");
        cikar=new JRadioButton("Çıkarma");
        carp=new JRadioButton("Çarpma");
        bol=new JRadioButton("Bölme");
        
        ButtonGroup islem=new ButtonGroup();//butonların dağınık durmaması için islem adında grup oluşturuldu
        islem.add(topla);
        islem.add(cikar);
        islem.add(carp);
        islem.add(bol);//radio butonları islem buton grubuna eklendi
        
        JPanel islemPanel=new JPanel();
        Border islemBorder=BorderFactory.createTitledBorder("İşlem");
        islemPanel.setBorder(islemBorder);
        
        islemPanel.add(topla);
        islemPanel.add(cikar);    
        islemPanel.add(carp);
        islemPanel.add(bol);
        
        topla.setSelected(true);
        
        panelim.add(islemPanel);
        
        label3=new JLabel("Kaç Defa");
        panelim.add(label3);
        
        kackere=new JSlider(0,99,1);
        kackere.setMinorTickSpacing(1);
        kackere.setMajorTickSpacing(10);
        kackere.setPaintTicks(true);
        kackere.setPaintLabels(true);
        
        ListenForSlider sliderL=new ListenForSlider();
        kackere.addChangeListener(sliderL);
        panelim.add(kackere);
        this.add(panelim);
        this.setVisible(true);
        textField1.requestFocus();
        
        
        
    }
    private class ListenForButton implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==buton1){
                try{
                    sayi1=Double.parseDouble(textField1.getText());
                    sayi2=Double.parseDouble(textField2.getText());
                }
                catch(NumberFormatException excep) { JOptionPane.showMessageDialog(JavaHesapMakinesi.this, "Yanlış bir veri girdiniz!", "Hata", JOptionPane.ERROR_MESSAGE );
                    System.exit(0);
                }
                if(topla.isSelected()){
                    sonuc=sayiTopla(sayi1,sayi2,kackere.getValue());
                }
                else if(cikar.isSelected()){
                    sonuc=sayiCikar(sayi1,sayi2,kackere.getValue());
                }
                else if(carp.isSelected()){
                    sonuc=sayiCarp(sayi1,sayi2,kackere.getValue());
                }
                else{
                    sonuc=sayiBol(sayi1,sayi2,kackere.getValue());
                            
                }
                if(parabirimi.isSelected()){
                    NumberFormat sayiFormat=NumberFormat.getCurrencyInstance();
                    JOptionPane.showMessageDialog(JavaHesapMakinesi.this, sayiFormat.format(sonuc),"Sonuç", JOptionPane.INFORMATION_MESSAGE );
                            
                }
                else if(virgul.isSelected()){
                    NumberFormat sayiFormat=NumberFormat.getNumberInstance();JOptionPane.showMessageDialog(JavaHesapMakinesi.this, sayiFormat.format(sonuc),"Sonuç", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    JOptionPane.showMessageDialog(JavaHesapMakinesi.this, sonuc,"Sonuç", JOptionPane.INFORMATION_MESSAGE );
                }
        }}
    }
        private class ListenForSlider implements ChangeListener{
            @Override
            public void stateChanged(ChangeEvent e){
                if(e.getSource()==kackere){
                    label3.setText("Kac Defa"+kackere.getValue());                   
                    
                }
            }
        }
        public static double sayiTopla(double sayi1,double sayi2, int kackere){
            double toplam=0;
            int i=1;
            while(i<=kackere){
                toplam=toplam+(sayi1+sayi2);
                i++;
            }
            return toplam;
        }
        public static double sayiCikar(double sayi1,double sayi2,int kackere){
            double toplam=0;
            int i=1;
            while(i<=kackere){
                toplam=toplam+(sayi1-sayi2);
                i++;
            }
            return toplam;
        }
        public static double sayiCarp(double sayi1,double sayi2,int kackere){
            double toplam=0;
            int i=1;
            while(i<=kackere){
                toplam=toplam+(sayi1*sayi2);
                i++;
            }
            return toplam;
        }
        public static double sayiBol(double sayi1,double sayi2,int kackere){
            double toplam=0;
            int i=1;
            while(i<=kackere){
                toplam=toplam+(sayi1/sayi2);
                i++;
            }
            return toplam;
            
        }
}
