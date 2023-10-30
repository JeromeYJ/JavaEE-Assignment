package edu.cn.demo.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jerome
 * @since 2023-10-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Product_supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer supplier_id;

    private Integer product_id;


}
