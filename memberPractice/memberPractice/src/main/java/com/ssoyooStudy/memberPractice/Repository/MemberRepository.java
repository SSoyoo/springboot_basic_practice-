package com.ssoyooStudy.memberPractice.Repository;

import com.ssoyooStudy.memberPractice.dto.MemberDto;
import com.ssoyooStudy.memberPractice.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //이메일로 회원 정보 조회
    Optional<MemberEntity> findByMemberEmail(String memberEmail);


}
