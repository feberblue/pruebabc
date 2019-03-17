package com.bancolombia.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bancolombia.models.Cliente;
import com.bancolombia.services.ClienteService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClienteController.class, secure = false)
public class ClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClienteService servicio;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAllCliente() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/clientes/cliente")
				.accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		int status = result.getResponse().getStatus();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		assertEquals(200, status);
	}

	@Test
	public void testGetClienteById() throws Exception {		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/clientes/cliente/1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{responseCode:200,message:OK,objeto{ id:1,nombre:Ana Duran Esteban,direccion:Dg. 1 # 68 - 50, ciudad: Bogotá, telefono:1}}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);

	}

	@Test
	public void testSave() throws Exception {
		Cliente newCliente = new Cliente("Prueba", "dir prueba", "PRUEBA",111L);
		String newClientestr = "{\"nombre\":\"Prueba\", \"direccion\":\"PRUEBA\", \"ciudad\":\"prueba\", \"telefono\":138764 }";
		Mockito.when(servicio.save(Mockito.any(Cliente.class))).thenReturn(newCliente);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/cliente/cliente")
				.accept(MediaType.APPLICATION_JSON).content(newClientestr).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testUpdate() throws Exception{
		
	}

	@Test
	public void testDelete() throws Exception{

	}

}
