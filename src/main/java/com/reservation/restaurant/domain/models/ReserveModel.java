package com.reservation.restaurant.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_reserves")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReserveModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserve;

    @ManyToOne
    @JoinColumn(name = "spot_id", nullable = false)
    private SpotModel spot;

    @Column(name = "reserve_owner", nullable = false)
    private String reserveOwner;
    @Column(name = "reserve_cpf", unique = true)
    private String reserveCpf;
    @Column(name = "people_number", nullable = false)
    private Integer peopleNumber;
    @Column(name = "reserve_status")
    private ReserveStatus reserveStatus;
    @Column(name = "reserve_date")
    private LocalDate reserveDate;
    @Column(name = "reserve_effective_date")
    private LocalDateTime reserveEffectiveDate;
    @Column(columnDefinition = "TEXT")
    private String notes;


    public Long getIdReserve() {
        return idReserve;
    }

    public void setIdReserve(Long idReserve) {
        this.idReserve = idReserve;
    }

    public SpotModel getSpot() {
        return spot;
    }

    public void setSpot(SpotModel spot) {
        this.spot = spot;
    }

    public String getReserveCpf() {
        return reserveCpf;
    }

    public void setReserveCpf(String reserveCpf) {
        this.reserveCpf = reserveCpf;
    }

    public String getReserveOwner() {
        return reserveOwner;
    }

    public void setReserveOwner(String reserveOwner) {
        this.reserveOwner = reserveOwner;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public ReserveStatus getReserveStatus() {
        return reserveStatus;
    }

    public void setReserveStatus(ReserveStatus reserveStatus) {
        this.reserveStatus = reserveStatus;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    public LocalDateTime getReserveEffectiveDate() {
        return reserveEffectiveDate;
    }

    public void setReserveEffectiveDate(LocalDateTime reserveEffectiveDate) {
        this.reserveEffectiveDate = reserveEffectiveDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
