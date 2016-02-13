package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Busca;
import controller.BuscaNomeMusica;
import controller.BuscaLetraMusica;
import model.Acervo;
import model.Artista;
import model.Musica;
import model.VagalumeConnection;

import javax.swing.Box;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;


public class TelaPrincipal implements Observer, WindowListener{
	
	private Busca busca;
	private List encontradas = new LinkedList();
	//private String unidadeNoDisco = "F";	
	
	private JFrame frmSadsa;
	private JTextField txtNomeTrecho;
	private JLabel lblNewLabel;
	private JLabel letraNaView = new JLabel("");
	JScrollPane scrollPane = new JScrollPane();
	
	JRadioButton radioTrecho = new JRadioButton("Buscar Letra");
	JRadioButton radioNome = new JRadioButton("Buscar Nome");
	//JButton btnAdicionar = new JButton("Adicionar");
	
	// Inicializa acervo
	Acervo acervo = new Acervo();
	TelaPrincipal view = this; // view
//	VagalumeConnection vc = new VagalumeConnection(); // Cria VagalumeConnection para pesquisar letras
	BuscaNomeMusica bnm = new BuscaNomeMusica(view, acervo);
	BuscaLetraMusica btm = new BuscaLetraMusica(view, acervo);
	
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel;
	private JLabel btnFecharLetra;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmSadsa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void inicializarAcervo(){
	
		acervo.addArtista("Pink Floyd");
		acervo.addArtista("Led Zeppelin");
		acervo.addArtista("Jethro Tull");
		acervo.addArtista("Kansas");
		acervo.addArtista("Queen");
		acervo.addArtista("Charlie Brown Jr.");
		acervo.addArtista("João Neto e Frederico");
		
		acervo.addMusica("Wish You Were Here","Pink Floyd");
		acervo.addMusica("Aqualung", "Jethro Tull");
		acervo.addMusica("One White Duck", "Jethro Tull");
		acervo.addMusica("Song for America", "Kansas");
		acervo.addMusica("Love of my Life", "Queen");
		acervo.addMusica("Não é serio", "Charlie Brown Jr.");
		acervo.addMusica("O Preço", "Charlie Brown Jr.");
		acervo.addMusica("Delegada", "João Neto e Frederico");
		acervo.addMusica("Have a Cigar", "Pink Floyd");
	
	}
	
