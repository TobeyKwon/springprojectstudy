package jpabook.japshop.service;

import jpabook.japshop.domain.Member;
import jpabook.japshop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long memberId = memberService.join(member);

        //then
        em.flush();
        Assertions.assertThat(member).isSameAs(memberRepository.findOne(memberId));
    }
    
    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }
        
        //then
        Assertions.fail("예외가 발생해야 됨.");
    }
}