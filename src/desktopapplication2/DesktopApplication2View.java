/*
 * DesktopApplication2View.java
 */
package desktopapplication2;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import org.hsqldb.jdbcDriver;
import com.mysql.jdbc.Driver;

/**
 * The application's main frame.
 */
public class DesktopApplication2View extends FrameView {
   

    public DesktopApplication2View(SingleFrameApplication app) {
        super(app);

        initComponents();
        initMyComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    private void initMyComponents() {
        //set db connection message
        conhsqljLabel.setText(dbconNOTmsg);
        conmsqljLabel.setText(dbconNOTmsg);
        //set data transmission status
        QueryNumjLabel.setText("0");
        totalreccountjLabel.setText("0");
        transmitjProgressBar.setValue(0);
        transmitjProgressBar.setStringPainted(true);
        //set input fields
        staff_fiojTextField.setText("");
        mysqluserjTextField.setText("");
        mysqldbjPasswordField.setText("");
        //disable controls
        msqlconnjButton.setEnabled(false);
        datasendjButton.setEnabled(false);
        disconnectjButton.setEnabled(false);
        staff_fiojTextField.setEnabled(false);
        mysqluserjTextField.setEnabled(false);
        mysqldbjPasswordField.setEnabled(false);
        //enable controls
        hsqlconnjButton.setEnabled(true);

    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = DesktopApplication2.getApplication().getMainFrame();
            aboutBox = new DesktopApplication2AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        DesktopApplication2.getApplication().show(aboutBox);
    }


    
    @Action
    public void myAppQuit() throws SQLException {
        shutdownalldb();
        DesktopApplication2.getApplication().exit(); 
        
     //   guit(global);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        hsqlconnjButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        conhsqljLabel = new javax.swing.JLabel();
        msqlconnjPanel = new javax.swing.JPanel();
        msqlconnjButton = new javax.swing.JButton();
        mysqldbjPasswordField = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        mysqluserjTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        staff_fiojTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        conmsqljLabel = new javax.swing.JLabel();
        disconnectjButton = new javax.swing.JButton();
        datacontroljPanel = new javax.swing.JPanel();
        datasendjButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        totalreccountjLabel = new javax.swing.JLabel();
        transmitjProgressBar = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        QueryNumjLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setMaximumSize(new java.awt.Dimension(800, 600));
        mainPanel.setName("mainPanel"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setName("LocalDBjPanel"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(desktopapplication2.DesktopApplication2.class).getContext().getActionMap(DesktopApplication2View.class, this);
        hsqlconnjButton.setAction(actionMap.get("hsqldbconnect")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(desktopapplication2.DesktopApplication2.class).getContext().getResourceMap(DesktopApplication2View.class);
        hsqlconnjButton.setFont(resourceMap.getFont("hsqldbconjBtn.font")); // NOI18N
        hsqlconnjButton.setText(resourceMap.getString("hsqldbconjBtn.text")); // NOI18N
        hsqlconnjButton.setName("hsqldbconjBtn"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        conhsqljLabel.setBackground(resourceMap.getColor("conhsqljLabel.background")); // NOI18N
        conhsqljLabel.setFont(resourceMap.getFont("conhsqljLabel.font")); // NOI18N
        conhsqljLabel.setForeground(resourceMap.getColor("conhsqljLabel.foreground")); // NOI18N
        conhsqljLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conhsqljLabel.setText(resourceMap.getString("conhsqljLabel.text")); // NOI18N
        conhsqljLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        conhsqljLabel.setName("conhsqljLabel"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hsqlconnjButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(conhsqljLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hsqlconnjButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(conhsqljLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        msqlconnjPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        msqlconnjPanel.setName("RemoteDBjPanel"); // NOI18N

        msqlconnjButton.setAction(actionMap.get("mysqlconnect")); // NOI18N
        msqlconnjButton.setFont(resourceMap.getFont("msqlconnjButton.font")); // NOI18N
        msqlconnjButton.setText(resourceMap.getString("msqlconnjButton.text")); // NOI18N
        msqlconnjButton.setName("msqlconnjButton"); // NOI18N

        mysqldbjPasswordField.setFont(resourceMap.getFont("mysqldbjPasswordField.font")); // NOI18N
        mysqldbjPasswordField.setText(resourceMap.getString("mysqldbjPasswordField.text")); // NOI18N
        mysqldbjPasswordField.setName("mysqldbjPasswordField"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        mysqluserjTextField.setFont(resourceMap.getFont("mysqluserjTextField.font")); // NOI18N
        mysqluserjTextField.setText(resourceMap.getString("mysqluserjTextField.text")); // NOI18N
        mysqluserjTextField.setName("mysqluserjTextField"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        staff_fiojTextField.setFont(resourceMap.getFont("staff_fiojTextField.font")); // NOI18N
        staff_fiojTextField.setText(resourceMap.getString("staff_fiojTextField.text")); // NOI18N
        staff_fiojTextField.setName("staff_fiojTextField"); // NOI18N
        staff_fiojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staff_fiojTextFieldKeyTyped(evt);
            }
        });

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        conmsqljLabel.setFont(resourceMap.getFont("conmsqljLabel.font")); // NOI18N
        conmsqljLabel.setForeground(resourceMap.getColor("conmsqljLabel.foreground")); // NOI18N
        conmsqljLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conmsqljLabel.setText(resourceMap.getString("conmsqljLabel.text")); // NOI18N
        conmsqljLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        conmsqljLabel.setName("conmsqljLabel"); // NOI18N

        javax.swing.GroupLayout msqlconnjPanelLayout = new javax.swing.GroupLayout(msqlconnjPanel);
        msqlconnjPanel.setLayout(msqlconnjPanelLayout);
        msqlconnjPanelLayout.setHorizontalGroup(
            msqlconnjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(msqlconnjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(msqlconnjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msqlconnjButton, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, msqlconnjPanelLayout.createSequentialGroup()
                        .addGroup(msqlconnjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(msqlconnjPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(msqlconnjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(msqlconnjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mysqluserjTextField)
                            .addComponent(staff_fiojTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                            .addComponent(mysqldbjPasswordField)))
                    .addGroup(msqlconnjPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(conmsqljLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)))
                .addContainerGap())
        );
        msqlconnjPanelLayout.setVerticalGroup(
            msqlconnjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(msqlconnjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(msqlconnjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staff_fiojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(msqlconnjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mysqluserjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(msqlconnjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mysqldbjPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(msqlconnjButton)
                .addGap(5, 5, 5)
                .addGroup(msqlconnjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(conmsqljLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        disconnectjButton.setAction(actionMap.get("shutdownalldb")); // NOI18N
        disconnectjButton.setFont(resourceMap.getFont("disconnectjButton.font")); // NOI18N
        disconnectjButton.setText(resourceMap.getString("disconnectjButton.text")); // NOI18N
        disconnectjButton.setName("disconnectjButton"); // NOI18N

        datacontroljPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        datacontroljPanel.setName("datacontroljPanel"); // NOI18N

        datasendjButton.setAction(actionMap.get("sendstatisticdata")); // NOI18N
        datasendjButton.setFont(resourceMap.getFont("datasendjButton.font")); // NOI18N
        datasendjButton.setName("datasendjButton"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        totalreccountjLabel.setFont(resourceMap.getFont("totalreccountjLabel.font")); // NOI18N
        totalreccountjLabel.setForeground(resourceMap.getColor("totalreccountjLabel.foreground")); // NOI18N
        totalreccountjLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalreccountjLabel.setText(resourceMap.getString("totalreccountjLabel.text")); // NOI18N
        totalreccountjLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        totalreccountjLabel.setName("totalreccountjLabel"); // NOI18N

        transmitjProgressBar.setName("transmitjProgressBar"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        QueryNumjLabel.setFont(resourceMap.getFont("QueryNumjLabel.font")); // NOI18N
        QueryNumjLabel.setForeground(resourceMap.getColor("QueryNumjLabel.foreground")); // NOI18N
        QueryNumjLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        QueryNumjLabel.setText(resourceMap.getString("QueryNumjLabel.text")); // NOI18N
        QueryNumjLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        QueryNumjLabel.setName("QueryNumjLabel"); // NOI18N

        javax.swing.GroupLayout datacontroljPanelLayout = new javax.swing.GroupLayout(datacontroljPanel);
        datacontroljPanel.setLayout(datacontroljPanelLayout);
        datacontroljPanelLayout.setHorizontalGroup(
            datacontroljPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datacontroljPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datacontroljPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datasendjButton, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addComponent(transmitjProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datacontroljPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(QueryNumjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalreccountjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        datacontroljPanelLayout.setVerticalGroup(
            datacontroljPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datacontroljPanelLayout.createSequentialGroup()
                .addGroup(datacontroljPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datacontroljPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(datasendjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(datacontroljPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(QueryNumjLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(transmitjProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(datacontroljPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(datacontroljPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalreccountjLabel)
                            .addComponent(jLabel5))))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msqlconnjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datacontroljPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(disconnectjButton, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE))
                .addGap(164, 164, 164))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msqlconnjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datacontroljPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disconnectjButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("myAppQuit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setToolTipText(resourceMap.getString("exitMenuItem.toolTipText")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setToolTipText(resourceMap.getString("aboutMenuItem.toolTipText")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 616, Short.MAX_VALUE)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(312, Short.MAX_VALUE))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusMessageLabel)
                        .addComponent(statusAnimationLabel))
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void staff_fiojTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staff_fiojTextFieldKeyTyped
        // TODO add your handling code here:
        if (staff_fiojTextField.getText().equals(null) || staff_fiojTextField.getText().equals("")) {
            mysqluserjTextField.setEnabled(false);
            mysqldbjPasswordField.setEnabled(false);
            msqlconnjButton.setEnabled(false);
        } else {
            mysqluserjTextField.setEnabled(true);
            mysqldbjPasswordField.setEnabled(true);
            msqlconnjButton.setEnabled(true);
        }
    }//GEN-LAST:event_staff_fiojTextFieldKeyTyped

    //connect to the HSQLDB and get patient count
    @Action
    public void hsqldbconnect() throws SQLException {
        conhsqljLabel.setText(dbconPROGmsg);
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            hsqlconn = DriverManager.getConnection("jdbc:hsqldb:" + hsqldb_file_name_prefix, "sa", "");
            conhsqljLabel.setText(dbconOKmsg);
            //enable controls         
            staff_fiojTextField.setEnabled(true);
            disconnectjButton.setEnabled(true);
            //disable self
            hsqlconnjButton.setEnabled(false);
        } catch (Exception e) {
            //"ERROR: failed to load HSQLDB JDBC driver."
            Logger.getLogger(DesktopApplication2View.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            //disable controls            
            staff_fiojTextField.setEnabled(false);
            disconnectjButton.setEnabled(false);
            //enable self
            hsqlconnjButton.setEnabled(true);
            return;
        }
    }

    @Action
    public void mysqlconnect() throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        String qrstr = "";
        int tmpID = 0;
        String tmpName = "";
        String tmpTown = "";

        conmsqljLabel.setText(dbconPROGmsg);
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            mysqlconn = DriverManager.getConnection(mysqldb_url, mysqluserName, mysqlpassword);
            conmsqljLabel.setText(dbconOKmsg);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Неможливо з\"єднатися!", "Dialog", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DesktopApplication2View.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        try {
            st = mysqlconn.createStatement();         // statement objects can be reused with    
            //TODO: need to send password as a MD5 string!
            String pass = new String(mysqldbjPasswordField.getPassword());
            qrstr = "select id, name, town from medoffice where (username='"
                    + mysqluserjTextField.getText() + "')AND("
                    + "userpass=MD5('" + pass + "'))";
            st.executeQuery(qrstr);
            rs = st.getResultSet();

            int count = 0;
            while (rs.next()) {
                tmpID = rs.getInt("id");
                tmpName = rs.getString("name");
                tmpTown = rs.getString("town");
//TODO:debug only
//                jTextArea2.append("id = " + tmpID + ", name = " + tmpName + ", town = " + tmpTown + "\n");
                ++count;
            }
            if (count != 1) {
                //TODO: need show dialog box!
                JOptionPane.showMessageDialog(new JFrame(), "Неправильний логін чи пароль!", "Dialog", JOptionPane.ERROR_MESSAGE);
                mysqldbshutdown();
            } else {
                officeID = tmpID;      //store office ID information
                officeName = tmpName;
                officeTown = tmpTown;
                //enable controls
                datasendjButton.setEnabled(true);
                //disable self
                msqlconnjButton.setEnabled(false);
            }
            //TODO: add query to get las recoded statistic value
            st.close();
        } catch (Exception ex) {
            Logger.getLogger(DesktopApplication2View.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Проблеми з мережею!", "Dialog", JOptionPane.ERROR_MESSAGE);
            conmsqljLabel.setText("Проблеми з мережею!");
        }
    }

    //stat01:HSQL return total patient count
    public int gethsqltotalpatientcount(Connection tmpconn) throws SQLException {
        int tmppatientcount = 0;
        String qrstr = "";
        ResultSet rs = null;
        Statement tmpst = tmpconn.createStatement();
        try {//get data from from HSQL
            qrstr = "select COUNT(ID) as PATIENTCOUNT from \"PUBLIC\".PATIENT";
            tmpst.executeQuery(qrstr);    // run the query
            rs = tmpst.getResultSet();
            if (rs.isBeforeFirst()) {
                rs.next();//goto first record - get only one record!
                tmppatientcount = rs.getInt("PATIENTCOUNT");
            }
        } catch (Exception ex) {
            Logger.getLogger(DesktopApplication2View.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            tmpst.close();
        }
        return tmppatientcount;
    }

    //stat01+log:MySQL set total patient count
    public int setmsqltotalpatientcount(Connection tmpconn, int tmpofficeid, String tmpstaffname, int tmppatientcount, String tmplogmsg) throws SQLException {
        int tmpinsreccount = 0;
        String qrstr = "";
        String tmpValues = "";
        ResultSet rs = null;
        Statement tmpst = tmpconn.createStatement();
        try {
            tmpValues = "(" + Integer.toString(tmpofficeid) + ", "
                    + "'" + tmpstaffname + "', " + Integer.toString(tmppatientcount)
                    + ", '" + tmplogmsg + "')";
            qrstr = "INSERT INTO officestat(office_id, staff_fio, patient_total, upd_record) VALUES " + tmpValues;
            tmpinsreccount = tmpst.executeUpdate(qrstr);
        } catch (Exception ex) {
            Logger.getLogger(DesktopApplication2View.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            tmpst.close();
        }
        return tmpinsreccount;
    }

    //stat02:MySQL return last date per office when the patient vizit count was submitted
    public Date getmsqllastsubmitteddate(Connection tmpconn, int tmpofficeid) throws SQLException {
        Date tmpdate = null;
        String qrstr = "";
        ResultSet rs = null;
        Statement tmpst = tmpconn.createStatement();
        try {//get lats date from from MySQL
            qrstr = "select max(vizit_date) as MAXVIZITDATE from statdetail where office_id=" + Integer.toString(tmpofficeid);
            tmpst.executeQuery(qrstr);    // run the query
            rs = tmpst.getResultSet();
            if (rs.isBeforeFirst()) {
                rs.next();//goto first record - get only one record!
                tmpdate = rs.getDate("MAXVIZITDATE");
            }
        } catch (Exception ex) {
            Logger.getLogger(DesktopApplication2View.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            tmpst.close();
        }
        return tmpdate;
    }

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!    
//    11111111111=============================================
//select count(patient_id) as patient_by_day, reg_date as my_date from "PUBLIC".EVENT group by reg_date order by reg_date
//показує кількість пацієнтів, зареєстрованих кожного дня
//222222222===========================================!!!!!!!!!!!!!!!!!!!!!!!!!!1
//select count (distinct patient_id) as patient_by_day, cast (visit_date as date) my_date from "PUBLIC".EVENT, "PUBLIC".VISIT 
//where (VISIT.EVENT_ID=EVENT.ID) and (reg_date<>my_date) group by my_date
//показує кількість візитів пацієнтів по днях, що відрізняються від дня їх реєстрації
//333333333================================================================
//select count(patient_id) as patient_by_day, reg_date as my_date from "PUBLIC".EVENT group by reg_date order by reg_date
//union all select count (distinct patient_id) as patient_by_day, cast (visit_date as date) my_date from "PUBLIC".EVENT, "PUBLIC".VISIT 
//where (VISIT.EVENT_ID=EVENT.ID) and (reg_date<>my_date) group by my_date
//працює
//4444====================================
//select sum (patient_by_day)as sum_patient_by_day, my_date from (select count(patient_id) as patient_by_day, reg_date as my_date from "PUBLIC".EVENT group by my_date 
//union all select count (distinct patient_id) as patient_by_day, cast (visit_date as date) as my_date from "PUBLIC".EVENT, "PUBLIC".VISIT 
//where (VISIT.EVENT_ID=EVENT.ID) and (reg_date<>my_date) group by my_date) group by my_date order by my_date
//працює - це сумування результатів запиту 1 та запиту 2 для дат, що співпадають на базі набору даних з запиту 3
    
    //stat02:HSQL get list of vizits with patient counts
    public ResultSet gethsqlpatcountperdayrs(Connection tmpconn, Date tmplastsubdate) throws SQLException {
        ResultSet rs = null;
        String qrstr = "";
        Statement tmpst = tmpconn.createStatement();
        try {//get data from from HSQL
            if (tmplastsubdate != null) {//not a first time statistic - get only last records
             //   qrstr = "select count(patient_id) as patient_by_day, reg_date from \"PUBLIC\".EVENT where reg_date>='" + tmplastsubdate.toString() + "' group by reg_date order by reg_date";
                qrstr = "select sum (patient_by_day) as sum_patient_by_day, vizit_date"+
                        " from (select count(patient_id) as patient_by_day, reg_date as vizit_date"+
                        " from \"PUBLIC\".EVENT group by vizit_date"+                
                        " union all"+
                        " select count (distinct patient_id) as patient_by_day, cast (visit_date as date) as vizit_date"+
                        " from \"PUBLIC\".EVENT, \"PUBLIC\".VISIT"+ 
                        " where (VISIT.EVENT_ID=EVENT.ID) and (reg_date<>vizit_date)"+
                        " group by vizit_date) where vizit_date>='" + tmplastsubdate.toString() + 
                        "' group by vizit_date order by vizit_date";
            } else {//first time statistic - get all records
             //    qrstr = "select count(patient_id) as patient_by_day, reg_date from \"PUBLIC\".EVENT group by reg_date order by reg_date";
                qrstr = "select sum (patient_by_day) as sum_patient_by_day, vizit_date"+
                        " from (select count(patient_id) as patient_by_day, reg_date as vizit_date"+
                        " from \"PUBLIC\".EVENT group by vizit_date"+                
                        " union all"+
                        " select count (distinct patient_id) as patient_by_day, cast (visit_date as date) as vizit_date"+
                        " from \"PUBLIC\".EVENT, \"PUBLIC\".VISIT"+ 
                        " where (VISIT.EVENT_ID=EVENT.ID) and (reg_date<>vizit_date)"+
                        " group by vizit_date) group by vizit_date order by vizit_date";
            }
            tmpst.executeQuery(qrstr);    // run the query
            rs = tmpst.getResultSet();
        } catch (Exception ex) {
            Logger.getLogger(DesktopApplication2View.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            tmpst.close();
        }
        return rs;
    }

    //stat02:MySQL set list of vizits with patient counts
    public int setmsqlpatcountperdayrs(Connection tmpconn, ResultSet jmedrs, int tmpofficeid, Date tmplastsubdate) throws SQLException {
        int tmpupdrec = 0;
        String qrstr = "";
        String tmpValues = "";
        int tmppatientcount = 0;

        Date tmpdate = null;
        Statement tmpst = tmpconn.createStatement();
        int count = 0;

        try {
            int pgbariterate = 0;
            while (jmedrs.next()) {
                tmppatientcount = jmedrs.getInt("sum_patient_by_day");
                tmpdate = jmedrs.getDate("vizit_date");                
                if ((tmplastsubdate != null) && (count == 0)) {//update must be doned only for the first record
                    qrstr = "UPDATE statdetail SET patient_count=" + Integer.toString(tmppatientcount)
                            + " WHERE (office_id=" + Integer.toString(tmpofficeid)
                            + ")AND(vizit_date='" + tmpdate.toString() + "')";
                } else {//inserl all onter new records
                    tmpValues = "(" + Integer.toString(tmpofficeid) + ", "
                            + "'" + tmpdate.toString() + "', " + Integer.toString(tmppatientcount) + ")";
                    qrstr = "INSERT INTO statdetail(office_id, vizit_date, patient_count) VALUES " + tmpValues;
                }
                tmpupdrec = tmpupdrec + tmpst.executeUpdate(qrstr);
                ++count;
                ++pgbariterate;
                transmitjProgressBar.setValue(pgbariterate);
                if (pgbariterate == 100) {
                    pgbariterate = 0;
                }
            }
            transmitjProgressBar.setValue(100);//done all!
        } catch (Exception ex) {
            Logger.getLogger(DesktopApplication2View.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            tmpst.close();
        }
        return tmpupdrec;
    }

    @Action
    public void sendstatisticdata() throws SQLException {
        ResultSet patcountperdayrs = null;
        int totalchangedrec = 0;
        int tmppatientcount = 0;
        int insrecord = 0;
        int runquery = 0;
        Date lastsubmdate = null;
        String tmplogmsg = "";

        if ((hsqlconn != null) && (mysqlconn != null)) {
            //disable self
            datasendjButton.setEnabled(false);
            //STATISTIC 01:get total patient count 
            tmppatientcount = gethsqltotalpatientcount(hsqlconn);//HSQL - get total paptient count from 

            //STATISTIC 02:get patient count per days (main statistics) {2012-02-06}
            lastsubmdate = getmsqllastsubmitteddate(mysqlconn, officeID);
            patcountperdayrs = gethsqlpatcountperdayrs(hsqlconn, lastsubmdate);//get patient count per day data from HSQL DB
            if (patcountperdayrs != null) {
                QueryNumjLabel.setText(Integer.toString(++runquery));
                insrecord = setmsqlpatcountperdayrs(mysqlconn, patcountperdayrs, officeID, lastsubmdate);//MySQL - write patient count per day data   
                totalchangedrec = totalchangedrec + insrecord;
                totalreccountjLabel.setText(Integer.toString(totalchangedrec));
            }

            //FINAL LOG: set log text 
            tmplogmsg = "1. Кількість пацієнтів по днях - добавлено/обновлено " + Integer.toString(insrecord) + " записів.\n";
            // ++runquery;
            QueryNumjLabel.setText(Integer.toString(++runquery));
            insrecord = setmsqltotalpatientcount(mysqlconn, officeID, staff_fiojTextField.getText(), tmppatientcount, tmplogmsg);//MySQL - write total patient count
            totalchangedrec = totalchangedrec + insrecord;
            totalreccountjLabel.setText(Integer.toString(totalchangedrec)); 
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Втрачено зв\"язок з однією з БД! \n Перезапустіть програму!", "Dialog", JOptionPane.ERROR_MESSAGE);
        }
    }

    //close all database and exit app
    @Action
    public void shutdownalldb() throws SQLException {

        if (hsqlconn != null) {//disconnect local HSQL database
            hsqldbshutdown();
        }
        if (mysqlconn != null) {//dicconnect remote MySQL database
            mysqldbshutdown();
        }
        initMyComponents();//set all controls to default
    }
    //close HSQLDB connection

    @Action
    public void hsqldbshutdown() {
        // db writes out to files and performs clean shuts down
        // otherwise there will be an unclean shutdown
        // when program ends       
        try {
            Statement st = hsqlconn.createStatement();
            st.execute("SHUTDOWN");
            hsqlconn.close();    // if there are no other open connection
            hsqlconn = null;
            conhsqljLabel.setText(dbconNOTmsg);
        } catch (Exception e) {
            Logger.getLogger(DesktopApplication2View.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    //close MySQL connection

    @Action
    public void mysqldbshutdown() {
//        Statement st = mysqlconn.createStatement();
//        st.execute("SHUTDOWN");
        try {
            mysqlconn.close();    // if there are no other open connection
            mysqlconn = null;
            conmsqljLabel.setText(dbconNOTmsg);
        } catch (Exception e) {
            Logger.getLogger(DesktopApplication2View.class.getName()).log(Level.SEVERE, null, e);
        }
        //       conjLabel.setText(dbnotconmsg);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel QueryNumjLabel;
    private javax.swing.JLabel conhsqljLabel;
    private javax.swing.JLabel conmsqljLabel;
    private javax.swing.JPanel datacontroljPanel;
    private javax.swing.JButton datasendjButton;
    private javax.swing.JButton disconnectjButton;
    private javax.swing.JButton hsqlconnjButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton msqlconnjButton;
    private javax.swing.JPanel msqlconnjPanel;
    private javax.swing.JPasswordField mysqldbjPasswordField;
    private javax.swing.JTextField mysqluserjTextField;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField staff_fiojTextField;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel totalreccountjLabel;
    private javax.swing.JProgressBar transmitjProgressBar;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
    private String dbconNOTmsg = "Немає!";
    private String dbconPROGmsg = "З\"єднуюсь...";
    private String dbconOKmsg = "Встановлено!";
//    private String hsqldb_file_name_prefix = "file:d:\\jMedReg_test\\data\\med_work";
    private String hsqldb_file_name_prefix = "file:..\\data\\med_work";
    private Connection hsqlconn;
 //   private String mysqldb_url = "jdbc:mysql://192.168.1.2:3306/jmedregstat";//local server!
    private String mysqldb_url = "jdbc:mysql://192.168.5.1:3306/jmedregstat?useUnicode=true&characterEncoding=UTF8";//VPN server!
    private Connection mysqlconn;
    private String mysqluserName = "statadmin";
    private String mysqlpassword = "adminstat";
    private int officeID = 0;
    private String officeName = "";
    private String officeTown = "";
}