	private void initialize() {
		
		this.inicializarAcervo();
		
		frmSadsa = new JFrame();
		frmSadsa.setTitle("Acervo de M\u00FAsicas");
		frmSadsa.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\fundo1.png"));
		frmSadsa.setBounds(100, 100, 764, 643);
		frmSadsa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frmSadsa.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		txtNomeTrecho = new JTextField();
		
		acervo.registerObserver(view);
		
		//inicializarAcervo();
		
		txtNomeTrecho.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (txtNomeTrecho.getText().equals("")) txtNomeTrecho.setText("Digite o nome ou trecho da música...");
			}
		});
		txtNomeTrecho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtNomeTrecho.setText("");
			}
		});
		
		txtNomeTrecho.setBounds(250, 64, 266, 20);
		desktopPane.add(txtNomeTrecho);
		txtNomeTrecho.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeTrecho.setText("Digite o nome ou trecho da m\u00FAsica ...");
		txtNomeTrecho.setColumns(10);
		
		lblNewLabel = new JLabel("");
		desktopPane.setLayer(lblNewLabel, 0);
		lblNewLabel.setIcon(new ImageIcon("img\\fundo1.png"));
		lblNewLabel.setBounds(0, 0, 907, 633);
		desktopPane.add(lblNewLabel);
		
		radioTrecho.setBounds(389, 33, 140, 23);
		desktopPane.add(radioTrecho);
		buttonGroup.add(radioTrecho);
		desktopPane.setLayer(radioTrecho, 1);
		
		radioNome.setBounds(250, 33, 124, 23);
		desktopPane.add(radioNome);
		buttonGroup.add(radioNome);
		desktopPane.setLayer(radioNome, 1);
		
		radioNome.setSelected(true);
		
		
		
		desktopPane.setLayer(scrollPane, 1);
		scrollPane.setBounds(127, 95, 500, 481);
		desktopPane.add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		desktopPane.setLayer(panel, 1);
		letraNaView.setBackground(Color.WHITE);
		letraNaView.setVerticalAlignment(SwingConstants.TOP);
		letraNaView.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(letraNaView, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(letraNaView, GroupLayout.PREFERRED_SIZE, 1868, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		btnFecharLetra = new JLabel("New label");
		btnFecharLetra.setVisible(false);
		btnFecharLetra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				scrollPane.setVisible(false);
				btnFecharLetra.setVisible(false);
			}
		});
		btnFecharLetra.setBounds(626, 95, 24, 14);
		desktopPane.add(btnFecharLetra);
		btnFecharLetra.setIcon(new ImageIcon("img\\fecharLetra.png"));
		desktopPane.setLayer(btnFecharLetra, 2);
		
		
		scrollPane.setVisible(false);
		
		
		txtNomeTrecho.addKeyListener(new KeyAdapter() {
					
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    // TODO , QUANDO USER APERTAR ENTER PRA BUSCAR
					
					if (txtNomeTrecho.getText().equals("") && radioTrecho.isSelected()) return;
					
					if (radioNome.isSelected()) view.setBusca(bnm);
					else view.setBusca(btm);
					
					acervo.buscarMusica(txtNomeTrecho.getText());
					
                }
			}
		});
	
		
	}
	
	
	// Copiados da View antiga
	
	public void update(List l){
		encontradas = l;
		busca.buscarMusica();
	}
	
	public void update(String msg){
		System.out.println(msg);
	}
	
	public void setBusca(Busca b){
		busca = b;
	}
	
	public void displayBuscaNome(){
		Collections.sort(encontradas);
		if (encontradas.isEmpty()){
			System.out.println("Nenhuma música encontrada!");
			letraNaView.setText("Nenhuma música encontrada!");
			if (!btnFecharLetra.isVisible()) btnFecharLetra.setVisible(true);
			if (!scrollPane.isVisible()) scrollPane.setVisible(true);
			return;
		}
		letraNaView.setText("<html>");
		for (Iterator i = encontradas.iterator(); i.hasNext(); ) {
		      Musica m = (Musica)i.next();
		      System.out.println(m.getNome());
		      letraNaView.setText(letraNaView.getText()+"<br>"+m.getNomeArtista()+" — "+m.getNome());
		      if (!btnFecharLetra.isVisible()) btnFecharLetra.setVisible(true);
		      if (!scrollPane.isVisible()) scrollPane.setVisible(true);
		}
		letraNaView.setText(letraNaView.getText()+"</html>");
		
	}
	
	public void displayBuscaLetra(){
		if (encontradas.isEmpty()){
			System.out.println("Nenhuma música encontrada!");
			letraNaView.setText("Nenhuma música encontrada!");
			if (!btnFecharLetra.isVisible()) btnFecharLetra.setVisible(true);
			if (!scrollPane.isVisible()) scrollPane.setVisible(true);
			return;
		}
		for (Iterator i = encontradas.iterator(); i.hasNext(); ) {
		      Musica m = (Musica)i.next();
		      System.out.println(m.getNome() + "\n\n"+m.getLetra());
		      String letra = m.getNome() +" — "+ m.getNomeArtista() +"\n\n"+m.getLetra();
		      String letraHtml =  "<html>"+letra.replace("\n", "<br>")+"</html>";
		      
		      letraNaView.setText(letraHtml);
		      if (!btnFecharLetra.isVisible()) btnFecharLetra.setVisible(true);
		      if (!scrollPane.isVisible()) scrollPane.setVisible(true);
		      
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		acervo.fechaDB();
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
