package com.example.bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT_TRANSACTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccountTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView(Views.FullTransaction.class)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID", nullable = false)
    private BankAccount bankAccount;

    @Enumerated(EnumType.STRING)
    @JsonView(Views.FullTransaction.class)
    private AccountTransactionType type;

    @Column(nullable = false)
    @JsonView(Views.FullTransaction.class)
    private BigDecimal amount;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    @JsonView(Views.FullTransaction.class)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "UPDATED_DATE", nullable = false)
    @JsonView(Views.FullTransaction.class)
    private LocalDateTime updatedDate;

}
