package br.ce.wcaquino.taskbackend.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void deveRetornarFalseParaDatasPassadas() {
		LocalDate umLugarNoPassado = LocalDate.of(1985, 7, 1);
		assertFalse(DateUtils.isEqualOrFutureDate(umLugarNoPassado));
	}
	
	@Test
	public void deveRetornarTrueParaAgora() {
		assertTrue(DateUtils.isEqualOrFutureDate(LocalDate.now()));
	}

	@Test
	public void deveRetornarTrueParaFuturo() {
		LocalDate laNaFrente = LocalDate.of(2030, 7, 1);
		assertTrue(DateUtils.isEqualOrFutureDate(laNaFrente));
	}
	
}
