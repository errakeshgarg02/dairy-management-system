package com.rakesh.dairy.util;

import java.text.DecimalFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.rakesh.dairy.dao.RateRepository;
import com.rakesh.dairy.entity.Rate;
import com.rakesh.dairy.exception.DairyException;

@Component
public class DairyUtil {

	@Value("${dairy.customer-code.prefix: R}")
	private String customerCodePrefix;
	@Autowired
	private RateRepository rateRepository;
	
	private static DecimalFormat df2 = new DecimalFormat(".##");

	public String increment(String number) {
		if (StringUtils.isEmpty(number)) {
			return customerCodePrefix + "00" + 1;
		} else {
			char[] cars = number.toUpperCase().toCharArray();
			for (int i = cars.length - 1; i >= 0; i--) {
				if (cars[i] == 'Z') {
					cars[i] = 'A';
				} else if (cars[i] == '9') {
					cars[i] = '0';
				} else {
					cars[i]++;
					break;
				}
			}
			return String.valueOf(cars);
		}
	}
	
	public Double getPrice(Float weight, Float fat, Float snf) throws DairyException {
		Optional<Rate> findRateByFatAndSnf = rateRepository.findRateByFatAndSnf(fat, snf);
		if(findRateByFatAndSnf.isPresent()) {
			return calculatePrice(weight, fat, findRateByFatAndSnf.get().getRate());
		} else {
			throw new DairyException("Rate not found for fat "+fat+ " and snf "+snf);
		}
	}
	
	private Double calculatePrice(Float weight, Float fat, Double rate) {
		Double price = weight * fat * rate;
		return Double.valueOf(df2.format(price));
	}
	
}
