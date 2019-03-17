package com.bancolombia.components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.bancolombia.models.AsesorBancario;
import com.bancolombia.models.Cliente;
import com.bancolombia.models.HistoricoTarjetas;
import com.bancolombia.models.Tarjeta;
import com.bancolombia.repository.AsesorBancarioRepository;
import com.bancolombia.repository.ClienteRepository;
import com.bancolombia.repository.HistoricoRepository;
import com.bancolombia.repository.TarjetaRepository;

/**
 * Clase de tipo COmponente que permite realizar el poblado inicial
 * de todas las tablas del sistema 
 * 
 * la base de datos in-Memory H2 contiene las siguientes tablas
 * 
 * ASESORBANCARIO: contiene la informacion de funcionarios
 * CLIENTE: contiene la informacion de los clientes del sistema
 * TARJETA: cntiene la informacion de las tarjetas de los clientes
 * HISTORICOTARJETA: contiene la informacion de los historicos de cada tarjeta 
 * @author Luis Beleño
 *
 */
@Component
public class DataLoadInit implements ApplicationRunner {

	private static final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	private AsesorBancarioRepository asRepo;
	private ClienteRepository clienteRepo;
	private HistoricoRepository historicoRepo;
	private TarjetaRepository tarjetaRepo;

	private String[] apellidos = { "Avila", "Bolaños", "Casas", "Duran", "Esteban", "Fernandez", "Gonzalez" };
	private String[] nombres = { "Ana", "Beatriz", "Carla", "Diana", "Esther", "Flavia", "Gina", "Isabel", "Johanna",
			"Kelly", "Laura", "Maria", "Norida" };
	private String[] especialidad = { "Analista de Cartera", "Cobro Juridico", "Especialista Corporativo" };

	private String[] clientes = { "Francisco", "Mario", "Zulma", "Andres", "Maria", "Gustavo", "Henrry", "Nelly",
			"Luis", "Luisa", "Esperanza", "Fernando" };

