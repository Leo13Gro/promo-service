package com.profi.promoservice.entity;

import com.profi.promoservice.dto.PromotionResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Prize> prizes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Participant> participants;

    public PromotionResponse toDTO() {
        return new PromotionResponse(id, name, description);
    }

    public Promotion(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
