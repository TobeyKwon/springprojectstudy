package jpabook.japshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "orders")
@Entity
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
