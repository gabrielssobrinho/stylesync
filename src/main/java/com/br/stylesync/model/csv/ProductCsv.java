package com.br.stylesync.model.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.ToString;

@Data
public class ProductCsv {
    @CsvBindByName(column = "CODIGO")
    private String sku;

    @CsvBindByName(column = "NOME")
    private String name;

    @CsvBindByName(column = "MARCA")
    private String brand;

    @CsvBindByName(column = "TAMANHO")
    private String size;

    @CsvBindByName(column = "PRECO")
    private String price;

    @CsvBindByName(column = "VARIACAO")
    private String variation;

    @CsvBindByName(column = "QUANTIDADE")
    private String quantity;
}
