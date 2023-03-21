
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ImageMusic extends Applet implements ActionListener {
    
    // Panel one
    ImageIcon[] icon = new ImageIcon[26];
    AudioClip[] clip = new AudioClip[26];
    JButton[] b = new JButton[26];
    
    // Panel second
    JButton[] button = new JButton[12];
    AudioClip[] animalClip = new AudioClip[12];
    ImageIcon[] animalIcon = new ImageIcon[12];
    
    // Panel third
    JButton[] numButton = new JButton[26];
    ImageIcon[] numIcon = new ImageIcon[26];
    AudioClip[] numbers = new AudioClip[21];
    
    AudioClip intro, all, closeMusic;
    String[] names = {"cat", "cow", "dog", "fox", "gorilla", "horse", "lion", "rattlesnake", "rooster", "schimpanse", "tiger", "wolf"};
   
    Panel centerPanel = new Panel(new GridLayout(6,5));
    Panel centerPanelSecond = new Panel(new GridLayout(4, 3));
    Panel centerPanelThird = new Panel(new GridLayout(5, 4));
    Panel west = new Panel();
    JPanel east = new JPanel();
    JPanel homePage = new JPanel(new FlowLayout());
    JLabel homePanel = new JLabel();
    
    JButton next, prev, home, close, play;
    ImageIcon homeIcon = new ImageIcon(Class.class.getResource("/buttons/home.png"));
    ImageIcon closeIcon = new ImageIcon(Class.class.getResource("/buttons/close.jpg"));
    ImageIcon nextIcon = new ImageIcon(Class.class.getResource("/buttons/next.png"));
    ImageIcon prevIcon = new ImageIcon(Class.class.getResource("/buttons/previous.png"));
    ImageIcon background = new ImageIcon(Class.class.getResource("/reference/best.jpg"));
    ImageIcon playIcon = new ImageIcon(Class.class.getResource("/buttons/play.png"));
    
    
    @Override
    public void init() {
        char ch = 97;
       for(int i = 0; ch <= 122; ch++, i++) {
             icon[i] = new ImageIcon(Class.class.getResource("/letters/" + ch + ".png"));
           }
       
       for(int i = 0; i < 12; i++) {
           animalIcon[i] = new ImageIcon(Class.class.getResource("/animals/" + names[i] + ".jpg"));
       }
       
           char cc = 97;
       for(int i = 0; cc <= 122; cc++, i++) {
           numIcon[i] = new ImageIcon(Class.class.getResource("/numbers/"+ cc +".png"));
       }
       
       setSize(1250, 900);
       this.setLayout(new BorderLayout());
       
       }
    
    
      @Override
    public void start() {
    
        char c = 65;
        for(int i = 0; i < 26; c++, i++) {
            b[i] = new JButton(icon[i]);
            b[i].setToolTipText("Click " + c + " to hear the sound" );
            b[i].addActionListener(this);
            centerPanel.add(b[i]);
        }
        
        for(int i = 0; i < names.length; i++) {
            button[i] = new JButton();
            button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = ((JButton)e.getSource());
                    for(int i = 0; i < 12; i++) {
        
                    if(button.getIcon() == animalIcon[i]) {
                         animalClip[i].play();
        }
        
       }
                }
            });
            button[i].setBounds(100,100,350,320);
            // Set image to size of JButton...
            int offset = button[i].getInsets().left;
            button[i].setIcon(resizeIcon(animalIcon[i], button[i].getWidth() - offset, button[i].getHeight() - offset));
            centerPanelSecond.add(button[i]);
        }
        next = new JButton(nextIcon);
        next.setToolTipText("Click here to see next");
        prev = new JButton(prevIcon);
        home = new JButton(homeIcon);
        home.setToolTipText("HOME");
        close = new JButton(closeIcon);
        close.setToolTipText("EXIT");
        play = new JButton(playIcon);
        
         
        east.add(next);
        west.add(home);
        west.add(prev);
        west.add(close);
        centerPanel.setPreferredSize(new Dimension(getWidth() - 80, getHeight()));
        homePage.setPreferredSize(new Dimension(getWidth() - 80, getHeight()));
        centerPanelSecond.setPreferredSize(new Dimension(getWidth() - 80, getHeight()));
        centerPanelThird.setPreferredSize(new Dimension(getWidth() - 80, getHeight()));
        west.setPreferredSize(new Dimension(120, getHeight()));
        west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
        west.setBackground(Color.cyan);
        east.setPreferredSize(new Dimension(120, getHeight()));
        east.setBackground(Color.cyan);
        
        homePanel.setIcon(background);
        homePage.add(homePanel);
        homePage.add(play);
        JLabel text = new JLabel("Welcome!\n" + "\n Click to Play");
        Font font = new Font("Serif", Font.BOLD, 52);
        text.setFont(font);
        text.setForeground(new Color(200, 150, 200));
   
        homePage.add(text);
        homePage.setBackground(Color.orange);
        intro = getAudioClip(Class.class.getResource("/reference/Intro.wav"));
        all =   getAudioClip(Class.class.getResource("/reference/all.wav"));
        closeMusic = getAudioClip(Class.class.getResource("/reference/close.wav"));
        intro.play();
        
        east.setLayout(new FlowLayout(FlowLayout.CENTER));
        next.setAlignmentY(FlowLayout.CENTER);
        
        
        
        this.add(centerPanelSecond, BorderLayout.CENTER);       
        this.add(west, BorderLayout.WEST);
        
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(centerPanelThird, BorderLayout.CENTER);
        this.add(east, BorderLayout.EAST);
        
        this.add(homePage, BorderLayout.CENTER);
        
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            intro.stop();
            all.play();
            for (Component component : getComponents())
            if (centerPanel == component) {
                remove(homePage);
                remove(centerPanel);
                remove(centerPanelThird);
                add(centerPanelSecond);

            } else if(centerPanelThird == component) {
                remove(homePage);
                remove(centerPanelSecond);
                remove(centerPanelThird);
                add(centerPanel);
            } else if(homePage == component) {
                remove(homePage);
                remove(centerPanelSecond);
                remove(centerPanelThird);
                add(centerPanel);
                
            } else {
                remove(homePage);
                remove(centerPanelSecond);
                remove(centerPanel);
                add(centerPanelThird);
            }

        repaint();
        revalidate();
        
        }
        });
        
        prev.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent event) {
            intro.stop(); 
            all.play();
            for (Component component : getComponents())
            if (centerPanel == component) {
                remove(centerPanel);
                remove(homePage);
                remove(centerPanelSecond);
                add(centerPanelThird);

            } else if(centerPanelThird == component) {
                remove(homePage);
                remove(centerPanel);
                remove(centerPanelThird);
                add(centerPanelSecond);
            } else {
                remove(homePage);
                remove(centerPanelThird);
                remove(centerPanelSecond);
                add(centerPanel);
            }

        repaint();
        revalidate();
        
        }
        });
        
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            intro.stop();
            all.play();
            for (Component component : getComponents())
            if (centerPanel == component) {
                remove(homePage);
                remove(centerPanel);
                add(centerPanelSecond);

            } else {
                remove(homePage);
                remove(centerPanelSecond);
                add(centerPanel);
            }

        repaint();
        revalidate();
        
        }
        });
        
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
              all.play();
              intro.play();
              for (Component component : getComponents())
                if (centerPanel == component) {
                remove(centerPanel);
                remove(centerPanelSecond);
                remove(centerPanelThird);
                add(homePage);

            } else {
                remove(centerPanelSecond);
                remove(centerPanelThird);
                add(homePage);
            }  
            }
        });
        
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                closeMusic.play();
                System.exit(0);
            }
        });
        
        
        char ch = 65;
        for(int i = 0; i < 26; ch++, i++) {
            try {
	    clip[i] = getAudioClip(Class.class.getResource("/letterSound/" + ch +".wav"));
            
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        for(int i = 0; i < 12; i++) {
            try {
	    animalClip[i] = getAudioClip(Class.class.getResource("/animalSound/" + names[i] +".wav"));
            
            } catch(Exception e) {
                e.printStackTrace();
            }
        }  
        char cch = 65;
       for(int i = 0; i < 21; cch++, i++) {
           try {
               numbers[i] = getAudioClip(Class.class.getResource("/letterSound/" + cch +".wav"));
           } catch(Exception e) {
               e.printStackTrace();
           }
       }
       
        for(int i = 0; i < 26; i++) {
            numButton[i] = new JButton(numIcon[i]);
            numButton[i].addActionListener(this);
            centerPanelThird.add(numButton[i]);
        }
       
    }
    
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
    Image img = icon.getImage();  
    Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
    return new ImageIcon(resizedImage);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = ((JButton)e.getSource());
       
        for(int i = 0; i < 12; i++) {
        
        if(button.getIcon() == this.animalIcon[i]) {
            animalClip[i].play();
        }
        
       } 
        
      for(int i = 0; i < 21; i++) {
        
        if(button.getIcon() == this.numbers[i]) {
            numbers[i].play();
        }
        
       } 
       
        for(int i = 0; i < 26; i++) {
        
        if(button.getIcon() == this.icon[i]) {
            clip[i].play();
        }      
       }      
    } 
 }
    
  
    
