package com.profi.promoservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class RaffleResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Participant winner;

    @OneToOne
    private Prize prize;

    public RaffleResult(Participant winner, Prize prize) {
        this.winner = winner;
        this.prize = prize;
    }
}
