package com.mise.hotspot.pojo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotNull
    @ElementCollection
    private List<String> url;

    private String view;

    private String rate;

    private String rateNumber;

    private String location;

    private String longitude;

    private String latitude;

    private String city;

    private String introduction;

    @NotNull
    @ElementCollection
    private List<Long> rooms;

    @NotNull
    @ElementCollection
    private List<String> remains;

    public List<String> getRemains() {
        return remains;
    }

    public void setRemains(List<String> remains) {
        this.remains = remains;
    }

    @NotNull
    @ElementCollection
    private List<String> prices;
}
