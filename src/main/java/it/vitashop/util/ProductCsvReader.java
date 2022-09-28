package it.vitashop.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import it.vitashop.model.dto.product.ProductRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ProductCsvReader {

	public static List<ProductRequest> read(FileReader file) throws IOException {
		CSVReader reader = new CSVReader(file, ',', '\'', 1);

		List<ProductRequest> products = new ArrayList<>();

		String[] record = null;

		while ((record = reader.readNext()) != null) {
			ProductRequest product = new ProductRequest();
			product.setName(record[0]);
			product.setImageFile(record[1]);
			product.setPrice(Double.parseDouble(record[2]));
			product.setCategory(record[3]);
			product.setDescrption(record[4]);
			products.add(product);
		}

		reader.close();

		return products;
	}
}