	private String[] nom_direccion = { "Cll.", "Cra.", "Dg.", "Trv." };
	private String[] numeros = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };

	private String[] tipo_tarjeta = { "Credito", "Debito" };
	private String[] ciudades = { "Bogotá", "Medellín", "Cali", "Cartagena", "Barranquilla", "Bucaramanga" };

	private String[] des_his_debito = { "Retiro de Cajero", "Gravamen Financiero", "Uso de Tarjeta",
			"Servicio de Canales" };
	private String[] des_his_credito = { "Avance por Cajero", "Gravamen Financiero", "Intereses" };

	/**
	 * Constructor del componente
	 * @param asRepo 
	 * @param clienteRepo
	 * @param historicoRepo
	 * @param tarjetaRepo
	 */
	public DataLoadInit(AsesorBancarioRepository asRepo, ClienteRepository clienteRepo,
			HistoricoRepository historicoRepo, TarjetaRepository tarjetaRepo) {
		super();
		this.asRepo = asRepo;
		this.clienteRepo = clienteRepo;
		this.historicoRepo = historicoRepo;
		this.tarjetaRepo = tarjetaRepo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		long countAs = asRepo.count();
		long countCliente = clienteRepo.count();
		
		if (countAs == 0) {
			llenarAsesores(6);
		}

		if (countCliente == 0) {
			llenarClientes(3);
		}
	}

	/**
	 * Genera un Numero de asesores bancarios en la DB
	 * 
	 * @param nroAsesores
	 */
	public void llenarAsesores(int nroAsesores) {

		while (nroAsesores >= 0) {
			int indiceApellido = (int) (Math.random() * apellidos.length);
			int indiceApellido2 = (int) (Math.random() * apellidos.length);
			int indicenombre = (int) (Math.random() * nombres.length);
			int indicenombre2 = (int) (Math.random() * nombres.length);
			int indiceEspecialidad = (int) (Math.random() * especialidad.length);

			indiceApellido = indiceApellido < 0 ? 0 : indiceApellido;
			indiceApellido2 = indiceApellido2 < 0 ? 0 : indiceApellido2;
			indicenombre = indicenombre < 0 ? 0 : indicenombre;
			indicenombre2 = indicenombre2 < 0 ? 0 : indicenombre2;
			indiceEspecialidad = indiceEspecialidad < 0 ? 0 : indiceEspecialidad;

			String nombreAsesor = nombres[indicenombre] + " " + nombres[indicenombre2] + " " + apellidos[indiceApellido]
					+ " " + apellidos[indiceApellido2];

			AsesorBancario as1 = new AsesorBancario(nombreAsesor, especialidad[indiceEspecialidad]);
			asRepo.save(as1);

			nroAsesores--;
		}
	}

	/***
	 * Permite generar un Numero de clientes de forma dinamica en la DB
	 * 
	 * @param nroClientes
	 *            Numero de clientes a generar
	 */
	public void llenarClientes(int nroClientes) {
		while (nroClientes >= 0) {
			int indiceApellido = (int) (Math.random() * apellidos.length);
			int indiceApellido2 = (int) (Math.random() * apellidos.length);
			int indicenombre = (int) (Math.random() * clientes.length);
			int indiceDireccion = (int) (Math.random() * nom_direccion.length);
			int indiceCiudad = (int) (Math.random() * ciudades.length);
			int indiceNumero = (int) (Math.random() * numeros.length);

			indiceApellido = indiceApellido < 0 ? 0 : indiceApellido;
			indiceApellido2 = indiceApellido2 < 0 ? 0 : indiceApellido2;
			indicenombre = indicenombre < 0 ? 0 : indicenombre;
			indiceDireccion = indiceDireccion < 0 ? 0 : indiceDireccion;

			String nombreCliente = nombres[indicenombre] + " " + apellidos[indiceApellido] + " "
					+ apellidos[indiceApellido2];
			String strDireccion = nom_direccion[indiceDireccion] + " " + numeros[indiceNumero] + " # "
					+ armarDireccion();

			Cliente cli = new Cliente(nombreCliente, strDireccion, ciudades[indiceCiudad], 1L);
			clienteRepo.save(cli);

			int nroTarjetas = (int) (Math.random() * 3);
			nroTarjetas = nroTarjetas <= 0 ? 1 : nroTarjetas;
			while (nroTarjetas > 0) {

				int indTipoTarjeta = (int) (Math.random() * tipo_tarjeta.length);
				indTipoTarjeta = indTipoTarjeta < 0 ? 0 : indTipoTarjeta;

				String strNroTarjeta = generarTarjeta();
				String strCCV = generarCCV();
				Tarjeta tarjeta = new Tarjeta(strNroTarjeta, Integer.parseInt(strCCV), tipo_tarjeta[indTipoTarjeta],
						cli);
				tarjetaRepo.save(tarjeta);

				int nroHistorico = (int) (Math.random() * 10);
				nroHistorico = nroHistorico < 0 ? 1 : nroHistorico;

				while (nroHistorico > 0) {
					Date fecha = new Date();
					String descripcion = formarDescripcionHistorico(tarjeta);
					Double montoPartial = (Math.random() * 600000);
					Double monto = montoPartial < 0 ? 600000 : montoPartial;
					HistoricoTarjetas ht = new HistoricoTarjetas(fecha, descripcion, monto, tarjeta);
					historicoRepo.save(ht);
					nroHistorico--;
				}
				nroTarjetas--;
			}

			nroClientes--;
		}
	}

	/**
	 * Arma la concordancia de los numero de una direccion de forma aleatoria
	 * 
	 * @return
	 */
	public String armarDireccion() {
		int ind_part1 = (int) (Math.random() * numeros.length);
		int ind_part2 = (int) (Math.random() * numeros.length);
		int ind_part3 = (int) (Math.random() * numeros.length);
		int ind_part4 = (int) (Math.random() * numeros.length);
		ind_part1 = ind_part1 < 0 ? 0 : ind_part1;
		ind_part2 = ind_part2 < 0 ? 0 : ind_part2;
		ind_part3 = ind_part3 < 0 ? 0 : ind_part3;
		ind_part4 = ind_part4 < 0 ? 0 : ind_part4;
		String direccion = numeros[ind_part1] + numeros[ind_part2] + " - " + numeros[ind_part3] + numeros[ind_part4];
		return direccion;
	}

	/**
	 * Permite generar Numeros de Tarjetas de forma Aleatoria
	 * 
	 * @return Numero de la Tarjeta Generada
	 */
	public String generarTarjeta() {

		String nroTarjeta = "";
		int veces = 4;
		while (veces > 0) {
			int ind_part1 = (int) (Math.random() * numeros.length);
			int ind_part2 = (int) (Math.random() * numeros.length);
			int ind_part3 = (int) (Math.random() * numeros.length);
			int ind_part4 = (int) (Math.random() * numeros.length);

			ind_part1 = ind_part1 < 0 ? 0 : ind_part1;
			ind_part2 = ind_part2 < 0 ? 0 : ind_part2;
			ind_part3 = ind_part3 < 0 ? 0 : ind_part3;
			ind_part4 = ind_part4 < 0 ? 0 : ind_part4;

			nroTarjeta = nroTarjeta + numeros[ind_part1] + numeros[ind_part2] + numeros[ind_part3] + numeros[ind_part4]
					+ "-";
			veces--;
		}

		nroTarjeta = nroTarjeta.substring(0, nroTarjeta.length() - 1);
		return nroTarjeta;
	}

	/**
	 * Genera el CCV de una tarjeta de forma Dinamica
	 * 
	 * @return
	 */
	public String generarCCV() {

		String nroCCV = "";
		int nroLongitud = (int) (Math.random() * 4);
		if (nroLongitud % 2 == 0) {
			nroLongitud = 4;
		} else {
			nroLongitud = 3;
		}

		while (nroLongitud > 0) {
			int ind_part = (int) (Math.random() * numeros.length);
			ind_part = ind_part < 0 ? 0 : ind_part;
			nroCCV = nroCCV + numeros[ind_part];
			nroLongitud--;
		}

		return nroCCV;
	}

	/**
	 * Obtiene de forma aleatoria la descripcion para el historico de un tarjeta ya
	 * sea Debito o Credito
	 * 
	 * @param tarjeta
	 * @return
	 */
	public String formarDescripcionHistorico(Tarjeta tarjeta) {
		String description = "";
		if (tarjeta.getTipoTarjeta().equals("Debito")) {
			int indice = (int) (Math.random() * des_his_debito.length);
			indice = indice < 0 ? 0 : indice;
			description = des_his_debito[indice];
		} else {
			int indice = (int) (Math.random() * des_his_credito.length);
			indice = indice < 0 ? 0 : indice;
			description = des_his_credito[indice];
		}

		return description;
	}

}
