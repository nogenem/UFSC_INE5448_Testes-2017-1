package visao;

import interfaces.IUsuario;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import modelo.FabricaDeMercado;
import modelo.Lance;
import modelo.MercadoLeilao;
import modelo.ProdutoLeilao;
import modelo.Usuario;

public class LeilaoGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton botao1, botao2, botao3, botao4, botao5, botao6, botao7, botao8, botao9, botao10, botao11;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private MercadoLeilao mercado;

	public LeilaoGUI(final MercadoLeilao mercadoLeilao) {
		this.mercado = mercadoLeilao;
		setTitle("Mercado de leilao");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 455);
		setResizable(false);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);

		botao1 = new JButton("Cadastrar Usuario");
		botao1.setBounds(30, 20, 350, 30);
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaCadastrarUsuario(mercado);
			}
		});
		getContentPane().add(botao1);

		botao2 = new JButton("Cadastrar Produto");
		botao2.setBounds(30, 60, 350, 30);
		botao2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaCadastrarProduto(mercado);
			}
		});
		getContentPane().add(botao2);

		botao3 = new JButton("Dar Lance");
		botao3.setBounds(30, 100, 350, 30);
		botao3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaDarLance(mercado);
			}
		});
		getContentPane().add(botao3);

		botao4 = new JButton("Ver Usu\u00E1rios Cadastrados");
		botao4.setBounds(30, 140, 350, 30);
		botao4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaUsuariosCadastrados(mercado);
			}
		});
		getContentPane().add(botao4);

		botao5 = new JButton("Ver Produtos Em Leilao");
		botao5.setBounds(30, 180, 350, 30);
		botao5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaProdutosEmLeilao(mercado);
			}
		});
		getContentPane().add(botao5);

		botao6 = new JButton("Ver Produtos Vendidos");
		botao6.setBounds(30, 220, 350, 30);
		botao6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaProdutosVendidos(mercado);
			}
		});
		getContentPane().add(botao6);

		botao7 = new JButton("Ver Produtos Vencidos");
		botao7.setBounds(30, 260, 350, 30);
		botao7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaProdutosVencidos(mercado);
			}
		});
		getContentPane().add(botao7);

		botao8 = new JButton("Ver Produtos De Um Leiloador");
		botao8.setBounds(30, 300, 350, 30);
		botao8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaProdutosDeUmLeiloador(mercado);
			}
		});
		getContentPane().add(botao8);

		botao9 = new JButton("Ver Lances De Um Usuario");
		botao9.setBounds(30, 340, 350, 30);
		botao9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaLancesDeUmUsuario(mercado);
			}
		});
		getContentPane().add(botao9);

		botao10 = new JButton("Salvar o Mercado");
		botao10.setBounds(30, 381, 165, 30);
		botao10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FabricaDeMercado fabrica = new FabricaDeMercado();
				fabrica.desmontar(mercado);
				JOptionPane.showMessageDialog(null, "Mercado Salvo");
			}

		});
		getContentPane().add(botao10);

		botao11 = new JButton("Carregar o Mercado");
		botao11.setBounds(215, 381, 165, 30);
		botao11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FabricaDeMercado fabrica = new FabricaDeMercado();
				mercado = (MercadoLeilao) fabrica.montar();
				JOptionPane.showMessageDialog(null, "Mercado Carregado");
			}
		});
		getContentPane().add(botao11);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void janelaCadastrarUsuario(final MercadoLeilao mercado) {
		final JFrame janela = new JFrame("Cadastrar Usuario");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(420, 420);
		janela.setResizable(false);
		janela.getContentPane().setLayout(null);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);

		JLabel labelNome = new JLabel("Nome do usuario:");
		labelNome.setBounds(30, 30, 150, 20);
		janela.getContentPane().add(labelNome);

		final JTextField fieldNome = new JTextField();
		fieldNome.setBounds(30, 50, 350, 30);
		janela.getContentPane().add(fieldNome);

		JLabel labelEndereco = new JLabel("Endereco do usuario:");
		labelEndereco.setBounds(30, 91, 150, 20);
		janela.getContentPane().add(labelEndereco);

		final JTextField fieldEndereco = new JTextField();
		fieldEndereco.setBounds(30, 111, 350, 30);
		janela.getContentPane().add(fieldEndereco);

		JLabel labelEmail = new JLabel("E-mail do usuario:");
		labelEmail.setBounds(30, 152, 150, 20);
		janela.getContentPane().add(labelEmail);

		final JTextField fieldEmail = new JTextField();
		fieldEmail.setBounds(30, 172, 350, 30);
		janela.getContentPane().add(fieldEmail);

		JLabel labelCpf = new JLabel("CPF do usuario:");
		labelCpf.setBounds(30, 213, 150, 20);
		janela.getContentPane().add(labelCpf);

		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("###.###.###-##");
		} catch (Exception e) {
		}
		mascara.setPlaceholderCharacter('_');
		final JFormattedTextField fieldCpf = new JFormattedTextField(mascara);
		fieldCpf.setBounds(30, 233, 350, 30);
		janela.getContentPane().add(fieldCpf);

		JButton botao = new JButton("Cadastrar");
		botao.setBounds(155, 330, 100, 30);
		botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = fieldNome.getText();
				String endereco = fieldEndereco.getText();
				String email = fieldEmail.getText();
				String cpf = fieldCpf.getText();
				try {
					mercado.cadastrarUsuario(nome, endereco, email, cpf);
					janela.dispose();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "O usuario ja existe.");
				}
			}
		});
		janela.getContentPane().add(botao);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void janelaCadastrarProduto(final MercadoLeilao mercado) {
		final JFrame janela = new JFrame("Cadastrar Produto");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(420, 420);
		janela.setResizable(false);
		janela.getContentPane().setLayout(null);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);

		JLabel labelNome = new JLabel("Nome do produto:");
		labelNome.setBounds(30, 25, 150, 20);
		janela.getContentPane().add(labelNome);

		final JTextField fieldNome = new JTextField();
		fieldNome.setBounds(30, 45, 350, 30);
		janela.getContentPane().add(fieldNome);

		JLabel labelDescricao = new JLabel("Descricao do produto:");
		labelDescricao.setBounds(30, 86, 150, 20);
		janela.getContentPane().add(labelDescricao);

		final JTextField fieldDescricao = new JTextField();
		fieldDescricao.setBounds(30, 106, 350, 30);
		janela.getContentPane().add(fieldDescricao);

		JLabel labelLanceMinimo = new JLabel("Lance minimo:");
		labelLanceMinimo.setBounds(30, 147, 150, 20);
		janela.getContentPane().add(labelLanceMinimo);

		final JTextField fieldLanceMinimo = new JTextField();
		fieldLanceMinimo.setBounds(30, 167, 350, 30);
		janela.getContentPane().add(fieldLanceMinimo);

		JLabel labelCpf = new JLabel("CPF do leiloador:");
		labelCpf.setBounds(30, 208, 150, 20);
		janela.getContentPane().add(labelCpf);

		MaskFormatter mascaraCpf = null;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
		} catch (Exception e) {
		}
		final JFormattedTextField fieldCpf = new JFormattedTextField(mascaraCpf);
		fieldCpf.setBounds(30, 228, 350, 30);
		janela.getContentPane().add(fieldCpf);

		JLabel labelData = new JLabel("Data limite (dd/MM/yyyy HH:mm)");
		labelData.setBounds(30, 269, 350, 20);
		janela.getContentPane().add(labelData);

		MaskFormatter mascaraData = null;
		try {
			mascaraData = new MaskFormatter("##/##/####   ##:##");
			mascaraData.setPlaceholderCharacter('_');
		} catch (Exception e) {
			e.printStackTrace();
		}
		final JFormattedTextField fieldData = new JFormattedTextField(mascaraData);
		fieldData.setBounds(30, 289, 350, 30);
		janela.getContentPane().add(fieldData);

		JButton botao = new JButton("Cadastrar");
		botao.setBounds(155, 330, 100, 30);
		botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = fieldNome.getText();
				String descricao = fieldDescricao.getText();
				Double valorMinimo = 0.0;
				try {
					valorMinimo = Double.parseDouble(fieldLanceMinimo.getText());
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "O lance minimo deve ser um numero.\nEx: 1234.56");
				}
				String cpfLeiloador = fieldCpf.getText();
				String data = fieldData.getText();
				try {
					Date dataLimite = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data);
					mercado.cadastrarProduto(nome, descricao, valorMinimo, cpfLeiloador, dataLimite);
					janela.dispose();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "O produto ja existe ou o leiloador nao esta cadastrado.");
				}
			}
		});
		janela.getContentPane().add(botao);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void janelaDarLance(final MercadoLeilao mercado) {
		final JFrame janela = new JFrame("Dar Lance");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(420, 420);
		janela.setResizable(false);
		janela.getContentPane().setLayout(null);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);

		JLabel labelNome = new JLabel("Nome produto:");
		labelNome.setBounds(30, 30, 150, 20);
		janela.getContentPane().add(labelNome);

		final JTextField fieldNome = new JTextField();
		fieldNome.setBounds(30, 50, 350, 30);
		janela.getContentPane().add(fieldNome);

		JLabel labelCpf = new JLabel("CPF do comprador:");
		labelCpf.setBounds(30, 91, 150, 20);
		janela.getContentPane().add(labelCpf);

		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("###.###.###-##");
		} catch (Exception e) {
		}
		mascara.setPlaceholderCharacter('_');
		final JFormattedTextField fieldCpf = new JFormattedTextField(mascara);
		fieldCpf.setBounds(30, 111, 350, 30);
		janela.getContentPane().add(fieldCpf);

		JLabel labelValor = new JLabel("Valor do lance:");
		labelValor.setBounds(30, 152, 150, 20);
		janela.getContentPane().add(labelValor);

		final JTextField fieldValor = new JTextField();
		fieldValor.setBounds(30, 172, 350, 30);
		janela.getContentPane().add(fieldValor);

		JButton botao = new JButton("Dar lance");
		botao.setBounds(155, 330, 100, 30);
		botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomeProduto = fieldNome.getText();
				String cpfComprador = fieldCpf.getText();
				Double valorLance = 0.0;
				try {
					valorLance = Double.parseDouble(fieldValor.getText());
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "O valor do lance deve ser um numero.\nEx: 1234.56");
				}
				try {
					mercado.daLance(nomeProduto, cpfComprador, valorLance);
					janela.dispose();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"O valor do lance eh inferior ao necessario ou o comprador nao esta cadastrado.");
				}
			}
		});
		janela.getContentPane().add(botao);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void janelaUsuariosCadastrados(MercadoLeilao mercado) {
		JFrame janela = new JFrame("Usuarios Cadastrados");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 250);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(5, 5, 190, 211);
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setBounds(205, 5, 379, 211);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.setLayout(null);
		contentPane.add(scroll);
		contentPane.add(scroll2);

		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		scroll2.setViewportView(panel);

		List<IUsuario> usuariosCadastrados = mercado.getUsuariosCadastrados();
		final JList list = new JList(usuariosCadastrados.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		scroll.setViewportView(list);

		IUsuario usuarioSelecionado = (IUsuario) list.getSelectedValue();
		final JLabel labelNome = new JLabel("Nome:  " + usuarioSelecionado.getNome());
		final JLabel labelCpf = new JLabel("CPF:  " + usuarioSelecionado.getCpf());
		final JLabel labelEndereco = new JLabel("Endereco:  " + usuarioSelecionado.getEndereco());
		final JLabel labelEmail = new JLabel("E-mail:  " + usuarioSelecionado.getEmail());
		panel.add(labelNome);
		panel.add(labelCpf);
		panel.add(labelEndereco);
		panel.add(labelEmail);

		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				Usuario usuarioSelecionado = (Usuario) list.getSelectedValue();
				labelNome.setText("Nome:  " + usuarioSelecionado.getNome());
				labelCpf.setText("CPF:  " + usuarioSelecionado.getCpf());
				labelEndereco.setText("Endereco:  " + usuarioSelecionado.getEndereco());
				labelEmail.setText("E-mail:  " + usuarioSelecionado.getEmail());
				panel.add(labelNome);
				panel.add(labelCpf);
				panel.add(labelEndereco);
				panel.add(labelEmail);
			}
		});
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void janelaProdutosEmLeilao(MercadoLeilao mercado) {
		JFrame janela = new JFrame("Produtos Em Leilao");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 250);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(5, 5, 190, 211);
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setBounds(205, 5, 379, 211);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.setLayout(null);
		contentPane.add(scroll);
		contentPane.add(scroll2);

		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(6, 1, 0, 0));
		scroll2.setViewportView(panel);

		List<ProdutoLeilao> produtosEmLeilao = (List<ProdutoLeilao>) mercado.getProdutosEmLeilao();
		final JList list = new JList(produtosEmLeilao.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		scroll.setViewportView(list);

		ProdutoLeilao produtoSelecionado = (ProdutoLeilao) list.getSelectedValue();
		final JLabel labelNome = new JLabel("Nome:  " + produtoSelecionado.getNome());
		final JLabel labelDescricao = new JLabel("Descricao:  " + produtoSelecionado.getDescricao());
		final JLabel labelLanceMinimo = new JLabel("Lance minimo:  R$" + produtoSelecionado.getLanceMinimo());
		final JLabel labelUltimoLance = new JLabel("Ultimo lance: R$" + produtoSelecionado.getValorUltimoLance());
		final JLabel labelCpfLeiloador = new JLabel("CPF Leiloador:  " + produtoSelecionado.getCpfLeiloador());
		final JLabel labelDataLimite = new JLabel("Data limite:  " + dateFormat.format(produtoSelecionado.getDataLimite()));
		panel.add(labelNome);
		panel.add(labelDescricao);
		panel.add(labelLanceMinimo);
		panel.add(labelUltimoLance);
		panel.add(labelCpfLeiloador);
		panel.add(labelDataLimite);

		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				ProdutoLeilao produtoSelecionado = (ProdutoLeilao) list.getSelectedValue();
				labelNome.setText("Nome:  " + produtoSelecionado.getNome());
				labelDescricao.setText("Descricao:  " + produtoSelecionado.getDescricao());
				labelLanceMinimo.setText("Lance minimo:  R$" + produtoSelecionado.getLanceMinimo());
				labelUltimoLance.setText("Ultimo lance: R$" + produtoSelecionado.getValorUltimoLance());
				labelCpfLeiloador.setText("CPF Leiloador:  " + produtoSelecionado.getCpfLeiloador());
				labelDataLimite.setText("Data limite:  " + dateFormat.format(produtoSelecionado.getDataLimite()));
				panel.add(labelNome);
				panel.add(labelDescricao);
				panel.add(labelLanceMinimo);
				panel.add(labelUltimoLance);
				panel.add(labelCpfLeiloador);
				panel.add(labelDataLimite);
			}
		});
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void janelaProdutosVendidos(MercadoLeilao mercado) {
		JFrame janela = new JFrame("Produtos Vendidos");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 250);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(5, 5, 190, 211);
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setBounds(205, 5, 379, 211);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.setLayout(null);
		contentPane.add(scroll);
		contentPane.add(scroll2);

		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(7, 1, 0, 0));
		scroll2.setViewportView(panel);

		List<ProdutoLeilao> produtosVendidos = (List<ProdutoLeilao>) mercado.getProdutosVendidos();
		final JList list = new JList(produtosVendidos.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		scroll.setViewportView(list);

		ProdutoLeilao produtoSelecionado = (ProdutoLeilao) list.getSelectedValue();
		final JLabel labelNome = new JLabel("Nome:  " + produtoSelecionado.getNome());
		final JLabel labelDescricao = new JLabel("Descricao:  " + produtoSelecionado.getDescricao());
		final JLabel labelLanceMinimo = new JLabel("Lance minimo:  R$" + produtoSelecionado.getLanceMinimo());
		final JLabel labelPrecoVendido = new JLabel("Preco vendido: R$" + produtoSelecionado.getValorUltimoLance());
		final JLabel labelCpfLeiloador = new JLabel("CPF Leiloador:  " + produtoSelecionado.getCpfLeiloador());
		final JLabel labelCpfComprador = new JLabel("CPF Comprador:  " + produtoSelecionado.getCpfComprador());
		final JLabel labelDataLimite = new JLabel("Data limite:  " + dateFormat.format(produtoSelecionado.getDataLimite()));
		panel.add(labelNome);
		panel.add(labelDescricao);
		panel.add(labelLanceMinimo);
		panel.add(labelPrecoVendido);
		panel.add(labelCpfLeiloador);
		panel.add(labelCpfComprador);
		panel.add(labelDataLimite);

		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				ProdutoLeilao produtoSelecionado = (ProdutoLeilao) list.getSelectedValue();
				labelNome.setText("Nome:  " + produtoSelecionado.getNome());
				labelDescricao.setText("Descricao:  " + produtoSelecionado.getDescricao());
				labelLanceMinimo.setText("Lance minimo:  R$" + produtoSelecionado.getLanceMinimo());
				labelPrecoVendido.setText("Preco vendido: R$" + produtoSelecionado.getValorUltimoLance());
				labelCpfLeiloador.setText("CPF Leiloador:  " + produtoSelecionado.getCpfLeiloador());
				labelCpfComprador.setText("CPF Comprador:  " + produtoSelecionado.getCpfComprador());
				labelDataLimite.setText("Data limite:  " + dateFormat.format(produtoSelecionado.getDataLimite()));
				panel.add(labelNome);
				panel.add(labelDescricao);
				panel.add(labelLanceMinimo);
				panel.add(labelPrecoVendido);
				panel.add(labelCpfLeiloador);
				panel.add(labelCpfComprador);
				panel.add(labelDataLimite);
			}
		});
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void janelaProdutosVencidos(MercadoLeilao mercado) {
		JFrame janela = new JFrame("Produtos Vencidos E Nao Vendidos");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 250);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(5, 5, 190, 211);
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setBounds(205, 5, 379, 211);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.setLayout(null);
		contentPane.add(scroll);
		contentPane.add(scroll2);

		final JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(5, 1, 0, 0));
		scroll2.setViewportView(panel);

		List<ProdutoLeilao> produtosVencidosENaoVendidos = (List<ProdutoLeilao>) mercado.getProdutosVencidosENaoVendidos();
		final JList list = new JList(produtosVencidosENaoVendidos.toArray());
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		scroll.setViewportView(list);

		ProdutoLeilao produtoSelecionado = (ProdutoLeilao) list.getSelectedValue();
		final JLabel labelNome = new JLabel("Nome:  " + produtoSelecionado.getNome());
		final JLabel labelDescricao = new JLabel("Descricao:  " + produtoSelecionado.getDescricao());
		final JLabel labelLanceMinimo = new JLabel("Lance minimo:  R$" + produtoSelecionado.getLanceMinimo());
		final JLabel labelCpfLeiloador = new JLabel("CPF Leiloador:  " + produtoSelecionado.getCpfLeiloador());
		final JLabel labelDataLimite = new JLabel("Data limite:  " + dateFormat.format(produtoSelecionado.getDataLimite()));
		panel.add(labelNome);
		panel.add(labelDescricao);
		panel.add(labelLanceMinimo);
		panel.add(labelCpfLeiloador);
		panel.add(labelDataLimite);

		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				ProdutoLeilao produtoSelecionado = (ProdutoLeilao) list.getSelectedValue();
				labelNome.setText("Nome:  " + produtoSelecionado.getNome());
				labelDescricao.setText("Descricao:  " + produtoSelecionado.getDescricao());
				labelLanceMinimo.setText("Lance minimo:  R$" + produtoSelecionado.getLanceMinimo());
				labelCpfLeiloador.setText("CPF Leiloador:  " + produtoSelecionado.getCpfLeiloador());
				labelDataLimite.setText("Data limite:  " + dateFormat.format(produtoSelecionado.getDataLimite()));
				panel.add(labelNome);
				panel.add(labelDescricao);
				panel.add(labelLanceMinimo);
				panel.add(labelCpfLeiloador);
				panel.add(labelDataLimite);
			}
		});
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void janelaProdutosDeUmLeiloador(final MercadoLeilao mercado) {
		JFrame janela = new JFrame("Ver Produtos De Um Leiloador");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 320);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);
		contentPane.setLayout(null);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(5, 56, 189, 224);
		contentPane.add(scrollPane);

		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(204, 56, 385, 224);
		contentPane.add(scrollPane_1);

		JLabel label = new JLabel("Selecione o usuario");
		label.setBounds(5, 3, 113, 20);
		contentPane.add(label);

		List<IUsuario> usuariosCadastrados = mercado.getUsuariosCadastrados();

		final JComboBox comboBox = new JComboBox(usuariosCadastrados.toArray());
		comboBox.setBounds(5, 23, 189, 22);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Usuario usuarioSelecionado = (Usuario) comboBox.getSelectedItem();
				List<ProdutoLeilao> produtosDoLeiloador = null;
				try {
					produtosDoLeiloador = mercado.retornaProdutosDeUmLeiloador(usuarioSelecionado.getCpf());
				} catch (Exception e) {
					e.printStackTrace();
				}
				final JList listProdutos = new JList(produtosDoLeiloador.toArray());
				scrollPane.setViewportView(listProdutos);
				listProdutos.setBorder(new LineBorder(new Color(0, 0, 0)));
				listProdutos.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent arg0) {
						ProdutoLeilao produtoSelecionado = (ProdutoLeilao) listProdutos.getSelectedValue();
						JPanel panel = new JPanel();
						panel.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel.setLayout(new GridLayout(6, 1, 0, 0));
						JLabel labelNome = new JLabel("Nome:  " + produtoSelecionado.getNome());
						JLabel labelDescricao = new JLabel("Descricao:  " + produtoSelecionado.getDescricao());
						JLabel labelLanceMinimo = new JLabel("Lance minimo:  R$" + produtoSelecionado.getLanceMinimo());
						JLabel labelUltimoLance = new JLabel("Ultimo lance: R$" + produtoSelecionado.getValorUltimoLance());
						JLabel labelCpfLeiloador = new JLabel("CPF Leiloador:  " + produtoSelecionado.getCpfLeiloador());
						JLabel labelDataLimite = new JLabel("Data limite:  " + dateFormat.format(produtoSelecionado.getDataLimite()));
						panel.add(labelNome);
						panel.add(labelDescricao);
						panel.add(labelLanceMinimo);
						panel.add(labelUltimoLance);
						panel.add(labelCpfLeiloador);
						panel.add(labelDataLimite);
						scrollPane_1.setViewportView(panel);
					}
				});
			}
		});

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void janelaLancesDeUmUsuario(final MercadoLeilao mercado) {
		JFrame janela = new JFrame("Ver Lances De Um Usuario");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(600, 320);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		janela.setContentPane(contentPane);
		contentPane.setLayout(null);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(5, 56, 189, 224);
		contentPane.add(scrollPane);

		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(204, 56, 385, 224);
		contentPane.add(scrollPane_1);

		JLabel label = new JLabel("Selecione o usuario");
		label.setBounds(5, 3, 113, 20);
		contentPane.add(label);

		List<IUsuario> usuariosCadastrados = mercado.getUsuariosCadastrados();

		final JComboBox comboBox = new JComboBox(usuariosCadastrados.toArray());
		comboBox.setBounds(5, 23, 189, 22);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Usuario usuarioSelecionado = (Usuario) comboBox.getSelectedItem();
				List<Lance> lancesDoUsuario = null;
				try {
					lancesDoUsuario = mercado.retornaLancesDeUmUsuario(usuarioSelecionado.getCpf());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				final JList listLances = new JList(lancesDoUsuario.toArray());
				scrollPane.setViewportView(listLances);
				listLances.setBorder(new LineBorder(new Color(0, 0, 0)));
				listLances.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent arg0) {
						Lance lanceSelecionado = (Lance) listLances.getSelectedValue();
						JPanel panel = new JPanel();
						panel.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel.setLayout(new GridLayout(3, 1, 0, 0));
						JLabel labelNomeDoUsuario = new JLabel("Nome do usuario:  " + lanceSelecionado.getNomeDonoDoLance());
						JLabel labelNomeDoProduto = new JLabel(
								"Nome do produto:  " + lanceSelecionado.getNomeProdutoQueRecebeuOLance());
						JLabel labelValorDoLance = new JLabel("Valor do lance:  R$" + lanceSelecionado.getValorDoLance());
						panel.add(labelNomeDoUsuario);
						panel.add(labelNomeDoProduto);
						panel.add(labelValorDoLance);
						scrollPane_1.setViewportView(panel);
					}
				});
			}
		});
	}
}