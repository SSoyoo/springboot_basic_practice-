package com.ssoyooStudy.memberPractice.entity;



import com.ssoyooStudy.memberPractice.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "member_table")

public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    public static MemberEntity toMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberName(memberDto.getMemberName());
        return memberEntity;

    }

    public static MemberEntity toUpdateMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDto.getId()); // id를 셋팅해줘야 스프링부트에서 업데이트 쿼리가 나감
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberName(memberDto.getMemberName());
        return memberEntity;

    }
}
