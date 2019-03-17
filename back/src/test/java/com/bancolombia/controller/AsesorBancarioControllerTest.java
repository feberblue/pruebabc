package com.bancolombia.controller;

import static org.junit.Assert.assertEquals;

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

import com.bancolombia.models.AsesorBancario;
import com.bancolombia.services.AsesorBancarioService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AsesorBancarioController.class, secure = false)
public class AsesorBancarioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AsesorBancarioService servicio;

	AsesorBancario asesor = new AsesorBancario(1L, "Norida Laura Duran Avila", "Especialista Corporativo");

	String exampleAsesor = "{\"id\":1, \"nombre\":\"Norida Laura Duran Avila\", \"especialidad\":\"Especialista Corporativo\"}";

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testGetAllAsesor() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/asesores/asesorbancario")
				.accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		String content = result.getResponse().getContentAsString();
		System.out.println(content);

	}

	/**
	 * Cambiar los valores dados que son aleatorios cuando se crean en la db
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAsesorById() throws Exception {
		Mockito.when(servicio.getAsesorById(Mockito.anyLong())).thenReturn(asesor);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/asesores/asesorbancario/1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,nombre:Norida Laura Duran Avila,especialidad:Especialista Corporativo}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testSaveAsesor() throws Exception {
		AsesorBancario newAsesor = new AsesorBancario("Norida Laura Duran Avila", "Especialista Corporativo");
		Mockito.when(servicio.save(Mockito.any(AsesorBancario.class))).thenReturn(newAsesor);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/asesores/asesorbancario")
				.accept(MediaType.APPLICATION_JSON).content(exampleAsesor).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testUpdateAsesor() throws Exception {
		AsesorBancario newAsesor = new AsesorBancario("Norida Laura Duran Avila", "Especialista Corporativo");
		Mockito.when(servicio.update(Mockito.anyLong(), Mockito.any(AsesorBancario.class))).thenReturn(newAsesor);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/asesores/asesorbancario/1")
				.accept(MediaType.APPLICATION_JSON).content(exampleAsesor).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testDeleteAsesorBancario() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/asesores/asesorbancario/1");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
