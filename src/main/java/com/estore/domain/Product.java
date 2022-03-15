package com.estore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double unitPrice;
    private String image;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "producteDate")
    private Date productDate;

    @Column(name = "available")
    private Boolean isAvailable;

    // private Integer categoryId;
    private Integer quantity;
    private String description;
    private Double discount;
    private Integer viewCount;

    @Column(name = "special")
    private Boolean isSpecial;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product){
            Product p = (Product) obj;
            return Objects.equals(p.id, id);
        }

        return false;
    }
}
