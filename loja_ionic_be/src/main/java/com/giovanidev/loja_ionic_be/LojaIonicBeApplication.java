package com.giovanidev.loja_ionic_be;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giovanidev.loja_ionic_be.domain.Categoria;
import com.giovanidev.loja_ionic_be.domain.Cidade;
import com.giovanidev.loja_ionic_be.domain.Cliente;
import com.giovanidev.loja_ionic_be.domain.Endereco;
import com.giovanidev.loja_ionic_be.domain.Estado;
import com.giovanidev.loja_ionic_be.domain.ItemPedido;
import com.giovanidev.loja_ionic_be.domain.Pagamento;
import com.giovanidev.loja_ionic_be.domain.PagamentoComBoleto;
import com.giovanidev.loja_ionic_be.domain.PagamentoComCartao;
import com.giovanidev.loja_ionic_be.domain.Pedido;
import com.giovanidev.loja_ionic_be.domain.Produto;
import com.giovanidev.loja_ionic_be.domain.enums.EstadoPagamento;
import com.giovanidev.loja_ionic_be.domain.enums.TipoCliente;
import com.giovanidev.loja_ionic_be.repository.CategoriaRepository;
import com.giovanidev.loja_ionic_be.repository.CidadeRepository;
import com.giovanidev.loja_ionic_be.repository.ClienteRepository;
import com.giovanidev.loja_ionic_be.repository.EnderecoRepository;
import com.giovanidev.loja_ionic_be.repository.EstadoRepository;
import com.giovanidev.loja_ionic_be.repository.ItemPedidoRepository;
import com.giovanidev.loja_ionic_be.repository.PagamentoRepository;
import com.giovanidev.loja_ionic_be.repository.PedidoRepository;
import com.giovanidev.loja_ionic_be.repository.ProdutoRepository;

@SpringBootApplication
public class LojaIonicBeApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(LojaIonicBeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Categoria cat1 = new Categoria(null, "INFORMATICA");
		Categoria cat2 = new Categoria(null, "ESCRITÓRIO");
		Categoria cat3 = new Categoria(null, "JARDINAGEM");
		Categoria cat4 = new Categoria(null, "CAMA MESA E BANHO");
		Categoria cat5 = new Categoria(null, "UTENSILIOS");
		Categoria cat6 = new Categoria(null, "BEBIDAS");
		Categoria cat7 = new Categoria(null, "ELETRONICA");
		Categoria cat8 = new Categoria(null, "CHINES");
		Categoria cat9 = new Categoria(null, "BARBECUE");
		Categoria cat10 = new Categoria(null, "PERFUMARIA");
		Categoria cat11 = new Categoria(null, "ESTOFADOS");
		Categoria cat12 = new Categoria(null, "VESTIMENTA");
	
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,cat12));
		Produto p1 = new Produto(null, "Computador",2000.00);
		Produto p2 = new Produto(null, "Impressora",800.00);
		Produto p3 = new Produto(null, "Mouse",2000.00);
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		est1.setCidades(Arrays.asList(c1));
		est2.setCidades(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","Maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		clienteRepository.save(cli1);
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto203","Jardim","38220834",cli1, c1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",cli1, c2);
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		cli1.setEnderecos(Arrays.asList(e1,e2));
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		clienteRepository.save(cli1);
		
		
		Pedido ped1 = new Pedido(null,format.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null,format.parse("10/10/2017 19:35"), cli1, e2);
		
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1,6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,format.parse("20/10/2017 00:00"),null);
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		
		
		
		pedidoRepository.saveAllAndFlush(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		clienteRepository.save(cli1);
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	}

}
