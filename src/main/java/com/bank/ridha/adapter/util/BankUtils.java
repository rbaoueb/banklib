package com.bank.ridha.adapter.util;

import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.function.Supplier;

@UtilityClass
public final class BankUtils {

	private static final Random random = new Random(System.currentTimeMillis());

	public static final Supplier<Integer> generateId = () -> Math.abs(random.nextInt());

}
